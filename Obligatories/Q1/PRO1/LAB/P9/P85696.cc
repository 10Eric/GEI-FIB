#include <typeinfo>
#include <iostream>
using namespace std;

    struct Racional {
         int num, den;
     };

int mcd(int a, int b){
  if(a < b){
    int aux = a;
    a = b;
    b = aux;
  }

  if (a < 0) a *= -1;
  if (b < 0) b *= -1;

  int r = a%b;
  while(r > 0){
    a = b;
    b = r;
    r = a%b;
  }

  return b;
}


Racional racional(int n, int d){
  int maxcd = 1;
  if(n != 0) maxcd = mcd(n,d);

  n = n / maxcd;
  d = d / maxcd;

  if (n == 0) d = 1;
  if (d < 0){
    d *= -1;
    n *= -1;
  }

  Racional resultat;
  resultat.num = n;
  resultat.den = d;

  return resultat;
}


int main() {

  int num, den;
  while (cin >> num >> den) {
    Racional r = racional(num, den);
    cout << r.num << " " << r.den << endl;
  }
}
