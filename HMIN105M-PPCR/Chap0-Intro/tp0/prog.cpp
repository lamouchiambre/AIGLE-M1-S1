#include <iostream>
using namespace std;
#include <assert.h> 


void exo1() {
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
}

void print(int T[], int n) {
	cout << "[ ";
	for (int i = 0; i < n; i++) {
		cout << T[i] << " ";
	}
	cout << "]" << endl;
}

int* extract(int T1[], int n, int a, int b) {
	int count = 0;
	int* T2 = (int*) malloc(sizeof(int));
	assert(T2 != NULL);
	
	if (a > b) {
		a = a + b;
		b = a - b;
		a = a - b;
	}
	for (int i = 0; i < n; i++) {
		if (T1[i] >= a && T1[i] <= b) {
			count++;
			T2 = (int*) realloc(T2, count * sizeof(int));
			T2[count] = T1[i]; 
		}  	
	} 

	return T2;
}

void exo2() {
	int const n = 5;
	int T1[n] = { 1, 2, 3, 4, 5 };
	int* T2 = extract(T1, n, 2, 5);
	print(T1, n);
	print(T2, n);

}

int main(int argc, char* argv[]) {
	exo2();
}