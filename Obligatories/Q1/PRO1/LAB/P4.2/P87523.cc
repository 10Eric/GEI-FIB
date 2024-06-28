#include <iostream>
using namespace std;

int main() {
    bool trobat = false;
    char m4;
    cin >> m4;

    while (m4 != '.') {
        if (m4 == 'h') {
            cin >> m4;
            if (m4 == 'o') {
                cin >> m4;
                if (m4 == 'l') {
                    cin >> m4;
                    if (m4 == 'a') {
                        trobat = true;
                    }
                }
            }
        }
        else cin >> m4;
    }

    if (trobat) cout << "hola" << endl;
    else cout << "adeu" << endl;
}
