#include <iostream>
using namespace std;

int main () {

    int m;
    int max,valor;
    while (cin >> m) {
        for (int j= 0; j < m; ++j) {
            cin >> valor;
            if (j == 0){
                max = valor;
            }

            else {
            if (valor > max) max = valor;
            }

            if (j == (m-1)) {
                cout << max << endl;
            }
        }

    }
}
