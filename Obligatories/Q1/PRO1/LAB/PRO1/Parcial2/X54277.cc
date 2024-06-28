#include <iostream>
using namespace std;

bool is_two_three_balanced(int n) {
    if (n%3 != 0) return n%2 != 0;
    else if (n%2 != 0) return false;
    else return is_two_three_balanced(n/6);

}


int main() {
    int n;
    while (cin >> n) {
        if (is_two_three_balanced(n)) cout << "yes";
        else cout << "no";
        cout << endl;
    }
}
