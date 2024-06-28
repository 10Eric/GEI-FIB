#include <iostream>
using namespace std;


void swap2(int& a, int& b){
    int a1 = b,b1 = a;
    a = a1;
    b = b1;
}



int main() {
    int x,y;
    while (cin >> x >> y) {
        swap2(x, y);
        cout << x << " " << y << endl;
    }
}
