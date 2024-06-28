#include <iostream>
using namespace std;

int main () {
    int x, x1, x2, s1, s2, s3; cin >> x;

        s1 = (x % 10);
        x1= x/10;
        s2 = (x1 % 10);
        x2= x1/10;
        s3 = (x2 % 10);
        cout << s1 + s2 + s3 << endl;
}
