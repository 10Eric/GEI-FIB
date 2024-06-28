#include <iostream>
using namespace std;

int main () {
    cout.setf(ios::fixed);
    cout.precision (4);
    int n;
    cin >> n;
    double y = 1, suma = 0;

    while (y <= n) {
        suma = suma + 1/y;
        ++y;

    }
    cout << suma << endl;
}
