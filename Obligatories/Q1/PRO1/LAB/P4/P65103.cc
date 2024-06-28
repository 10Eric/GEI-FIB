#include <iostream>
using namespace std;

int main() {
    int i, x, pos = 1;
    cin >> i;
    bool found = false;
    while (not found and cin >> x) {
        if (i == pos) {
            cout << "A la posicio " << i << " hi ha un " << x << "." << endl;
            found = true;
        }
        ++pos;
    }

    if (not found) cout << "Posicio incorrecta."<< endl;
}
