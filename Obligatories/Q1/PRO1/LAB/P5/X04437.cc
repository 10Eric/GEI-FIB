#include <iostream>
#include <cmath>
using namespace std;


double dist_or(double x,double y){
  double t;
  x = x*x;
  y = y*y;
  t = sqrt(x+y);
  return t;
}


int main() {
  double x,y;
  while (cin >> x >> y) cout << dist_or(x,y) << endl;
}
