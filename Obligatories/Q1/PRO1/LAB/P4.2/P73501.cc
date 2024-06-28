#include <iostream>
using namespace std;

int main () {
    int n; cin >> n;
    for (int i = 0; i < n; ++i){
        int m1,m2, counter = 0; cin >> m1;

        while (m1 > 0) {
            cin >> m2;
            if (m2 > m1) ++counter;
            m1= m2;

        }

        cout << counter << endl;
    }
}
