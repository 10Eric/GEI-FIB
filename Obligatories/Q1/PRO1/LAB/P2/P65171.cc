#include <iostream>
using namespace std;

int main() {
	cout.precision(2);
	cout.setf(ios::fixed);

    double n, x, y, z;
    cin >> n;
    cin >> x;
    z = x * x;

    for (int i = 1; i < n; ++i) {
        cin >> y;
        x = x + y;
        z = z + (y*y);
        }
    cout << ( ((1/(n-1)) * z) - ((1/(n*(n-1))) * (x * x))) << endl;
}
