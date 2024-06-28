#include <iostream>
using namespace std;

int main (){
    int n = 1;
    string p,s;
    cin >> p;

    bool trobat = false;

    while (cin >> s and trobat == false) {
        if (s == p) {
            cout << n << endl;
            trobat = true;

        }

        ++n;
    }

    if (trobat == false) cout << "No existe" << endl;
}
