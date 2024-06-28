#include <iostream>
#include <vector>

using namespace std;

bool lo_hay(const vector <int>& v, int xsum, int n) {
	for (int i = 0; i < n; ++i) {
		if (xsum-v[i] == v[i]) return true;
	}
	return false;
}

int main() {
	int n;
	while (cin >> n) {
		vector <int>list(n);
		int xsum = 0;
		for (int i = 0; i < n; ++i) {
			cin >> list[i];
			xsum += list[i];
		}
		if (lo_hay(list, xsum, n)) cout << "YES" << endl;
		else cout << "NO" << endl;
	}
}
