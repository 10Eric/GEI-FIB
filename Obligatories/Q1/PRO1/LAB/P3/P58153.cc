#include <iostream>
using namespace std;

int main() {
	cout.setf(ios::fixed);
	cout.precision(10);
	int n, m;

	while (cin >> n >> m) {
		if (n == m) cout << double(0) << endl;
		else {
			double r = 0.0;
			for (double i = m+1; i <= n; ++i) r += 1 / i;
			cout << r << endl;
		}
	}
}
