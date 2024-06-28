#include <iostream>
using namespace std;

int main () {
    cout.setf(ios::fixed);
    cout.precision(4);

    int n; cin >> n;

    for (int i= 0; i < n; ++i){
        int m; cin >> m;
        double max, min, media,valor;
        for (int j= 0; j < m; ++j) {
            cin >> valor;
            if (j == 0){
                max = valor;
                min = valor;
                media = valor;
            }
            else {

            if (valor > max) max = valor;
            if (valor < min) min = valor;
            media = media + valor;
            }

            if (j == (m-1)) {
                media = media/m;
                cout << min << ' '<< max << ' '<< media << endl;
            }
        }

    }
}

