#include <iostream>
using namespace std;

int main() {
	int x, num, r = 0;
	cin >> x;
	num = x;

	while (x != 0) {
		r += x % 10;
		x /= 100;
	}

	string b;
	if (r % 2 == 0) b = "";
	else b = " NO";
	cout << num << b << " ES TXATXI" << endl;
}
