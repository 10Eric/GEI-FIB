#include <iostream>
#include <map>
using namespace std;

const int LIMIT = 100000000;

int main() {
    int n, x, y;
    map<int, int> collatz; //{n,rep}
    while (cin >> x >> y >> n) {
        bool repe = false;
        int result = 0;
        while (not repe && n <= LIMIT) {
            if (collatz.find(n) != collatz.end()) {
                result = result - collatz[n];
                repe = true;
            }
            result++;
            collatz[n] = result;
            n = (n%2) ? 3*n+y : n/2+x;
        }
        collatz.clear();
        cout << (repe ? result : n)  << endl;
    }
}