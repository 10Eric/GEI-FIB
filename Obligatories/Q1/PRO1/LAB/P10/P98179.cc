#include <iostream>
#include <vector>
using namespace std;

void insereix(vector<double>& v){
	int tam = v.size();
	for (int i = 1; i < tam; ++i)  {
		double x = v[i];
		int j = i;
		while (j > 0 && x < v[j - 1]) {
			v[j] = v[j - 1];
			--j;
		}
		v[j] = x;
	}
}

int main()
{
    cout.setf(ios::fixed, ios::floatfield);
    cout.precision(4);
    int n;
    while (cin >> n) {
        vector<double> V(n);
        for (int i = 0; i < n; ++i) {
            cin >> V[i];
        }
        insereix(V);
        for (int i = 0; i < n; ++i) {
            cout << " " << V[i];
        }
        cout << endl;
    }
    return 0;
}
