#include "Employee.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_Employee_raiseSalary(JNIEnv* env, jobject this_obj, jdouble byPercent)
{
   /* ��������� ������ */
   jclass class_Employee = (*env)->GetObjectClass(env, this_obj);

   /* ��������� �������������� ���� */
   jfieldID id_salary = (*env)->GetFieldID(env, class_Employee, "salary", "D");

   /* ��������� �������� ���� */
   jdouble salary = (*env)->GetDoubleField(env, this_obj, id_salary);

   salary *= 1 + byPercent / 100;

   /* ��������� �������� ���� */
   (*env)->SetDoubleField(env, this_obj, id_salary, salary);
}
