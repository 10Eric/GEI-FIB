#include <iostream>
using namespace std;

int main () {
    int h0,m0,h1,m1,h2,m2; cin >> h0 >> m0 >> h1 >> m1;
    if ((h1 < h0)) h1 = h1 + 24;

    h2 = (h1-h0);
    m2 = (m1-m0);

    if (m1 < m0) {
        m2 = 60 - (m0-m1);
        h2 = (h1-h0) -1;
        if (h2 < 0) h2 = h2 +24;
    }



    if (h2 < 10 and m2 < 10) cout << 0 << h2 << ':' << 0 << m2 <<endl;
    else if ((h2) < 10) cout << 0 << h2 << ':' << m2 <<endl;
    else if ((m2) < 10) cout << h2 << ':' << 0 << m2 <<endl;


    else cout << h2 << ':' << m2 <<endl;
}
