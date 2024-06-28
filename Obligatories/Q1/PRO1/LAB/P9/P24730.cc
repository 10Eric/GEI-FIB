#include <typeinfo>
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

struct Resultats {
    string prof;
    int car;
    int pas;
};

bool comp (const Resultats& a, const Resultats& b) {
    if (a.car != b.car) return a.car > b.car;
    if (a.pas != b.pas) return a.pas > b.pas;
    if (a.prof.size() != b.prof.size()) return a.prof.size() < b.prof.size();
    return a.prof < b.prof;
}

int main () {
    int n; cin >> n;
    for (int w = 0; w < n; ++w) {
        int q; cin >> q;
        vector<Resultats> llista (q);
        for (int r = 0; r < q; ++r) {
            cin >> llista [r].prof >> llista [r].car >> llista [r].pas;
        }

        sort (llista.begin(), llista.end(), comp);
        for (int t = 0; t < q; ++t) {
            cout << llista [t].prof << endl;
        }
        cout << endl;
    }
}

