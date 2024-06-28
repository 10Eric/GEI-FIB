#include <iostream>
using namespace std;

int main() {
	int x, y;
	bool first = true;

	while (cin >> y >> x) {
		if (not first) cout << endl;
		int c = 9;
		for (int i = 0; i < y; ++i) {
			for (int j = 0; j < x; ++j) {
				if (c == -1) c = 9;
				cout << c;
				--c;
			}

			cout << endl;
		}

		first = false;
	}
}
