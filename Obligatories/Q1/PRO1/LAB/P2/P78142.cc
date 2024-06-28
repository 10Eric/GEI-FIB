#include <iostream>
using namespace std;

int main() {
	cout.precision(2);
	cout.setf(ios::fixed);

    double x, t = 0, c = 0;


    while (cin >> x) {
        t += x;
		++c;
	}

	cout << t / c << endl;
}

