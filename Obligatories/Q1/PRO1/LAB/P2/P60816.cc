#include <iostream>
using namespace std;

int main() {
	int x, y;
	cin >> x;

	if (x == 0) cout << x;

	while (x != 0) {
		y = x % 16;
		x /= 16;

		if (y >= 10) cout << char('A'+y-10);
		else cout << y;
	}
	cout << endl;
}
