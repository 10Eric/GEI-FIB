#include <iostream>
#include <vector>
#include <cstdlib>  // para srand
#include <ctime>
using namespace std;

int main() {
    srand(static_cast<unsigned int>(time(0)));
    vector<int> numeros(80);
    bool primer = true;
    for (int i = 0; i < 80; ++i) numeros[i] = rand()%1000;
    for (int i = 0; i < 80; ++i) {
        if (not primer) cout << "  ";
        cout << numeros[i];
        if (primer) primer = false;
    }
    cout << endl;
}
