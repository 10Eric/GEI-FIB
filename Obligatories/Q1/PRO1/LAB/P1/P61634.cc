#include <iostream>
using namespace std;

int main () {
    int x; cin >> x;

    if ((x > 1800) and (x < 9999) and ((x % 4) == 0) and ((x % 100) != 0)) {
        cout << "YES" << endl; }

        else if ((x > 1800 and x < 9999) and (((x / 100) % 4) == 0) and ((x % 100) == 0)) {
            cout << "YES" << endl; }

    else (cout << "NO" << endl);

}
