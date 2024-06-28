#include <iostream>
#include <string>
#include <vector>
using namespace std;

typedef vector< vector<char> > MC;
typedef vector< vector<int> > MN;

int max_puntuacio(const MC& A, const MN& B, string s, bool& found) {
    int f = A.size();
    int c = A[0].size();
    int siz = s.size();
    int max = 0;

    for (int i = 0; i < f; ++i) {
        for (int j = 0; j < c; ++j) {
            if (f - i >= siz) {
                bool esta = true;
                int sum = 0;
                int aux = 0;
                while (esta and aux < siz) {
                    if (s[aux] == A[i + aux][j]) {
                        sum += B[i + aux][j];
                        ++aux;
                    }
                    else esta = false;
                }
                if (esta) {
                    if (sum > max)max = sum;
                    found = true;
                }
            }
            if (c - j >= siz) {
                bool esta = true;
                int sum = 0;
                int aux = 0;
                while (esta and aux < siz) {
                    if (s[aux] == A[i][j + aux]) {
                        sum += B[i][j + aux];
                        ++aux;
                    }
                    else esta = false;
                }
                if (esta) {
                    if (sum > max)max = sum;
                    found = true;
                }
            }
        }
    }
    return max;
}

int main() {
    int c, f;
    while (cin >> f >> c) {
        MC A(f, vector<char> (c));
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) cin >> A[i][j];
        }

        MN B(f, vector<int> (c));
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) cin >> B[i][j];
        }

        int n;
        cin >> n;
        vector <string> s(n);
        for (int i = 0; i < n; ++i) cin >> s[i];

        for (int i = 0; i < n; ++i) {
            bool found = false;
            int max = max_puntuacio(A, B, s[i], found);
            if (not found) cout << "no" << endl;
            else cout << max << endl;
        }
    }
}
