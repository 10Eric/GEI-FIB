#include <iostream>
#include <vector>
using namespace std;


double producte_escalar(const vector<double>& u, const vector<double>& v){
    int n = u.size();
    double aux, sum = 0;
	for (int i = 0; i < n; ++i) {
		aux = u[i] * v[i];
		sum += aux;
	}
	return sum;
}


int main() {
    cout.setf(ios::fixed, ios::floatfield);
    cout.precision(4);
    int n;
    while (cin >> n) {
        vector<double> u(n);
        vector<double> v(n);
        for (int i = 0; i < n; ++i) cin >> u[i];
        for (int i = 0; i < n; ++i) cin >> v[i];
        cout << producte_escalar(u, v) << endl;        
    }
    return 0;
}
