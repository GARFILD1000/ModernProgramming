#include <iostream>
#include "TFrac.h"

int main() {
    TFrac<long long> fraction(9,2);
    TFrac<long long> fraction2(4,3);

    std::cout << "First constructor: " << TFrac<long long>(9,2).toString() << std::endl;
    std::cout << "Second constructor: " << TFrac<long long>("9/2").toString() << std::endl;
    std::cout << "Test constructor: " << TFrac<long long>("10").toString() << std::endl;

    std::cout << "Sum: " << fraction.toString() << " + " << fraction2.toString() << " = " <<
        (fraction + fraction2).toString() << std::endl;

    std::cout << "Diff: " << fraction.toString() << " - " << fraction2.toString()  << " = " <<
        (fraction - fraction2).toString() << std::endl;

    std::cout << "Multiply: " << fraction.toString() << " * " << fraction2.toString()  << " = " <<
        (fraction * fraction2).toString() << std::endl;

    std::cout << "Divide: " << fraction.toString() << " / " << fraction2.toString()  << " = " <<
              (fraction / fraction2).toString() << std::endl;

    std::cout << "Square: " << fraction.toString() << " ^ 2 =  " << fraction.getSquare().toString() << std::endl;

    std::cout << "Reverse: 1 / " << fraction.toString() << " =  " << fraction.getReverse().toString() << std::endl;

    std::cout << "Negative: - " << fraction.toString() << " = " << fraction.getNegative().toString() << std::endl;

    auto result = (fraction < fraction2)? "true" : "false";
    std::cout << "Less: " << fraction.toString() << " < " << fraction2.toString() << " is " <<
        result << std::endl;

    result = (fraction > fraction2)? "true" : "false";
    std::cout << "More: " << fraction.toString() << " > " << fraction2.toString() << " is " <<
        result << std::endl;

    result = (fraction == fraction2)? "true" : "false";
    std::cout << "Equals: " << fraction.toString() << " == " << fraction2.toString() << " is " <<
        result << std::endl;

    result = (fraction == fraction)? "true" : "false";
    std::cout << "Equals: " << fraction.toString() << " == " << fraction.toString() << " is " <<
              result << std::endl;

    std::cout << "Numerator number: " << fraction.getNumeratorNumber() << std::endl;

    std::cout << "Numerator string: " << fraction.getNumeratorString() << std::endl;

    std::cout << "Denominator number: " << fraction.getDenominatorNumber() << std::endl;

    std::cout << "Denominator string: " << fraction.getDenominatorString() << std::endl;
    return 0;
}