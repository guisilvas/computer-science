#include <iostream>

using namespace std;

int main(void)
{
    char name[20];
    int age;
    float height;

    cout << "Enter your name: ";
    cin >> name;
    cout << "Enter your age: ";
    cin >> age;
    cout << "Enter your height: ";
    cin >> height;

    cout << name << " is "<< age <<" years old and is "<< height <<"m tall";
    return 0;
}