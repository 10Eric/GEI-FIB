#include <iostream>
#include <cmath>
using namespace std;

int main() {
	cout.precision(4);
	cout.setf(ios::fixed);

    double c,i, f; cin >> c >> i;
    int t; cin >> t;
    string interes; cin >> interes;
    i = i/100;

    if (interes == "simple") {
        f = c + (c * i * t);
        cout << f << endl;
    }

    if  (interes == "compost") {
        f = c * pow(1+i, t);
        cout << f << endl;
    }
}
