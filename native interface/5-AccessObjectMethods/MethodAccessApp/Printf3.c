/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */

#include "Printf3.h"
#include <string.h>
#include <stdlib.h>
#include <float.h>

/**
 * @param format ������, ���������� ������������ ������� (��������, "%8.2f").
 * ��������� "%%" ������������.
 * @return ��������� �� ������������ ������� ��� NULL
 */
char* find_format(const char format[])
{
   char* p;
   char* q;
   p = strchr(format, '%');
   while (p != NULL && *(p + 1) == '%')   /* ������� %% */
      p = strchr(p + 2, '%');
   if (q != NULL) return NULL;   /* % �� ���������� */
   q = p + strspn(p, " -0+#");   /* ������� ������ */
   q += strspn(q, "0123456789"); /* ������� ������ ���� */
   if (*q == '.')
   {  /* ������� �������� */
      q++;
      q += strspn(q, "0123456789");
   }
   if (strchr("eEfFgG", *q) == NULL) return NULL;
      /* ������, �������� �� ������� � ��������� ������ */
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

   /* ��������� � ps.print(str) */

   /* ��������� ������ */
   class_PrintWriter = (*env)->GetObjectClass(env, out);

   /* ��������� �������������� ������ */
   id_print = (*env)->GetMethodID(env, class_PrintWriter, "print", "(Ljava/lang/String;)V");

   /* ����� ������ */
   (*env)->CallVoidMethod(env, out, id_print, str);
}
