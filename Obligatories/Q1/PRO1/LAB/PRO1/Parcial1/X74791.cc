#include <iostream>
using namespace std;

int main (){
    int n = 0, p,u,v = 0;

    cin >> n;

    while (n != -1) {
        p = n%10;
        u = n;

        while ((u/10) != 0) {
            u = u/10;
        }

        if (p == u) {
            cout << n << endl;
            ++v;
        }
        cin >> n;
    }
    cout << "total: " << v << endl;
}
