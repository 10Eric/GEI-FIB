#include <iostream>
using namespace std;


bool c_frac(int n1, int d1, int n2, int d2){
    double f1 ,f2;
    f2 = double(n2)/d2;
    f1 = double(n1)/d1;
    return f1 < f2;
}


int main() {
    int n1, d1, n2, d2;
    while (cin >> n1 >> d1 >> n2 >> d2) {
        if (c_frac(n1,d1,n2,d2)) cout << "si" << endl;
        else cout << "no" << endl;
    }   
}