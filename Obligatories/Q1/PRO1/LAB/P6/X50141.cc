#include <iostream>
using namespace std;

int mx(int x) {
    int res = 0;
    while (x>0) {
      if (res < x%10) res = x%10;
        x/=10;
    }
    return res;
}

int engreixa(int x){
  if (x == 0) return 0;
  return mx(x) + 10*engreixa(x/10);
}



int main() {
  int x;
  while (cin >> x) cout << engreixa(x) << endl;
}
