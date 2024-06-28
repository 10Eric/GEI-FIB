#include <iostream>
using namespace std;

int main () {
    int h0,m0,r,h2,m2; cin >> h0 >> m0 >> r;

    while (r > 60) {
        r = r-60;
        h0= h0+1;
    }

    if ((m0+r) >= 60) {
        m2 = m0+r -60;
        h2 = h0+1;
    }

    else {
        m2 = m0+r;
        h2 = h0;
    }

    while (h2 >= 24) {
        h2 = h2-24;
    }

    if (h2 < 10) cout << 0 << h2 << ':';
    else cout << h2 << ':';

    if (m2 < 10) cout << 0 << m2 << endl;
    else cout << m2 << endl;

}
