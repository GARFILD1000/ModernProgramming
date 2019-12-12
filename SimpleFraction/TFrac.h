#ifndef COLUMNSOLVERSLAE_TFRAC_H
#define COLUMNSOLVERSLAE_TFRAC_H

#include <cmath>
#include <string>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <vector>

#define DELTA 0.00000000000000001

template <typename T>
class TFrac{
private:
    T numerator;
    T denominator;

    std::vector<std::string> split(const std::string &s, char delim) {
        std::stringstream ss(s);
        std::string item;
        std::vector<std::string> elems;
        while (std::getline(ss, item, delim)) {
            elems.push_back(item);
            // elems.push_back(std::move(item)); // if C++11 (based on comment from @mchiasson)
        }
        return elems;
    }

    T getGreatestCommonDivisor(T number1, T number2){
        number1 = std::abs(number1);
        number2 = std::abs(number2);
        if (number1 == 0 || number2 == 0){
            return 0;
        }
        if (number1 == 1 || number2 == 1){
            return 1;
        }
        while (number1 != number2) {
            if (number1 > number2) {
                number1 = number1 - number2;
            } else {
                number2 = number2 - number1;
            }
        }
        return number1;
    }

    T getLowestCommonDenominator(T number1, T number2){
//        if (number1 == 0 || number2 == 0){
//            return 0;
//        }
        long long lowest = number1*number2 / getGreatestCommonDivisor(number1, number2);
        return lowest;
    }

    void setCommonDenominator(TFrac &fraction1, TFrac &fraction2){
        if (fraction1.denominator != fraction2.denominator){
            T lcd = getLowestCommonDenominator(fraction1.denominator, fraction2.denominator);
            fraction1.numerator = fraction1.numerator * (lcd / fraction1.denominator);
            fraction2.numerator = fraction2.numerator * (lcd / fraction2.denominator);
            fraction1.denominator = lcd;
            fraction2.denominator = lcd;
        }
    }

public:

    TFrac(){
        numerator = 0;
        denominator = 1;
    }

    TFrac(T num, T denom){
        numerator = num;
        denominator = denom;
        reduceFraction();
    }

    void init(T num, T denom){
        numerator = num;
        denominator = denom;
        denominator = denom;
        reduceFraction();
    }

    TFrac(std::string stringFraction){
        auto parts = split(stringFraction,'/');
        if (parts.size() == 2 && parts[0].size() > 0 && parts[1].size() > 0){
            auto numeratorString = parts[0];
            auto denominatorString = parts[1];
            if (numeratorString.find(',') > 0 || denominatorString.find(',') > 0){
                auto newNumerator = std::stod(numeratorString);
                auto newDenominator = std::stod(denominatorString);
                init(newNumerator, newDenominator);
            }
            else if (numeratorString.find('.') > 0 || denominatorString.find('.') > 0){
                auto newNumerator = std::stod(numeratorString);
                auto newDenominator = std::stod(denominatorString);
                init(newNumerator, newDenominator);
            }
            else{
                auto newNumerator = std::stoi(numeratorString);
                auto newDenominator = std::stoi(denominatorString);
                init(newNumerator, newDenominator);
            }
        }
        else if (parts.size() == 1){
            auto numeratorString = parts[0];
            if (numeratorString.find(',') > 0 ){
                auto newNumerator = std::stod(numeratorString);
                auto newDenominator = 1.0;
                init(newNumerator, newDenominator);
            }
            else if (numeratorString.find('.') > 0){
                auto newNumerator = std::stod(numeratorString);
                auto newDenominator = 1.0;
                init(newNumerator, newDenominator);
            }
            else{
                auto newNumerator = std::stoi(numeratorString);
                auto newDenominator = 1;
                init(newNumerator, newDenominator);
            }
        }
        else{
            numerator = 0;
            denominator = 1;
        }
    }

    TFrac(TFrac *newFraction){
        numerator = newFraction->getNumerator();
        denominator = newFraction->getDenominator();
        reduceFraction();
    }

    void setNumerator(T num){
        numerator = num;
    }

    T getNumerator() const {
        return numerator;
    }

    void setDenominator(T denom){
        denominator = denom;
    }

    T getDenominator() const {
        return denominator;
    }

    TFrac copy(){
        return TFrac(numerator, denominator);
    }

    void reduceFraction(){
        long long gcd = getGreatestCommonDivisor(numerator, denominator);
        if (gcd != 0){
            numerator = numerator / gcd;
            denominator = denominator / gcd;
            if(numerator < 0 && denominator < 0){
                numerator = std::abs(numerator);
                denominator = std::abs(denominator);
            }
            else if (denominator < 0){
                numerator = -numerator;
                denominator = std::abs(denominator);
            }
        }
    }

    TFrac getReverse(){
        return TFrac(denominator, numerator);
    }

    TFrac getNegative(){
        auto var = TFrac(numerator, denominator);
        var.setNegative();
        return var;
    }

    TFrac getAbs(){
        TFrac abs;
        if(this->getNumerator() < 0){
            abs.setNumerator(-this->getNumerator());
        }
        else{
            abs.setNumerator(this->getNumerator());
        }
        abs.setDenominator(this->getDenominator());
        return abs;
    }

    TFrac getSquare(){
        return TFrac(numerator * numerator, denominator * denominator);
    }

    TFrac operator+(TFrac &operand2){
        TFrac result;
        this->reduceFraction();
        operand2.reduceFraction();
        setCommonDenominator(*this, operand2);
        result.setNumerator(this->numerator + operand2.numerator);
        result.setDenominator(this->denominator);
        result.reduceFraction();
        this->reduceFraction();
        operand2.reduceFraction();
        return result;
    }

    TFrac operator-(TFrac &operand2){
        TFrac result;
        this->reduceFraction();
        operand2.reduceFraction();
        setCommonDenominator(*this, operand2);
        result.setNumerator(this->numerator - operand2.numerator);
        result.setDenominator(this->denominator);
        result.reduceFraction();
        this->reduceFraction();
        operand2.reduceFraction();
        return result;
    }

    TFrac operator*(TFrac &operand2){
        TFrac result;
        this->reduceFraction();
        operand2.reduceFraction();
        result.setNumerator(this->numerator * operand2.numerator);
        result.setDenominator(this->denominator * operand2.denominator);
        result.reduceFraction();
        this->reduceFraction();
        operand2.reduceFraction();
        return result;
    }

    TFrac operator/(TFrac &operand2){
        TFrac result;
        this->reduceFraction();
        operand2.reduceFraction();
        result.setNumerator(this->numerator * operand2.denominator);
        result.setDenominator(this->denominator * operand2.numerator);
        result.reduceFraction();
        this->reduceFraction();
        operand2.reduceFraction();
        return result;
    }

    bool operator!=(int number){
        if (number == 0 && numerator == 0){
            return false;
        }
        double fraction = this->toDouble();
        int roundedFraction = (int)std::round(fraction);
        if ((std::abs(fraction - roundedFraction) < DELTA)){
            if (roundedFraction != number){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (fraction == number){

                return false;
            }
            else{
                return true;
            }
        }
    }

    bool operator==(TFrac number){
        return (number.numerator == this->numerator && number.denominator == this->denominator);
    }

    bool operator==(int number){
        TFrac fraction(getNumerator(), getDenominator());
        return !(fraction != number);
    }

    bool operator>(int number){
        double fraction = this->toDouble();
        int roundedFraction = (int)std::round(fraction);
        if ((std::abs((fraction - roundedFraction)) < DELTA)){
            if (roundedFraction > number){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (fraction > number){
                return true;
            }
            else{
                return false;
            }
        }
    }

    bool operator <(int number){
        double fraction = this->toDouble();
        int roundedFraction = (int)std::round(fraction);
        if ((std::abs(fraction - roundedFraction) < DELTA)){
                return false;
        }
        else{
            if (fraction < number){
                return true;
            }
            else{
                return false;
            }
        }
    }

    bool operator>=(int number){
        double fraction = this->toDouble();
        int roundedFraction = (int)std::round(fraction);
        if ((std::abs(fraction - roundedFraction) < DELTA)){
            if (roundedFraction >= number){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (fraction >= number){
                return true;
            }
            else{
                return false;
            }
        }
    }

    bool operator<=(int number){
        double fraction = this->toDouble();
        int roundedFraction = (int)std::round(fraction);
        if ((std::abs(fraction - roundedFraction) < DELTA)){
            if (roundedFraction <= number){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (fraction <= number){
                return true;
            }
            else{
                return false;
            }
        }
    }

    bool operator<(TFrac number){
        TFrac thisNumber(this->numerator, this->denominator);
        if (this->numerator != 0 && number.numerator != 0) {
            setCommonDenominator(thisNumber, number);
        }
        //std::cout << "Compare " << thisNumber.toString() << " < " << number.toString() << "?" << std::endl;
        if (thisNumber.numerator < number.numerator){
            reduceFraction();
            number.reduceFraction();
            return true;
        }
        else{
            reduceFraction();
            number.reduceFraction();
            return false;
        }
    }

    bool operator>(TFrac number){
        if (this->numerator != 0 && number.numerator != 0) {
            setCommonDenominator(*this, number);
        }
        if (numerator > number.numerator){
            reduceFraction();
            number.reduceFraction();
            return true;
        }
        else{
            reduceFraction();
            number.reduceFraction();
            return false;
        }
    }

    bool operator>=(TFrac number){
        setCommonDenominator(*this, number);
        if (numerator >= number.numerator){
            reduceFraction();
            number.reduceFraction();
            return true;
        }
        else{
            reduceFraction();
            number.reduceFraction();
            return false;
        }
    }

    bool operator<=(TFrac number){
        setCommonDenominator(*this, number);
        if (numerator <= number.numerator){
            reduceFraction();
            number.reduceFraction();
            return true;
        }
        else{
            reduceFraction();
            number.reduceFraction();
            return false;
        }
    }

    double getNumeratorNumber(){
        return static_cast<double>(numerator);
    }

    double getDenominatorNumber(){
        return static_cast<double>(denominator);
    }

    std::string getNumeratorString(){
        std::stringstream sstream("");
        sstream << numerator;
        return sstream.str();
    }

    std::string getDenominatorString(){
        std::stringstream sstream("");
        sstream << denominator;
        return sstream.str();
    }

    std::string toString(){
        std::stringstream stringStream("");
        if (numerator == 0){
            stringStream << "(" << numerator << ")" ;
        }
        else if (denominator == 1){
            stringStream << "(" << numerator << ")" ;
        }
        else{
            stringStream  << "(" << numerator << " / " << denominator << ")";
        }
        return stringStream.str();
    }

    double toDouble(){
        return numerator / (double)denominator;
    }

    bool isInteger(){
        bool isInteger = (denominator == 1);
        return isInteger;
    }

    int getInteger(){
        double notIntegerValue = (double) numerator / (double) denominator;
        int integer = static_cast<int>(std::floor(notIntegerValue));
        return integer;
    }

    double getFractional(){
        int integer = getInteger();
        double fractional = numerator / static_cast<double>(denominator) - static_cast<double>(integer);
        return fractional;
    }

    void setNegative(){
        this->numerator = -this->numerator;
    }
};


#endif
