#include <iostream>
using namespace std;

struct Fraccio {
	int num, den;
};

int mcd(int a, int b) {
	while (a != b) {
		if (a > b)
			a -= b;
		else
			b -= a;
	}
	return a;
}

void reduir(Fraccio& a) {
	if (a.den > 1) {
		int e = mcd(a.num, a.den);
		a.num /= e;
		a.den /= e;
	}
}

Fraccio suma(const Fraccio& x, const Fraccio& y) {
	Fraccio a;
	a.num = x.num * y.den+y.num * x.den;
	a.den = x.den * y.den;
	return a;
}

int main() {
	Fraccio primera, acumulada;
	acumulada.num = 0;
	acumulada.den = 1;
	char useless;
	bool keep = true;
	while (keep) {
		cin >> primera.num >> useless >> primera.den >> useless;
		if (useless == '=')
			keep = false;
		reduir(primera);
		acumulada = suma(primera, acumulada);
		reduir(acumulada);
	}
	cout << acumulada.num << "/" << acumulada.den << endl;
}
