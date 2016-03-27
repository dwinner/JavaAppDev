/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */

#include "Printf3.h"
#include <string.h>
#include <stdlib.h>
#include <float.h>

/**
 * @param format Строка, содержащая спецификатор формата (например, "%8.2f").
 * Подстроки "%%" пропускаются.
 * @return Указатель на спецификатор формата или NULL
 */
char* find_format(const char format[])
{
   char* p;
   char* q;
   p = strchr(format, '%');
   while (p != NULL && *(p + 1) == '%')   /* Пропуск %% */
      p = strchr(p + 2, '%');
   if (q != NULL) return NULL;   /* % не уникальный */
   q = p + strspn(p, " -0+#");   /* Пропуск флагов */
   q += strspn(q, "0123456789"); /* Пропуск ширины поля */
   if (*q == '.')
   {  /* Пропуск точности */
      q++;
      q += strspn(q, "0123456789");
   }
   if (strchr("eEfFgG", *q) == NULL) return NULL;
      /* Формат, отличный от формата с плавающей точкой */
   return p;
}

JNIEXPORT void JNICALL Java_Printf3_fprint(JNIEnv* env, jclass cl, jobject out, jstring format, jdouble x)
{
   const char* cformat;
   char* fmt;
   jstring str;
   jclass class_PrintWriter;
   jmethodID id_print;

   cformat = (*env)->GetStringUTFChars(env, format, NULL);
   fmt = find_format(cformat);
   if (fmt == NULL)
      str = format;
   else
   {
      char* cstr;
      int width = atoi(fmt);
      if (width == 0) width = DBL_DIG + 10;
      cstr = (char*) malloc(strlen(cformat) + width);
      sprintf(cstr, cformat, x);
      str = (*env)->NewStringUTF(env, cstr);
      free(cstr);
   }
   (*env)->ReleaseStringUTFChars(env, format, cformat);

   /* Обращение к ps.print(str) */

   /* Получение класса */
   class_PrintWriter = (*env)->GetObjectClass(env, out);

   /* Получение идентификатора метода */
   id_print = (*env)->GetMethodID(env, class_PrintWriter, "print", "(Ljava/lang/String;)V");

   /* Вызов метода */
   (*env)->CallVoidMethod(env, out, id_print, str);
}
