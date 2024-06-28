#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
    cout.setf(ios::fixed);
    cout.precision(2);

    int any; cin >> any;
    double dada; cin >> dada;
    string paraula; cin >> paraula;
    paraula [0] = paraula [0] - int ('a' - 'A');

    for (int a = 0; a < paraula.size (); ++a) {
        if (paraula [a] == '_') cout << ' ';
        else cout << paraula [a];
    }

    if (paraula [0] == 'M') dada = dada * 0.31;
    else if (paraula [0] == 'P') dada = dada * 0.27;
    else if (paraula [0] == 'K') dada = dada * 0.07;
    else if (paraula [0] == 'G') {
        if (paraula [1] == 'r') dada = dada * 0.19;
        else dada = dada * 0.16;
    }


    cout << " produced " << dada << " CO2 Gt in " << any << endl;

}





