#include <iostream>
using namespace std;

int main () {
    int v,espacios,p;
    cin >> v;
    bool acabado = false;

    p= v*2-1;
    for (int i=0; i < v; ++i){
        espacios = i;

        for (int j = 1; j < v*2 and not acabado; ++j){
            if (i!= v-1) {
                if (espacios > 0) {
                    cout << ' ';
                    --espacios;
                }

                else if (i == j-1) cout << 'V';
                else if (p == j) {
                    cout << 'V';
                    acabado = true;
                }

                else cout << ' ';
            }

            else {
                if (j < v) cout << ' ';
                else if (j == v) {
                    acabado = true;
                    cout << 'V';
                }
            }
        }
        acabado = false;
        --p;
        cout << endl;
    }
}
