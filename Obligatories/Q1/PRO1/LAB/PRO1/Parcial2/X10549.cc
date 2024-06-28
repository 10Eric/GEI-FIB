#include <iostream>
using namespace std;

int elimina_zeros(int x) {
    if (x < 10) return x;
    else if (x%10 == 0) return elimina_zeros(x/10) + x%10;
    else return 10*elimina_zeros(x/10) + x%10;
}

int main () {
    int n;
    while (cin >> n) {
        int m = elimina_zeros(n);
        cout << m << endl;
    }
}
