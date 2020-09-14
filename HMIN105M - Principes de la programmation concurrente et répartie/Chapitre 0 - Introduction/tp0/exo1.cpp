#include <iostream>
using namespace std; 

int main() {
  int a = 10;
  int b = 25;
  int* p = &b;
  int* pp = &a;

  // 1. 25 valeur de b
  //cout << *(&(*(*(&p))));

  // 2. -858993460 adresse de a 
  //cout << *(p - 1);

  // 3. -858993460 adresse de a
  //cout << *(*(&p) - 1);

  // 4. -858993460 adresse de a
  //cout << *(*(&pp) + 1);

  // 5. -858993460 adresse de a
  //cout << *(&(*(*(&p))) - 1);

  // 6. -858993460 adresse de a
  cout << *(&(*(*(&p))) - 1);

  // test
}