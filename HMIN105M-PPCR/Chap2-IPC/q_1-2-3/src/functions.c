#include "functions.h"

void calculation_init(float *a, float *b, char *c) {
  char x;
  float y;
  float t;
  printf(YEL "        [CALCULATION INIT]* What type of mathematical operation would you do %s?\n", " ");
  scanf("\n%c", &x);
  while (!(x == '+' || x == '-' || x == '*' || x == '/')) {
    printf(RED "        [CALCULATION INIT]* ERROR ! Unrecognized operator, Please enter a valid operator. %s\n", " ");
    scanf("\n%c", &x);
  }
  *c = x;
  printf(YEL "        [CALCULATION INIT]* What is the first number for your mathematical operation ?%s\n", " ");
  scanf("\n%f", &y);
  *a = y;
  printf(YEL "        [CALCULATION INIT]* What is the second number for your mathematical operation ?%s\n", " ");
  scanf("\n%f", &t);
  *b = t;

  printf(BLU "        [CALCULATION INIT]* You have entered the operation : %f %c %f %s\n\n\n", *a ,*c ,*b, " ");
}

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