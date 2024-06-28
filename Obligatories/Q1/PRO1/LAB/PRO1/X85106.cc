#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

typedef vector<char> Binario;
typedef vector<Binario> Matriz;

// Pre: n > 0, m > 0
// Post: retorna una matriz de '0' y '1' con dimensiones n*m leída de la entrada
Matriz leer_matriz(int n, int m) {
    Matriz inputs (n, Binario (m));
    for (int r = 0; r < n; ++r) {
        for (int w = 0; w < m; ++w) {
            cin >> inputs[r][w];
        }
    }

    return inputs;
}

// Pre: -
// Post: escribe el string s, seguido por dos punts, seguido por el Binario b
void escribir(string s, const Binario& b) {
    cout << s << ":";
    for (int i = 0; i < b.size(); ++i) cout << " " << b[i];
    cout << endl;
}

// Pre: gamma.size() = epsilon.size()
// Post: retorna el consumo calculado como gamma*epsilon en decimal
int calcula_consumo(const Binario& gamma, const Binario& epsilon) {
    int gamma1 = 0;
    int epsilon1 = 0;
    int s = 0;
    for (int a = gamma.size()-1; a >= 0; --a) {
        if (gamma[a] == '1') gamma1 += pow(2,s);
        if (epsilon[a] == '1') epsilon1 += pow(2,s);
        ++s;
    }
    return gamma1*epsilon1;
}

// Pre: mat.size > 0; 0 <= j < mat[0].size()
// Post: retorna el bit más frecuente en la columna j de mat. En caso de empate retorna '0'.
char bit_mas_comun(const Matriz& mat, int j) {
    int uno = 0;
    int zero = 0;
    for (int q = 0; q < mat.size(); ++q) {
        if (mat[q][j] == '0') ++zero;
        else ++uno;
    }
    if (zero >= uno) return '0';
    else return '1';
}

// Pre: mat.size > 0; gamma.size() == mat[0].size(); epsilon.size() == mat[0].size()
// Post: gamma y epsilon son el ratio gamma y epsilon de mat, respectivamente
void calcula_ratios(const Matriz& mat, Binario& gamma, Binario& epsilon) {
    int y = mat[0].size();
    for (int p = 0; p < y; ++p) {
        gamma [p] = bit_mas_comun (mat,p);
        if (bit_mas_comun (mat,p) == '0') epsilon [p] = '1';
        else epsilon[p] = '0';
}
}

int main() {
 int n, m;
    while (cin >> n >> m) {
        Matriz mat = leer_matriz(n, m);
        Binario gamma(m);
        Binario epsilon(m);
        calcula_ratios(mat, gamma, epsilon);
        escribir("Gamma", gamma);
        escribir("Epsilon", epsilon);
        cout << "Consumo: " << calcula_consumo(gamma, epsilon) << endl << endl;
    }
}
