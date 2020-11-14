#include <iostream>
using namespace std;
#include <assert.h> 
#include <sys/types.h>
#include <unistd.h>


void exo1_1() {
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
	int m = 0;
	int* T2 = (int*) malloc(sizeof(int));
	
	for (int i = 0; i < n; i++) {
		if (T1[i] >= a && T1[i] <= b) {
			m++;
			T2 = (int*) realloc(T2, m * sizeof(int));
			T2[m-1] = T1[i]; 
		}  	
	} 

	return T2;
}

void exo1_2() {
	int const n = 5;
	int T1[n] = { 1, 3, 5, 7, 9 };
	int* T2 = extract(T1, n, 2, 5);
	print(T1, n);
	print(T2, n);

}

void exo1_3() {
	int n = 0;

	cout << "Saisissez la taille du tableau : ";
	cin >> n;
	int tab[n];
	for (int i = 0; i < n; i++) {
		cout << "tab[" << i << "] = ";
		cin >> tab[i]; 
	}
   
} 

void exo2(int argc, char* argv[]) {
	pid_t id = 1;

	assert(argc == 3);

	cout << "-- Start: " << getpid() << " --" << endl;

	for (int i = 0; i < atoi(argv[1]); i++) {
		sleep(1);

		if (id != 0) {
			id = fork();
		}

		for (int j = 0; j < atoi(argv[2]); j++) {
			if (id == 0) {
				id = fork();
			}
		} 

		sleep(1);
		cout << " pid: " << getpid() << " ppid: " << getppid() << endl;
		sleep(1);
	}

	cout << "-- End: " << getpid() << " --" << endl;
	sleep(1);
}   

int main(int argc, char* argv[]) {
	exo2(argc, argv);
}