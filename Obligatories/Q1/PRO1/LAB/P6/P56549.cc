#include <iostream>
using namespace std;

void binaria(int n) {
    if (n < 2) cout << n;
    else {
        binaria(n/2);
        cout << n%2;
    }
}

void octal(int n) {
    if (n < 8) cout << n;
    else {
        octal(n/8);
        cout << n%8;
    }
}

void hex (int n) {
    int r = n%16;
    char c = 'A';
    if (n < 16) {
        if (n < 10) cout << n;
        else {
            c += n-10;
            cout << c;
        }
    }
    else {
        hex(n/16);
        if (r < 10) cout << r;
        else {
            c += r-10;
            cout << c;
        }
    }
}

int main () {
	int n;
    while(cin >> n) {
        cout << n << " = ";
        binaria(n);
        cout << ", ";
        octal(n);
        cout << ", ";
        hex(n);
        cout << endl;
    }
}



