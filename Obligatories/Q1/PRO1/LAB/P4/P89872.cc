#include <iostream>
using namespace std;

int main() {
    string ultima = " ";
    string penultima = " ";
    string n;

    while (cin >> n) {
        if (ultima < n) {
            penultima = ultima;
            ultima = n;
        }
        else if (penultima < n and n < ultima) {
            penultima = n;
        }
    }
    cout << penultima << endl;
}
