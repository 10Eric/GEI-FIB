#include <iostream>
using namespace std;

int main() {
    char y;
    int a = 0;

    while (a >= 0 and cin >> y) {
        if (y == '(' ) ++a;
        else {
            --a;
        }
    }

    if (a == 0) cout << "si" << endl;
    else {
        cout << "no" << endl;
    }
}


