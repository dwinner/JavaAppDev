/**
 * @version 1.00 1997-07-01
 * @author Cay Horstmann
 */

 #include "Win32RegKey.h"
 #include "Win32RegKeyEnumeration.h"
 #include <string.h>
 #include <stdlib.h>
 #include <windows.h>

 JNIEXPORT jobject JNICALL Java_Win32RegKey_getValue(JNIEnv* env, jobject this_obj, jobject name)
 {
    const char* cname;
    jstring path;
    const char* cpath;
    HKEY hkey;
    DWORD type;
    DWORD size;
    jclass this_class;
    jfieldID id_root;
    jfieldID id_path;
    HKEY root;
    jobject ret;
    char* cret;

    /* Получение класса. */
    this_class = (*env)->GetObjectClass(env, this_obj);

    /* Получение идентификаторов полей. */
    id_root = (*env)->GetFieldID(env, this_class, "root", "I");
    id_path = (*env)->GetFieldID(env, this_class, "path", "Ljava/lang/String");

    /* Получение полей. */
    root = (HKEY) (*env)->GetIntField(env, this_obj, id_root);
    path = (jstring) (*env)->GetObjectField(env, this_obj, id_path);
    cpath = (*env)->GetStringUTFChars(env, path, NULL);

    /* Открытие раздела, соответствующего ключу реестра. */
    if (RegOpenKeyEx(root, cpath, 0, KEY_READ, &hkey) != ERROR_SUCCESS)
    {
       (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Open key failed");
       /* Не удалось открыть ключ */
       (*env)->ReleaseStringUTFChars(env, path, cpath);
       return NULL;
    }

    (*env)->ReleaseStringUTFChars(env, path, cpath);
    cname = (*env)->GetStringUTFChars(env, name, NULL);

    /* Определение типа и размера значения. */
    if (RegQueryValueEx(hkey, cname, NULL, &type, NULL, &size) != ERROR_SUCCESS)
    {
       (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Query value key failed");
       /* Запрос значения ключа не удался */
       RegCloseKey(hkey);
       (*env)->ReleaseStringUTFChars(env, name, cname);
       return NULL;
    }

    /* Выделение памяти для размещения значения. */
    cret = (char*) malloc(size);

    /* Чтение значения. */
    if (RegQueryValueEx(hkey, cname, NULL, &type, cret, &size) != ERROR_SUCCESS)
    {
       (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Query value key failed");
       /* Запрос значения ключа не удался */
       free(cret);
       RegCloseKey(hkey);
       (*env)->ReleaseStringUTFChars(env, namem cname);
       return NULL;
    }

    /* В зависимости от типа, значение помещается в строку,
    целочисленную переменную или байтовый массив. */
   if (type == REG_SZ)
   {
      ret = (*env)->NewStringUTF(env, cret);
   }
   else if (type == REG_DWORD)
   {
      jclass class_Integer = (*env)->FindClass(env, "java/lang/Integer");
      /* Получение идентификатора конструктора. */
      jmethodID id_Integer = (*env)->GetMethodID(env, class_Integer, "<init>", "(I)V");
      int value = *(int*) cret;
      /* Вызов конструктора. */
      ret = (*env)->NewObject(env, class_Integer, id_Integer, value);
   }
   else if (type == REG_BINARY)
   {
      ret = (*env)->NewByteArray(env, size);
      (*env)->SetByteArrayRegion(env, (jarray) ret, 0, size, cret);
   }
   else
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Unsupported value type");
      /* Неподдерживаемый тип значения */
      ret = NULL;
   }

   free(cret);
   RegCloseKey(hkey);
   (*env)->ReleaseStringUTFChars(env, name, cname);

   return ret;
 }

JNIEXPORT void JNICALL Java_Win32RegKey_setValue(JNIEnv* env, jobject this_obj, jstring name, jobject value)
{
   const char* cname;
   jstring path;
   const char* cpath;
   HKEY hkey;
   DWORD type;
   DWORD size;
   jclass this_class;
   jclass class_value;
   jclass class_Integer;
   jfieldID id_root;
   jfieldID id_path;
   HKEY root;
   const char* cvalue;
   int ivalue;

   /* Получение класса. */
   this_class = (*env)->GetObjectClass(env, this_obj);

   /* Получение идентификаторов полей. */
   id_root = (*env)->GetFieldID(env, this_class, "root", "I");
   id_path = (*env)->GetFieldID(env, this_class, "path", "Ljava/lang/String;");

   /* Получение полей. */
   root = (HKEY) (*env)->GetIntField(env, this_obj, id_root);
   path = (jstring) (*env)->GetObjectField(env, this_obj, id_path);
   cpath = (*env)->GetStringUTFChars(env, path, NULL);

   /* Открытие раздела, соответствующего ключу реестра. */
   if (RegOpenKeyEx(root, cpath, 0, KEY_WRITE, &hkey) != ERROR_SUCCESS)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Open key failed");
      /* Не удалось открыть ключ */
      (*env)->ReleaseStringUTFChars(env, path, cpath);
      return;
   }

   (*env)->ReleaseStringUTFChars(env, path, cpath);
   cname = (*env)->GetStringUTFChars(env, name, NULL);

   class_value = (*env)->GetObjectClass(env, value);
   class_Integer = (*env)->FindClass(env, "java/lang/Integer");
   /* Определение типа значения. */
   if ((*env)->IsAssignableFrom(env, class_value, (*env)->FindClass(env, "java/lang/String")))
   {
      /* Строка - получение указателя. */
      cvalue = (*env)->GetStringUTFChars(env, (jstring) value, NULL);
      type = REG_SZ;
      size = (*env)->GetStringLength(env, (jstring) value) + 1;
   }
   else if ((*env)->IsAssignableFrom(env, class_value, class_Integer))
   {
      /* Целое число - вызов intValue для получения значения */
      jmethodID id_intValue = (*env)->GetMethodID(env, class_Integer, "intValue", "()I");
      ivalue = (*env)->CallIntMethod(env, value, id_intValue);
      type = REG_DWORD;
      cvalue = (char*) &ivalue;
      size = 4;
   }
   else if ((*env)->IsAssignableFrom(env, class_value, (*env)->FindClass(env, "[B")))
   {
      /* Байтовый массив - получение указателя. */
      type = REG_BINARY;
      cvalue = (char*) (*env)->GetByteArrayElements(env, (jarray) value, NULL);
      size = (*env)->GetArrayLength(env, (jarray) value);
   }
   else
   {
      /* Данный тип не поддерживается. */
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Unsupported value type");
      /* Не поддерживаемый тип значения */
      RegCloseKey(hkey);
      (*env)->ReleaseStringUTFChars(env, name, cname);
      return;
   }

   /* Установка значения. */
   if (RegSetValueEx(hkey, cname, 0, type, cvalue, size) != ERROR_SUCCESS)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Set value failed");
      /* Установка значения не удалась */
   }

   RegCloseKey(hkey);
   (*env)->ReleaseStringUTFChars(env, name, cname);

   /* Если значение представляло собой строку или байтовый массив,
   освобождается занимаемая им память. */
   if (type == REG_SZ)
   {
      (*env)->ReleaseStringUTFChars(env, (jstring) value, cvalue);
   }
   else if (type == REG_BINARY)
   {
      (*env)->ReleaseByteArrayElements(env, (jarray) value, (jbyte*) cvalue, 0);
   }
}

/* Вспомогательная функция для получения имен. */
static int startNameEnumeration(JNIEnv* env, jobject this_obj, jclass this_class)
{
   jfieldID id_index;
   jfieldID id_count;
   jfieldID id_root;
   jfieldID id_path;
   jfieldID id_hkey;
   jfieldID id_maxsize;

   HKEY root;
   jstring path;
   const char* cpath;
   HKEY hkey;
   DWORD maxsize = 0;
   DWORD count = 0;

   /* Получение идентификаторов полей. */
   id_root = (*env)->GetFieldID(env, this_class, "root", "I");
   id_path = (*env)->GetFieldID(env, this_class, "path", "Ljava/lang/String;");
   id_hkey = (*env)->GetFieldID(env, this_class, "hkey", "I");
   id_maxsize = (*env)->GetFieldID(env, this_class, "maxsize", "I");
   id_index = (*env)->GetFieldID(env, this_class, "index", "I");
   id_count = (*env)->GetFieldID(env, this_class, "count", "I");

   /* Получение значений полей. */
   root = (HKEY) (*env)->GetIntField(env, this_obj, id_root);
   path = (jstring) (*env)->GetObjectField(env, this_obj, id_path);
   cpath = (*env)->GetStringUTFChars(env, path, NULL);

   /* Открытие раздела, соответствующего ключу реестра. */
   if (RegOpenKeyEx(root, cpath, 0, KEY_READ, &hkey) != ERROR_SUCCESS)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Open key failed");
      /* Не удалось открыть ключ */
      (*env)->ReleaseStringUTFChars(env, path, cpath);
      return -1;
   }
   (*env)->ReleaseStringUTFChars(env, path, cpath);

   /* Запрос счетчика имен и максимальной длины имени. */
   if (RegQueryInfoKey(hkey, NULL, NULL, NULL, NULL, NULL, NULL, &count, &maxsize, NULL, NULL, NULL) != ERROR_SUCCESS)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Query info key failed");
      /* Запрос информации о ключе не удался */
      RegCloseKey(hkey);
      return -1;
   }

   /* Установка значений полей. */
   (*env)->SetIntField(env, this_obj, id_hkey, (DWORD) hkey);
   (*env)->SetIntField(env, this_obj, id_maxsize, maxsize + 1);
   (*env)->SetIntField(env, this_obj, id_index, 0);
   (*env)->SetIntField(env, this_obj, id_count, count);
   return count;
}

JNIEXPORT jboolean JNICALL Java_Win32RegKeyEnumeration_hasMoreElements(JNIEnv* env, jobject this_obj)
{
   jclass this_class;
   jfieldID id_index;
   jfieldID id_count;
   int index;
   int count;

   /* Получение класса. */
   this_class = (*env)->GetObjectClass(env, this_obj);

   /* Получение идентификаторов полей. */
   id_index = (*env)->GetFieldID(env, this_class, "index", "I");
   id_count = (*env)->GetFieldID(env, this_class, "count", "I");

   index = (*env)->GetIntField(env, this_obj, id_index);
   if (index == -1)
   {
      count = startNameEnumeration(env, this_obj, this_class);
      index = 0;
   }
   else
      count = (*env)->GetIntField(env, this_obj, id_count);
   return index < count;
}

JNIEXPORT jobject JNICALL Java_Win32RegKeyEnumeration_nextElement(JNIEnv* env, jobject this_obj)
{
   jclass this_class;
   jfieldID id_index;
   jfieldID id_hkey;
   jfieldID id_count;
   jfieldID id_maxsize;

   HKEY hkey;
   int index;
   int count;
   DWORD maxsize;

   char* cret;
   jstring ret;

   /* Получение класса. */
   this_class = (*env)->GetObjectClass(env, this_obj);

   /* Получение идентификаторов полей. */
   id_index = (*env)->GetFieldID(env, this_class, "index", "I");
   id_count = (*env)->GetFieldID(env, this_class, "count", "I");
   id_hkey = (*env)->GetFieldID(env, this_class, "hkey", "I");
   id_maxsize = (*env)->GetFieldID(env, this_class, "maxsize", "I");

   index = (*env)->GetIntField(env, this_obj, id_index);
   if (index >= count)  /* Содержимое исчерпано. */
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "java/util/NoSuchElementException"), "past end of enumeration");
      /* Конец перечисления */
      return NULL;
   }

   maxsize = (*env)->GetIntField(env, this_obj, id_maxsize);
   hkey = (HKEY) (*env)->GetIntField(env, this_obj, id_hkey);
   cret = (char*) malloc(maxsize);

   /* Поиск следующего имени. */
   if (RegEnumValue(hkey, index, cret, &maxsize, NULL, NULL, NULL, NULL) != ERROR_SUCCESS)
   {
      (*env)->ThrowNew(env, (*env)->FindClass(env, "Win32RegKeyException"), "Enum value failed");
      /* Не удалось перечислить значение */
      free(cret);
      RegCloseKey(hkey);
      (*env)->SetIntField(env, this_obj, id_index, count);
      return NULL;
   }

   ret = (*env)->NewStringUTF(env, cret);
   free(cret);

   /* Увеличение индекса. */
   index++;
   (*env)->SetIntField(env, this_obj, id_index, index);

   if (index == count)  /* Конец перечисления */
   {
      RegCloseKey(hkey);
   }

   return ret;
}
