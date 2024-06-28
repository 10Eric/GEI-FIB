#include <iostream>
#include <vector>
#include <cmath>
using namespace std;


int avalua(const vector<int>& P, int x){
    int n = P.size(), aux, sum = 0;
	for (int i = 0; i < n; ++i) {
		aux = pow(x, i);
		sum += (P[i] * aux);
	}
	return sum;
}


int main()
{
    int n;
    while (cin >> n) {
        vector<int> P(n);
        for (int i=0; i<n; ++i) cin >> P[i];
        int x;  cin >> x;
        cout << avalua(P,x) << endl;
    }
}
