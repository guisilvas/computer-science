#include <iostream>

using namespace std;

int main()
{
    int x = -12;

    cout << "Enter a number: ";
    cin >> x;

    if(x>0)
    {
        cout << x << " is positive";
    }
    else
    {
        cout << x << " is negative";
    }
    
    return 0;
}