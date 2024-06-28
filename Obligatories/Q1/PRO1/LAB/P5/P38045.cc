#include <iostream>
#include<cmath>
using namespace std;

int potencia (int x) {
    x = x*x;
    return x;
}

int main() {
    cout.setf(ios::fixed);
    cout.precision(6);

    int x;

    while (cin >> x) {
        cout << potencia(x) << ' ' << sqrt(x) << endl;
    }
}

