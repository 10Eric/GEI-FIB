#include <iostream>
#include <vector>
using namespace std;

struct Assignatura {
	string nom;
	double nota;
};

struct Estudiant {
	string nom;
	int dni;
	vector <Assignatura>ass;
};

double nota(const vector <Estudiant>& stu, int dni, string nom) {
	bool f = true;
	for (int i = 0; i < stu.size() and f; ++i) {
		Estudiant st = stu[i];
		if (st.dni == dni) {
			for (int s = 0; s < st.ass.size(); ++s) {
				if (st.ass[s].nom == nom && st.ass[s].nota >= 0)
					return st.ass[s].nota;
			}
			f = false;
		}
	}
	return -1;
}

double mitjana(const vector <Assignatura>& ass) {
	double m = 0;
	int c = 0;
	for (int i = 0; i < ass.size(); ++i) {
		if (ass[i].nota >= 0) {
			++c;
			m += ass[i].nota;
		}
	}
	return m / c;
}

void compta(const vector <Estudiant>& stu, int dni, string nom, int& counter) {
	counter = 0;
	double n = nota(stu, dni, nom);
	for (int i = 0; i < stu.size(); ++i) {
		Estudiant s = stu[i];
		if (mitjana(s.ass) > n)
			counter++;
	}
}

int main() {
	int n;
	cin >> n;
	vector <Estudiant>v(n);
	int j = 0;
	while (n--) {
		cin >> v[j].nom >> v[j].dni;
		int ns;
		cin >> ns;
		int i = 0;
		vector <Assignatura>vs(ns);
		while (i < ns) {
			cin >> vs[i].nom >> vs[i].nota;
			++i;
		}
		v[j].ass = vs;
		++j;
	}
	int i;
	string s;
	while (cin >> i >> s) {
		int c;
		compta(v, i, s, c);
		cout << c << endl;
	}
}
