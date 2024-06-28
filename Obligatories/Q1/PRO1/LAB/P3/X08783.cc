#include <iostream>
using namespace std;

int main() {
	int b, n;

    while (cin >> b >> n) {
        int contador = 0;
        while (n != 0) {
			n = n/b;
			++contador;
		}
		cout << contador << endl;
	}
}
