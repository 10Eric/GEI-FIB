#include <iostream>
using namespace std;

int main () {
    int n; cin >> n;
    int blanks1 = 1, blanks2 = (n-1);

    for (int i = 0; i < n; ++i) {
        cout << '*';
    }
    cout << endl;

    for (int j = 2; j < n; ++j) {

        for (int w = blanks1; w < j; ++w) {
            cout << ' ';
        }

        cout << '*';

        for (int r = blanks2; r > 2; --r){
            cout << ' ';
        }

        blanks2 = blanks2 -1;

        cout << '*' << endl;

    }

    for (int m = 0; m < (n-1); ++m) {
        cout << ' ';
    }

    cout << '*' << endl;
}
