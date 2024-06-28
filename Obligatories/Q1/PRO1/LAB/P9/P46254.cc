#include <typeinfo>
#include <iostream>
#include <cmath>
using namespace std;

struct Punt {
    double x, y;
};

double distancia(const Punt& a, const Punt& b){
   return sqrt(pow(a.x-b.x,2) + pow(a.y-b.y,2));
}


int main() {
  cout.setf(ios::fixed);
  cout.precision(6);

  double x1, y1, x2, y2;
  while (cin >> x1 >> y1 >> x2 >> y2) {
    Punt a, b;
    a.x = x1;
    a.y = y1;
    b.x = x2;
    b.y = y2;
    cout << distancia(a, b) << endl;
  }
}
