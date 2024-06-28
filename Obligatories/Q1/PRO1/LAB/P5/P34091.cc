#include <iostream>
using namespace std;

bool es_perfecte(int x){
  int divisor = 1;
    for (int i = 2; i*i <= x; ++i) {
        if (x%i == 0) divisor += i + x/i;
    }
    return (divisor == x and x != 0 and x != 1);
}

int main () {
  int x;
  while (cin >> x) cout << (es_perfecte(x) ? "true" : "false") << endl;
}
