#include <iostream>
using namespace std;

int main() {
	int x, y, c = 0;
	cin >> x;
	while (cin >> y) {
		if ((y % x) == 0) ++c;
	}
	cout << c << endl;
}
