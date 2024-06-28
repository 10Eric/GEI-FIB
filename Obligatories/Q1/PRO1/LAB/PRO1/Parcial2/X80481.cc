#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
    int n; cin >> n;
    for (int w = 0; w < n; ++w) {
        int v; cin >> v;
        int v1; cin >> v1;
        int counter = 0;
        int min = v;

        if (v1 == 0) cout << 0 << endl;

        else {

        if (v < v1 and v1 != 0) {
            ++counter;
        }

        v = v1;


        while (cin >> v1 and v1 != 0) {
            bool primera = false;
            bool segona = false;

            if (min > v) {
                primera = true;
                min = v;

                if (v < v1) {
                segona = true;
                min = v;
                }

                else min = v1;
            }

            if (primera and segona) ++counter;
            v = v1;
        }

        cout << counter << endl;
    }
}
}


