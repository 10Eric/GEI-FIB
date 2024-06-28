#include <iostream>
#include <vector>
using namespace std;

bool es_primer(int n) {
	if (n == 0 or n == 1) return false;
	else {
		for (int i = 2; i * i <= n; ++i) {
			if (n % i == 0) return false;
		}
		return true;
	}
}

int main() {
	int n;
	while (cin >> n) {
		vector <int>v(n);
		for (int i = 0; i < n; ++i) cin >> v[i];
		int i = 0;
		bool trobat = false;
		while (not trobat and i < n) {
			int j = 0;
			while (not trobat and j < n) {
				if (i != j) {
					if (es_primer(v[i]+v[j])) trobat = true;
				}
				++j;
			}
			++i;
		}
		if (trobat) cout << "yes" << endl;
		else cout << "no" << endl;
	}
}
