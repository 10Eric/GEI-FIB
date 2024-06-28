#include <iostream>
using namespace std;

int main() {
	int n,r,suma;
    while (cin >> n) {
        r = n;
        suma = 0;
        if ((n/10) == 0) cout << "La suma dels digits de " << r << " es "<< r << '.' << endl;

        if((n/10) != 0) {
            while ((n/10) != 0)  {
            suma += n%10;
            n = n/10;
            }
        cout << "La suma dels digits de " << r << " es "<< suma+n << '.' << endl; }
    }
}
