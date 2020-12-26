//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#ifndef CONTACTBOOK_H
#define CONTACTBOOK_H
#include <iostream>
#include <string>
#include "Contact.h"


class ContactBook {

public:
	//Constructors
	ContactBook();
	ContactBook(string f, string l);

	//Setters
	void setFirstName(string f);
	void setLastName(string l);
	void setIndex(int i);

	//Getters
	string getFirstName();
	string getLastName();
	int getIndex();
	Contact getContact(int i);

	//Functions
	void addNewContact();
	void addNewContact(string f, string l, string p, string e, Address a);
	void deleteContact(int i);
	void displayAll();
	int displayContactInfo(string f, string l);
	void updateContactInfo(string f, string l);
	void sort();

	//Big Three
	~ContactBook();
	ContactBook(const ContactBook& copy);
	ContactBook& operator =(const ContactBook& copy);

	//Selection Sort
	void selectionSort(Contact* array, int size);
	void swapping(Contact& a, Contact& b);


private:
	string firstName;
	string lastName;
	int index;
	int SIZE;
	Contact* b;
	void grow();

};
#endif