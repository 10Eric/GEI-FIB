#include <iostream>
#include <typeinfo>
#include <vector>
using namespace std;

int posicio_maxim(const vector<double>& v, int m){
    double max = v[0];
    int pos = 0;

    for (int q = 1; q <= m; ++q) {
        if (v[q] > max) {
            max = v[q];
            pos = q;
        }
    }

    return pos;
}


int main()
{
    int m, n;
    while (cin >> m >> n) {
        vector<double> V(n);
        for (int i = 0; i < n; ++i)
            cin >> V[i];
        cout << posicio_maxim(V, m) << endl;
    }
    return 0;
}
