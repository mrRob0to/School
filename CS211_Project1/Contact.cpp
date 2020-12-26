//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#include "Contact.h"
#include <iostream>
#include <regex>
#include <string>
using namespace std;



//Constructors
Contact::Contact() {
	firstName = "";
	lastName = "";
	phoneNumber = "xxxxxxxxx";
	email = "";

}

Contact::Contact(string f, string l, string p, string e, Address a) {

	setLastName(l);
	setFirstName(f);
	setAddress(a);
	setPhoneNumber(p);
	setEmail(e);
}

//Function - Getters
string Contact::getLastName() {
	return lastName;
}

string Contact::getFirstName() {
	return firstName;
}

Address Contact::getAddress() {
	return a;
}

string Contact::getPhoneNumber() {
	return phoneNumber;
}

string Contact::getEmail() {
	return email;
}


//Function Setters
void Contact::setLastName(string t) {
	lastName = t;
}

void Contact::setFirstName(string t) {
	firstName = t;
}

void Contact::setAddress(Address t) {
	a = t;
}
void Contact::setPhoneNumber(string t) {
	if (regex_match(t, regex("\\d{3}\\d{3}\\d{4}")))
		phoneNumber = t;
	else
		cout << "PHONE - INVALID INPUT" << endl;
}

void Contact::setEmail(string t) {
	if (regex_match(t, regex("(\\w+)(\\.|_)?(\\w*)@(\\w+)(\\.(\\w+))+")))
		email = t;
	else
		cout << "EMAIL - INVALID INPUT" << endl;
}


//IO Functions
void Contact::input() {

	string checkNumber, checkEmail;
	string lastName, firstName;
	Address temp;

	cout << "\nEnter First name: ";
	cin >> firstName;
	setFirstName(firstName);
	cout << "Enter Last name: ";
	cin >> lastName;
	setLastName(lastName);
	cout << "Enter phone number: ";
	cin >> checkNumber;
	setPhoneNumber(checkNumber);
	cout << "Enter email address: ";
	cin >> checkEmail;
	setEmail(checkEmail);
	temp.input();
	setAddress(temp);

}

//Having issues outputting data with pointers, not able to display contents.
void Contact::output() {

	cout << "First name is: " << firstName;
	cout << "\nLast name is: " << lastName;
	cout << "\nPhone number is: " << phoneNumber;
	cout << "\nEmail is: " << email;
	a.output();

}


//Operator Overload
istream& operator >> (istream& in, Contact& c) {

	string checkNumber, checkEmail;
	string lastName, firstName;
	Address temp;

	cout << "\nEnter First name: ";
	in >> firstName;
	c.setFirstName(firstName);
	cout << "Enter Last name: ";
	in >> lastName;
	c.setLastName(lastName);
	cout << "Enter phone number: ";
	in >> checkNumber;
	c.setPhoneNumber(checkNumber);
	cout << "Enter email address: ";
	in >> checkEmail;
	c.setEmail(checkEmail);
	temp.input();
	c.setAddress(temp);

	return in;

}

ostream& operator << (ostream& out, const Contact& c) {

	out << "First name is: " << c.firstName;
	out << "\nLast name is: " << c.lastName;
	out << "\nPhone number is: " << c.phoneNumber;
	out << "\nEmail is: " << c.email;
	out << c.address;

	return out;

}

bool Contact::operator < (const Contact& c) {
	if (lastName < c.lastName || (lastName == c.lastName && firstName < c.firstName))
		return true;
	else return false;
}

bool Contact::operator > (const Contact& c) {
	if (c.lastName > this->lastName || (this->lastName == c.lastName && this->firstName > c.firstName))
		return true;
	else return false;
}

bool Contact::operator == (const Contact& c) {
	if (this->lastName == c.lastName || (this->lastName == c.lastName && this->firstName == c.firstName))
		return true;
	else return false;
}

bool Contact::operator != (const Contact& c) {
	if (this->lastName != c.lastName || (this->lastName != c.lastName && this->firstName != c.firstName))
		return true;
	else return false;
}

bool Contact::operator <= (const Contact& c) {
	if (c.lastName <= this->lastName || (this->lastName == c.lastName && this->firstName <= c.firstName))
		return true;
	else return false;
}

bool Contact::operator >= (const Contact& c) {
	if (c.lastName >= this->lastName || (this->lastName == c.lastName && this->firstName >= c.firstName))
		return true;
	else return false;
}
