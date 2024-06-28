#include <iostream>
using namespace std;

int main() {
    int h, m;
    double m1, x, s1, f, s; cin >> x;

    h = (x / 3600);
    m1= (x / 3600);
    m = ((m1-h)*60);
    s1 = ((m1-h)*60);
    f = (s1-m);
    s= (f*60);


    cout << h << " " << m << " " << s << endl;
}
