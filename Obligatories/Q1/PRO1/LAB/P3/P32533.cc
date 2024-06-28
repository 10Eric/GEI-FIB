#include <iostream>
using namespace std;

int main() {
int n;
cin >> n;
int suma = n - 1;
int asteriscos = 0;

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= suma; ++j) cout << '+';
        cout << '/';
        for (int j = suma +2; j <= n; ++j) cout << '*';
        cout << endl;
        --suma;
        ++asteriscos;
            }
}
