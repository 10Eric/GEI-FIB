#include <iostream>
using namespace std;

void aval () {
    string s;
    bool trobat = false;
    if (cin >> s and not trobat) {
        if (s == "fi") trobat = true;
        else {
        aval();
        cout << s << endl;
    }
}
}

int main () {
	aval();
}
