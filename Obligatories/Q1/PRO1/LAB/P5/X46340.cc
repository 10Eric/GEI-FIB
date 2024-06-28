#include <iostream>
using namespace std;


int sum_min_max(int x,int y,int z){
  int max=0, min=0,t=0;
  if (x >= y and x >= z) {
    max = x;
    if (y >=z) min = z;
    else min = y;
  }

  if (y >= x and y >= z) {
    max = y;
    if (x >=z) min = z;
    else min = x;
  }

   if (z >= x and z >= y) {
    max = z;
    if (x >=y) min = y;
    else min = x;
  }

  t= max + min;
  return t;
}

int main() {
  int x,y,z;
  while (cin >> x >> y >> z) cout << sum_min_max(x,y,z) << endl;
}
