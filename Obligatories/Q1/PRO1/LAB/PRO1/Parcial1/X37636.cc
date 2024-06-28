#include <iostream>
using namespace std;

int main () {
    cout.setf(ios::fixed);
    cout.precision(1);

    int n; cin >> n;
    char temp;
    double gr;

    for (int j = 0; j < n; ++j) {
        cin >> temp >> gr;

        if (temp == 'C') {
            gr = (1.8 * gr + 32);
            cout << 'F' << ' ' << gr << endl;
        }

        if (temp == 'F') {
            gr = ((gr-32)/1.8);
            cout << 'C' << ' ' << gr << endl;
        }
    }
}

