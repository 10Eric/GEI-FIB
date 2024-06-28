#include <iostream>
using namespace std;

int main() {
    int a , d , h , m , s ; cin >> a >> d >> h >> m >> s ;
    int d1 , h1 ,m1 ,s1 ,stotal;

    d1 = (a*365);
    h1 = ((d1+d) * 24);
    m1 = ((h1+h) * 60);
    s1 = ((m1+m) * 60);
    stotal = s1 + s;

    cout << stotal << endl;

}

