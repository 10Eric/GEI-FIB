#include <iostream>
using namespace std;

int main() {
    int a = 0;
    char x = 'x';

    while (x != '.') {
        cin >> x;

        if (x == 'a') {
            ++a;
        }
    }
    cout << a << endl;
}
