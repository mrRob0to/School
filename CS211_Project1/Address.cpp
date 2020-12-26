//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#include "Address.h"
#include "Contact.h"
#include <string>
#include <iostream>
using namespace std;

Address::Address() {

}


void Address::setHome(string t) {
	home = t;
}

void Address::setStreet(string t) {
	street = t;
}

void Address::setApt(string t) {
	apt = t;
}

void Address::setCity(string t) {
	city = t;
}

void Address::setState(string t) {
	state = t;
}

void Address::setZip(string t) {
	zip = t;
}


// Accessor method for the home instance variable
string Address::getHome() const {
	return home;
}

// Accessor method for the street instance variable
string Address::getStreet() const {
	return street;
}

/*Accessor method that returns apartment number
if it is an apartment building, or "none" if
it is a private house.*/
string Address::getApt() const {

	if (apt == "none" || apt == "NONE")
		return "";
	else
		return apt;
}

// Accessor method for the city instance variable
string Address::getCity() const {
	return city;
}

// Accessor method for the state instance variable
string Address::getState() const {
	return state;
}

// Accessor method for the zip instance variable
string Address::getZip() const {
	return zip;
}

// Method that prints Address to console
void Address::output() const {

	cout << "\nYour address is: " << getHome() << " "
		<< getStreet() << " " << getApt() << " "
		<< getCity() << " " << getState() << " "
		<< getZip() << " " << endl;


}

// Method that solicits the information 
// Apartment will be set to "none" if it is a private house
// If it is an Apartment Building, method will solicit
// info about apartment 
void Address::input() {

	cout << "Enter home number: ";
	cin.ignore();
	getline(cin, home);
	cout << "Enter street number: ";
	getline(cin, street);
	cout << "Enter apt, if n/a type \"none\": ";
	getline(cin, apt);
	cout << "Enter city: ";
	getline(cin, city);
	cout << "Enter state: ";
	getline(cin, state);
	cout << "Enter zip: ";
	getline(cin, zip);

}



//Operator overload
ostream& operator << (ostream& out, const Address& a) {

	out << "\nYour address is: " << a.home << " "
		<< a.street << " " << a.apt << " "
		<< a.city << " " << a.state << " "
		<< a.zip << " " << endl;

	return out;

}


istream& operator >> (istream& in, Address& a) {


	cout << "Enter home number: ";
	cin.ignore();
	getline(in, a.home);
	cout << "Enter street number: ";
	getline(in, a.street);
	cout << "Enter apt, if n/a type \"none\": ";
	getline(in, a.apt);
	cout << "Enter city: ";
	getline(in, a.city);
	cout << "Enter state: ";
	getline(in, a.state);
	cout << "Enter zip: ";
	getline(in, a.zip);

	return in;
}