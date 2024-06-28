#include<iostream>
#include <vector>
using namespace std;

void imprimir(vector<int>&Res) {
    int op = Res.size();
    for (int w = 0; w < op; ++w) {
        cout << Res[w];
    }
    cout << endl;
}

void test (vector<int> Res, bool adjyacente, vector<int> fin, int pos) {
    int pp =  Res.size()/3;
    if (pos < Res.size()) {
        for(int qwe = 1; qwe <= 3; ++qwe) {
            if (fin[qwe] < pp and (not adjyacente or (adjyacente and Res[pos-1] != qwe))) {
                Res[pos] = qwe;
                ++fin[qwe];
                if(Res[pos] == Res[pos-1]) adjyacente = true;
                test(Res,adjyacente,fin,pos+1);
                if (pos == Res.size()-1 and adjyacente) imprimir(Res);
                --fin[qwe];
                if(Res[pos] == Res[pos-1])  adjyacente = false;
            }

        }
    }
}

void testos(int n) {
    int r = 3*n;
    bool adjyacente = false;
    for (int q = 0; q < 3; ++q) {
        vector<int> Res(r);
        vector<int> fin(4,0);
        Res[0] = q+1;
        fin[q+1] = 1;
        test(Res,adjyacente,fin,1);
    }

}

int main() {
    int n;
    while (cin >> n) {
        testos(n);
        cout << string(10,'*') << endl;
    }
}
