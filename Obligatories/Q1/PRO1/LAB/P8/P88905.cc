#include <iostream>
#include <vector>
using namespace std;

typedef vector<vector<int> > Matriu;

Matriu producte(const Matriu& A, const Matriu& B){
    size_t p = A.size(), q = B[0].size(), r = B.size();
    Matriu prod (p, vector <int> (q,0));
    for (size_t i=0; i < p; ++i)
        for (size_t j=0; j < q; ++j)
            for (size_t k = 0; k < r; ++k)
                prod[i][j]+=A[i][k]*B[k][j];
    return prod;
}

int main(){
    int p, q, r;
    while (cin >> p >> q >> r) {
        Matriu A(p, vector<int>(q));
        Matriu B(q, vector<int>(r));
        for (int i = 0; i < p; ++i) {
            for (int j = 0; j < q; ++j) {
                cin >> A[i][j];
            }
        }
        for (int i = 0; i < q; ++i) {
            for (int j = 0; j < r; ++j) {
                cin >> B[i][j];
            }
        }
        Matriu C = producte(A, B);
        for (int i = 0; i < p; ++i) {
            for (int j = 0; j < r; ++j) {
                cout << C[i][j] << " ";
            }
            cout << endl;
        }
        cout << endl;
    }
}
