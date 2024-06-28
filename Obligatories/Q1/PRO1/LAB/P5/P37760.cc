#include <iostream>
#include<cmath>
using namespace std;

int main() {
    cout.setf(ios::fixed);
    cout.precision(6);

    double x;
    while (cin >> x) {
        x = x*(2*M_PI/360);
        cout << sin(x) << ' ' << cos(x) << endl;
    }
}
