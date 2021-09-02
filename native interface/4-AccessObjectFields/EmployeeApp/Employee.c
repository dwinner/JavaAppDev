#include "Employee.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_Employee_raiseSalary(JNIEnv* env, jobject this_obj, jdouble byPercent)
{
   /* Получение класса */
   jclass class_Employee = (*env)->GetObjectClass(env, this_obj);

   /* Получение идентификатора поля */
   jfieldID id_salary = (*env)->GetFieldID(env, class_Employee, "salary", "D");

   /* Получение значения поля */
   jdouble salary = (*env)->GetDoubleField(env, this_obj, id_salary);

   salary *= 1 + byPercent / 100;

   /* Установка значения поля */
   (*env)->SetDoubleField(env, this_obj, id_salary, salary);
}
