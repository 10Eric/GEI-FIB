#include <iostream>
using namespace std;

int main() {
    int n; cin >> n;

    for (int i = 0; i < n; ++i) {
        int num; cin >> num;
        int x = 2;
        cout << num;
        bool prim = true;

        while (x*x <= num and prim) {
            if (num%x == 0) prim = false;
            ++x;
        }
        if (num < 2) cout << " no es primer" << endl;
        else if (prim) cout << " es primer" << endl;
        else cout << " no es primer" << endl;
    }
}
