#include <iostream>
#include <vector>
#include <typeinfo>
using namespace std;

typedef vector<int> Fila;
typedef vector<Fila> Matriu;

Matriu read_matriu (int n){
    Matriu llegir (n, Fila(n));
    for (int y = 0; y < n; ++y) {
        for (int p = 0; p < n; ++p) cin >> llegir[y][p];
    }
    return llegir;
}


bool is_hankel(const Matriu& res) {
    int n = res.size();
    bool res1 = true;
    bool res2 = true;
    bool res3 = true;
    int fila = 0;
    int e = 0;
    while (e < n and res1 == true){
        if (res[fila][e] != res[e][fila]) res1 = false;
        ++e;
    }

    fila = n-1;
    int t = 1;
    while (t < n and res2 == true){
        if (res[fila][t] != res[t][fila]) res2 = false;
        ++t;
    }
    fila = 0;
    int u = n-1;
    int x = res[fila][u];
    while (u >= 0  and res3 == true){
        if (x != res[fila][u]) res3 = false;
        --u;
        ++fila;
    }

    if (res1 == true and res2 == true and res3 == true) return true;
    return false;
}


int main (){
    int a; cin >> a;
    for (int m = 0; m < a; ++m) {
        int n; cin >> n;
        Matriu resultat = read_matriu (n);
        if (is_hankel(resultat)) cout << "yes" << endl;
        else cout << "no" << endl;
    }
}

