#include <iostream>
using namespace std;

int main () {
    int n; cin >> n;
    int m,pos = 1;
    cin >> m;

    bool trobat = false;

    while (m != -1 and trobat == false) {
        if (((n+m)%10) == 0) {
            cout << m << ':' << ' ' << pos << endl;
            trobat = true;
        }
        ++pos;
        cin >> m;
    }

    if (trobat == false) cout << "No existe" << endl;
}
