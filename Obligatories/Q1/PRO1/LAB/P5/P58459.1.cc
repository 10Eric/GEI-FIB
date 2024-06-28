#include <iostream>
using namespace std;


bool es_any_de_traspas(int any){
    if (((any % 4) == 0) and ((any % 100) != 0)) return true;
    else if ((((any / 100) % 4) == 0) and ((any % 100) == 0)) return true;
    else return false;
}

bool es_data_valida(int d, int m, int a) {
  bool verdad = true;
    if (m == 2) {
          if (es_any_de_traspas(a)) {
            if (d > 0 and d <= 29) return verdad = true;
            else return verdad = false;
          }

          else if (not es_any_de_traspas(a)) {
            if (d > 0 and d <= 28) return verdad = true;
            else return verdad = false;
          }
      }
    else if (m > 0 and m <= 12) {
            if (d > 0 and d <= 31) return verdad = true;
            else return verdad = false;
      }

    else return verdad = false;
}

int main() {
  int d, m, a;
  while (cin >> d >> m >> a)
    cout << (es_data_valida(d, m, a) ? "true" : "false") << endl;
}
