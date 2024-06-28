#include <iostream>
using namespace std;

int main() {
    int numeros, valor; cin >> numeros;


    for (int j = 0; j < numeros; ++j){
        cin >> valor;
        bool primo = true;
        for (int i = 2; i < valor; ++i) {
            if ((valor % i) == 0) {
                primo = false;

            }
        }
        if (primo == true) cout << valor << " es primer" << endl;
        if (primo == false) cout << valor << " no es primer" << endl;
    }
}
