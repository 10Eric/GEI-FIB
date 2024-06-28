#include <iostream>
#include <map>
using namespace std;

int main() {
    
    map <string,int> bag;
    string op;
    string word;
    while (cin >> op) {
    	if (op == "minimum?") {
    		if (bag.empty())
    			cout << "indefinite minimum" << endl;
    		else
    			cout << "minimum: " << bag.begin() -> first<< ", " << bag.begin()->second << " time(s)" << endl;
    	}

    	else if (op == "delete") {
    		cin >> word;
    		bag[word] > 1 ? --bag[word] : bag.erase(word);
    	}

    	else if (op == "store") {
    		cin >> word;
    		bag.find(word) != bag.end() ? ++bag[word] : bag[word] = 1;
    	}

    	else {
    		if (bag.empty())
    			cout << "indefinite maximum" << endl;
    		else {
    			map<string, int>::iterator it = bag.end();
            	--it;
    			cout << "maximum: " << it -> first<< ", " << it -> second << " time(s)" << endl;
    		}
    	}	
    }
}
