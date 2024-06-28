#include <iostream>
using namespace std;

int main() {
	int x, y;
	cin >> x;

	if (x == 0) cout << x;

	while (x != 0) {
		y = x % 2;
		x = x / 2;
		cout << y;
	}
	cout << endl;
}
