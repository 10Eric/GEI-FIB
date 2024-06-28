#include <iostream>
using namespace std;

int main() {
	int n, x;
	cin >> n;

	for (int i = 2; i <= 16; ++i) {
		int count = 0;
		x = n;
		while (x != 0) {
			x = x / i;
			++count;
		}
		cout << "Base " << i << ": " << count << " cifras." << endl;
	}
}
