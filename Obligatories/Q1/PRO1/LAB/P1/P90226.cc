#include <iostream>
using namespace std;

int main() {
    string x ; cin >> x;
    string y ; cin >> y;

    if (x > y) {
        cout << x << " " << ">" << " " << y << endl;}

    else if (x < y) {
        cout << x << " " << "<" << " " << y << endl;}

    else if (x == y) {
        cout << x << " " << "=" << " " << y << endl;}

}
