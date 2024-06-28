#include <iostream>
#include <set>
using namespace std;

int main() {
	set <string> words;
	auto median = words.end();
	string word;
	bool odd = 0;
	while (cin >> word and word != "END") {
		auto it = words.insert(word).first;
		odd = not odd;
		if (median == words.end())
			median = it;
		else if (odd and word > *median)
			++median;
		else if (not odd and word < *median)
			--median;
		cout << *median << endl;
	}

}