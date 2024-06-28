#include <iostream>
using namespace std;

int main() {
	int d,n,t, x, r = 0, pos = 0;
	cin >> d >> n >> t;

	for (int i = 0; i < t; i++) {
        cin >> x;
        r += x-d;

        if ((n+r) > 0) {
            ++pos;
        }
    }
    cout << pos << endl;
}
