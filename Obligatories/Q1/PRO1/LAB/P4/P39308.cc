#include <iostream>
using namespace std;

int main() {
    int n;
    while (cin >> n){
        cout << "divisors de " << n << ":";

        int d;
        for (d = 1; d*d <= n; ++d) {
            if (n%d == 0) cout << " " << d;
        }
        --d;
        while (d > 0) {
            if (n%d == 0 and n/d != d) cout << " " << n/d;
            --d;
        }
        cout << endl;
    }
}
