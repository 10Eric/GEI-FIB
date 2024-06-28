#include <typeinfo>
#include <iostream>
using namespace std;

struct Racional {
    int num, den;
};

int maximo_comun_divisor(int a, int b) {
    int temporal;
    while (b != 0) {
        temporal = b;
        b = a % b;
        a = temporal;
    }
    return a;
}

int minimo_comun_multiplo(int a, int b) {
    return (a * b) / maximo_comun_divisor(a, b);
}

Racional suma_resta (const string operacion, Racional& r, Racional& t) {
    if (r.den != t.den) {
        int n = minimo_comun_multiplo (r.den, t.den) /r.den;
        int w = minimo_comun_multiplo (r.den, t.den) /t.den;
        r.num *= n;
        r.den *= n;
        t.num *= w;
        t.den *= w;
    }


    Racional resultat;
    if (operacion == "suma") resultat.num = r.num + t.num;
    else resultat.num = r.num - t.num;

    return resultat;
    }



int main() {
    Racional r;
    int num, den; cin >> num >> den;
    r.num = num;
    r.den = den;
    cout << r.num << '/' << r.den << endl;
    Racional t;
    string operacion;
    while (cin >> operacion) {
        cin >> num >> den;
        t.num = num;
        t.den = den;
        Racional fin;

        if (operacion == "suma" or operacion == "resta") {
            fin = suma_resta (operacion, r, t);
            if (fin.num % fin.den == 0) cout << fin.num/fin.den << endl;
            else cout << fin.num << '/' << fin.den << endl;
        }

        else if (operacion == "divideix" or operacion == "multiplica") {
            if (operacion == "multiplica") {
            fin.num = r.num * t.num;
            fin.den = r.den * t.den;
            }
            else {
            fin.num = r.num * t.den;
            fin.den = r.den * t.num;
            }

            if (fin.num % fin.den == 0) cout << fin.num/fin.den << endl;
            else cout << fin.num << '/' << fin.den << endl;

        }

    }
}




