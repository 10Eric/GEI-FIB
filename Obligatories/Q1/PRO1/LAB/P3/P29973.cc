#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;
    int nblanks = n - 1;
    for (int i = 1; i <= n; ++i) {
        for (int j = nblanks + 1; j <= n; ++j) cout << '*';
        cout << endl;
        --nblanks;
            }
}
