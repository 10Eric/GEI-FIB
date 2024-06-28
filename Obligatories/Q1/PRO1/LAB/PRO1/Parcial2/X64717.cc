#include <iostream>
using namespace std;

int main () {
    char c;
    while (cin >> c) {
        int contador = 0;
        bool trobat = false;
        char pred = '#';

        while (c != '$') {
                if (c == 'a') {
                    trobat = true;

                }


                else if (pred != '#' and c == '#') {
                    if (not trobat) ++contador;
                    trobat = false;
            }

            pred = c;
            cin >> c;
        }

            if (pred != '#' and not trobat) ++contador;

        cout << contador << endl;
    }
}


