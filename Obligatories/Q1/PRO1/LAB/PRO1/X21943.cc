#include <iostream>
#include <vector>
#include <typeinfo>
#include <algorithm>
using namespace std;

struct Student {
    string name;
    int right;
    int wrong;
};

vector <int> read_right_answers (int r) {
    vector <int> pim (r);
    for (int s = 0; s < r; ++s) {
        cin >> pim [s];
    }
    return pim;
}

Student get_info_std (const vector<int>& answer) {
    int m = answer.size();
    Student std;
    cin >> std.name;
    std.right = 0;
    std.wrong = 0 ;
    for (int i = 0; i < m ; ++i) {
        int option;
        cin >> option;
        if (option == answer[i]) ++std.right;
        else if (option != 0 ) ++std.wrong;
}
    return std;
}


vector <Student> get_info_test (const vector<int>& answers, int n){
    vector <Student> resp (n);
    for (int w = 0; w < n; ++w) resp[w] = get_info_std(answers);
    return resp;
}

bool cmp(const Student& a, const Student& b) {
    int mark_a = 2*a.right-a.wrong;
    int mark_b = 2*b.right - b.wrong;
    if (mark_a != mark_b) return mark_a > mark_b;
    if (a.wrong != b.wrong) return a.wrong < b.wrong;
    return a.name < b.name ;
}

void write_results(const vector<Student>& v) {
    int n = v.size();
    for (int i = 0; i < n; ++i)
    cout << v[i].name << " " << v[i].right << " " << v[i].wrong << endl;
}

int main() {
    int m;
    cin >> m;
    vector<int> answer = read_right_answers(m);
    int n;
    cin >> n;
    vector<Student> v = get_info_test(answer, n);
    sort(v.begin(), v.end(), cmp);
    write_results(v);
}
