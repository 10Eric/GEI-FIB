#include <iostream>
using namespace std;

int main () {
int n,n1;
    cin >> n;
    n1 = n;
    int ndigits = 0;

    if (n == 0) ndigits = 1;
        else if (n > 0){
            while (n > 0) {
            ++ndigits;
            n /= 10;
            }
            }

    cout << "El nombre de digits de" << ' ' << n1 << ' '<< "es " << ndigits << '.' <<endl;
}
