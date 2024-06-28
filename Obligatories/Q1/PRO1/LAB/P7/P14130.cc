#include <iostream>
#include <vector>
using namespace std;

int main () {
    int n; cin >> n;
    int numero;
    int cunt = 0;
    vector<int>contador(n);

    for (int i = 0; i < n; ++i) {
        cin >> numero;
        contador [i] =  numero;
    }

    for (int j = 0; j < n-1; ++j) {
        if (contador [j] == contador [n-1]) ++cunt;

    }

    cout << cunt << endl;
}




