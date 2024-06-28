#include <iostream>
using namespace std;

int main() {
	int f, c;
	cin >> f >> c;

	char x;
	int coin = 0;
	for (int i = 0; i < f; i++) {
		for (int j = 1; j <= c; j++) {
			cin >> x;
			if ((j%2) != 0) {
			coin += x-'0'; }
		}
	}
	cout << coin << endl;
}
