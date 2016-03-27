/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */

#include "Printf4.h"
#include <string.h>
#include <stdlib.h>
#include <float.h>

/**
 * @param format Строка, содержащая спецификатор формата
 * (например, "%8.2f"). Подстроки "%%" пропускаются.
 * @return Указатель на спецификатор формата (без учета '%')
 * или NULL, если уникального спецификатора формата не было.
 */
char* find_format(const char format[])
{
   char* p;
   char* q;

   p = strchr(format, '%');
   while (p != NULL && *(p + 1) == '%')   /* Пропуск %% */
      p = strchr(p + 2, '%');
   if (p == NULL) return NULL;
   /* Проверка единственности */
   p++;
   q = strchr(p, '%');
   while (q != NULL && *(q + 1) == '%')   /* Пропуск %% */
      q = strchr(q + 2, '%');
   if (q != NULL) return NULL;   /* % не единственный */
   q = p + strspn(p, " -0+#");   /* Пропуск флагов */
   q += strspn(q, "0123456789"); /* Пропуск ширины поля */
   if (*q == '.') /* Пропуск точности */
   {
      q++;
      q += strspn(q, "0123456789");
   }
   /* Формат, отличный от формата с плавающей точкой */
   if (strchr("eEfFgG", *q) == NULL) return NULL;
   return p;
}

JNIEXPORT void JNICALL Java_Printf4_fprint(JNIEnv* env, jclass cl, jobject out, jstring format, jdouble x)
{
   const char* cformat;
   char* fmt;
   jclass class_PrintWriter;
   jmethodID id_print;
   char* cstr;
   int width;
   int i;

   if (format == NULL)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/NullPointerException"), "Printf4.fprint: format is null");
      return;
   }

   cformat = (*env)->GetStringUTFChars(env, format, NULL);
   fmt = find_format(cformat);

   if (fmt == NULL)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"), "Printf4.fprint: format is invalid");
      return;
   }

   width = atoi(fmt);
   if (width == 0) width = DBL_DIG + 10;
   cstr = (char*) malloc(strlen(cformat) + width);

   if (cstr == NULL)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Printf4.fprint: malloc failed");
      return;
   }

   sprintf(cstr, cformat, x);

   (*env)->ReleaseStringUTFChars(env, format, cformat);

   /* Обращение к ps.print(str) */

   /* Получение класса */
   class_PrintWriter = (*env)->GetObjectClass(env, out);

   /* Получение идентификатора метода */
   id_print = (*env)->GetMethodID(env, class_PrintWriter, "print", "(C)V");

   /* Вызов метода */
   for (i = 0; cstr[i] != 0 && !(*env)->ExceptionOccured(env); i++)
      (*env)->CallVoidMethod(env, out, id_print, cstr[i]);

   free(cstr);
}
