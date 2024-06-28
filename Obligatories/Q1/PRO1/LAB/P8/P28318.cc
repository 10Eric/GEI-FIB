#include <iostream>
#include <vector>
using namespace std;

int main () {
    int f; cin >> f;
    int c; cin >> c;
    typedef vector<int> Row;
    typedef vector<Row> Matrix;
    Matrix my_matrix(f,Row(c));

    for (int j = 0; j < f; ++j) {
        for (int i = 0; i < c; ++i) cin >> my_matrix [j][i];
    }

    string s;
    while (cin >> s) {
        if (s=="fila") {
            int i;
            cin >> i;
            cout << s << ' ' << i << ':';
            i--;
            for (int j = 0; j < c; j++) cout << ' ' << my_matrix[i][j];
            cout << endl;
        } else if (s=="columna") {
            int j;
            cin >> j;
            cout << s << ' ' << j << ':';
            j--;
            for (int i = 0; i < f; i++) cout << ' ' << my_matrix[i][j];
            cout << endl;
        } else {
            int i,j;
            cin >> i >> j;
            cout << "element " << i << ' ' << j << ": " << my_matrix[i-1][j-1] << endl;
        }
    }
}
