#include <iostream>
using namespace std;

int main() {
    char x; cin>>x;

    if (int('A') <= int(x) and int(x) <=int('Z')) {
        cout << "majuscula" << endl; }

        else if (int('a') <= int(x) and int(x) <=int('z')) {
            cout << "minuscula" << endl; }

    if ((x == 'a') or (x == 'e')) {
        cout << "vocal" << endl; }

        else if ((x == 'i') or (x == 'o')) {
            cout << "vocal" << endl; }

        else if ((x == 'u') or (x == 'A')) {
            cout << "vocal" << endl; }

        else if ((x == 'E') or (x == 'I')) {
            cout << "vocal" << endl; }

        else if ((x == 'O') or (x == 'U')) {
            cout << "vocal" << endl; }

        else { cout << "consonant" << endl; }

}
