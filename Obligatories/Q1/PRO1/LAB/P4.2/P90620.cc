#include <iostream>
using namespace std;

int main () {
    int n1,n2,n3;
    cin >> n1 >> n2 >> n3;
    bool trobat = false;

    while (n3 != 0 and not trobat) {
        if (n2 > 3143){
            if (n2 > n1 and n2 > n3) {
                cout << "SI" << endl;
                trobat = true;
            }
        }
        n1 = n2;
        n2 = n3;
        cin >> n3;
    }

    if (not trobat) cout << "NO" << endl;
}
