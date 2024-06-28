#include <iostream>
#include <map>
#include <map>
using namespace std;

int main() {
	string name,action;
	int money;
	map <string,int> casino;
	
	while (cin >> name >> action) {	
		if (action == "enters") {
			if (casino.find(name) != casino.end())
				cout <<  name << " is already in the casino" << endl;
			else 
				casino[name] = 0;
		}

		else if (action == "leaves") {
			if (casino.find(name) == casino.end())
				cout << name << " is not in the casino" << endl;
			else{
				cout << name << " has won " << casino[name] << endl;
				casino.erase(name);
			}
		}

		else if (action == "wins") {
			cin >> money;
			if (casino.find(name) == casino.end()) 
				cout << name << " is not in the casino" << endl;				
			else 
				casino[name] += money;
		}
	}

	cout << "----------" << endl;
	for (pair <string,int> x:casino) {
		cout << x.first << " is winning " << x.second << endl;
	}
}