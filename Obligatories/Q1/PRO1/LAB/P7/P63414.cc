#include <iostream>
#include <vector>

using namespace std;

int main() {
	int n;
	cin >> n;
    int year;
    const int max = 1000001000;
    const int min = 1000000000;
    vector <int > counter (max-min + 1, 0);
    for (int j = 0; j < n; ++j) {
        cin >> year;
        ++counter[year - min];
    }

    for (int i = 0; i < max-min + 1; ++i) {
        if (counter [i] != 0) {
        cout << min + i << " : "<< counter[i] << endl;
    }
    }
}
