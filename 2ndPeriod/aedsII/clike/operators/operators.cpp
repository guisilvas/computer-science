#include <iostream>

using namespace std;

int main()
{
    int x=3, y=2;
    int sum, sub, mult, remainder, quoc, inc, dec;
    float div;

    sum = x+y;
    cout << "x + y = " << sum << "\n";
    sub = x-y;
    cout << "x - y = " << sub << "\n";
    mult = x*y;
    cout << "x * y = " << mult << "\n";
    div = (float)x/y;
    cout << "x / y = " << div << "\n";
    remainder = x%y;
    cout << "x % y = " << remainder << "\n";
    quoc = x/y;
    cout << "x / y = " << quoc << "\n";
    inc = x+1;
    cout << x << "++ = " << inc << "\n";
    dec = y-1;
    cout << y << "-- = " << dec << "\n";

    return 0;
}