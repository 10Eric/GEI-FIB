#include <iostream>
using namespace std;

int main() {
	int b,n,p,suma;
    cin >> b;
    while (cin >> n) {
        p = n;
        suma = 0;
        if ((n/b) == 0) cout << p << ": " << n << endl;

        if((n/b) != 0) {
            while ((n/b) != 0)  {
            suma += n%b;
            n = n/b;
            }
        cout << p << ": " << suma + n << endl; }
    }
}
