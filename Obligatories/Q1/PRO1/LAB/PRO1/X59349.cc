#include <iostream>
#include <typeinfo>
#include <vector>
using namespace std;

typedef vector<char> Fila;
typedef vector<Fila> Flag;

Flag read_flag (int n) {
    Flag leer(n,vector<char>(n));
    for (int w = 0; w < n; ++w) {
        for (int r = 0; r < n; ++r) cin >> leer [w][r];
    }
    return leer;
}

int column_count (const Flag& bandera) {
    int  contador = 0;
    int l = bandera.size();
    for (int q = 0; q < l; ++q) {
        char x1 = bandera[0][q];
        char x2 = bandera[1][q];
        if (x1 == x2) {
            if (l == 2) ++contador;
            else {
                int s = 2;
                while (s < l and x1 == x2) {
                    x2 = bandera[s][q];
                    ++s;
                }

                if (s == l-1 and x1 != x2) ++contador;

    }
        }
    }
    return contador;
}

int main() {
    int n;
    while (cin >> n) {
        Flag flag = read_flag(n);
        cout << column_count(flag) << endl;
    }
}
