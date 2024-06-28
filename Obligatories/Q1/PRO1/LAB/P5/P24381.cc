#include <iostream>
using namespace std;


void cross(int n, char c){
  char blank = ' ';
  for (int i = 0; i < n; ++i) {
    if (i == (n/2)) {
      for (int j  = 0; j < n; ++j) cout << c;
      cout << endl;
    }
    else {
      for (int l = 0; l < (n/2); ++l) cout << blank;
      cout << c << endl;
    }
  }
}



int main() {
  int n;
  char c;
  while (cin >> n >> c) cross(n, c);
}
