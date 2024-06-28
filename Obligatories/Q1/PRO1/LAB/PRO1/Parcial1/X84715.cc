#include <iostream>
using namespace std;

int main () {
    int n,r=0; cin >> n;
    int blank1 = 0,blank2 = (n*2)-2;

    for (int j = 0; j < (n-1); ++j) {
        for (int i = blank1; i < j; ++i){
            cout << ' ';
        }

        cout << 'V';


        while (blank2 > r){
            cout << ' ';
            blank2 = blank2 -2;
        }

         r = j-1;

        cout << 'V' << endl;

    }

    for (int i = blank1; i < (n-1); ++i){
            cout << ' ';
        }
        cout << 'V' << endl;
}
