#include <iostream>
#include <vector>
using namespace std;

int main () {
    int n;
    while (cin >> n) {
        int numero;
        vector<int>llista (n);
        for (int i = 0; i < n; ++i) {
            cin >> numero;
            llista [i] = numero;
        }

        for (int j = n-1; j >= 0; --j) {
            cout << llista [j];
            if (j > 0) cout << ' ';
        }
        cout << endl;
    }
}
