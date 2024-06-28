#include <iostream>
using namespace std;

int main () {
    int n; cin >> n;
    for (int f = 0; f < n; ++f) {
        int contador = 0;
        string carta1;
        string carta2;
        cin >> carta1 >> carta2;
        while (carta2 != "end") {
            if (carta2 != carta1) {
                if (carta2 != "multicolor") {
                    ++contador;
                    carta1 = carta2;
                }
            }
            cin >> carta2;
        }

        cout << contador << endl;
    }
}
