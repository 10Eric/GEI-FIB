#include <iostream>
#include <vector>
#include <string>
using namespace std;

typedef vector<vector<int> > Matrix;
typedef vector<vector<char> > Matrix1;

int MAX_FILES (string paraula,int f,int c, Matrix1 Lletres, Matrix Valors) {
    int z = paraula.size ();
    int max = 0;

    for (int l = 0; l < f; ++l) {
            int q = 0;
            int temp = 0;
        for (int y = 0; y < c; ++y){
            if (Lletres [l][y] == paraula [q]) {
                temp += Valors [l][y];
                if (q == (z-1)) {
                    if (max < temp) max = temp;
                }
                ++q;
            }

        }
    }

    return max;
}

int MAX_COLUMNES (string paraula, int f, int c, Matrix1 Lletres, Matrix Valors) {
    int z = paraula.size ();
    int max1 = 0;

    for (int p = 0; p < c; ++p) {
            int az = 0;
            int temp1 = 0;
        for (int e = 0; e < f; ++e){
            if (Lletres [p][e] == paraula [az]) {
                temp1 += Valors [p][e];
                if (az == (z-1)) {
                    if (max1 < temp1) max1 = temp1;
                }
                ++az;
            }
        }
    }
    return max1;
}


int main () {
    int f;
    int c;
    while (cin >> f and cin >> c) {
    Matrix1 Lletres (f,vector<char>(c));
    Matrix Valors (f,vector<int>(c));

    for (int j = 0; j < f; ++j) {
        for (int i = 0; i < c; ++i) cin >> Lletres [j][i];
    }

    for (int r = 0; r < f; ++r) {
        for (int s = 0; s < c; ++s) cin >> Valors [r][s];
    }


    int n; cin >> n;
    string paraula;
    for (int w = 0; w < n; ++w) {
        cin >> paraula;
        int z = MAX_FILES (paraula,f,c, Lletres, Valors);
        int x = MAX_COLUMNES(paraula,f,c, Lletres, Valors);

        if (z == 0 and x == 0) cout << "no" << endl;

        else if (z >= x) cout << z << endl;
        else cout << x << endl;
    }
    }
}


