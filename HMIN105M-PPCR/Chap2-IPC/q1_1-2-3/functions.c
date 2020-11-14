#include "functions.h"

// void calculation_init(float *nb1, float *nb2, char *op) { 
//   printf("Operation: %f %c %f\n", *nb1, *op, *nb2);
// }

float calculation(float a, float b, char operator) {
  float res = -99999;
  switch (operator) {
   case '+':
      res = a+b;
      break;
   case '-':
      res = a-b;
      break;
   case '*':
      res = a*b;
      break;
   case '/':
      res = a/b;
      break;
  }
  return res;
}