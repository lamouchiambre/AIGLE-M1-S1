#include <iostream>
using namespace std; 

int main() {
  int a = 10;
  int b = 25;
  int* p = &b;
  int* pp = &a;

  // 1.
  cout << *(&(*(*(&p))));
}