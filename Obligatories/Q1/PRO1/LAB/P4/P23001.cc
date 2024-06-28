#include <iostream>
#include <string>
using namespace std;

int main() {
    string primera;
    cin >> primera;
    string n;
    int max = 1;
    int cont = 1;
    while (cin >> n) {
        if (primera != n) cont = 0;
        else {
            ++cont;
            if (cont > max) max = cont;
        }
    }
    cout << max << endl;
}
