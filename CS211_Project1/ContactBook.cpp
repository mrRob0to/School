//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#include "ContactBook.h"
using namespace std;

//Constructors
ContactBook::ContactBook()
{
	firstName = "";
	lastName = "";
	index = 0;
	SIZE = 3;
	b = new Contact [SIZE];
}

ContactBook::ContactBook(string f, string l) {
	firstName = f;
	lastName = l;
	index = 0;
	SIZE = 3;
	b = new Contact [SIZE];
}

//Setters
void ContactBook::setFirstName(string f) {
	firstName = f;
}

void ContactBook::setLastName(string l) {
	lastName = l;
}

void ContactBook::setIndex(int i) {
	index = i;
}


//Getters
string ContactBook::getFirstName() {
	return firstName;
}

string ContactBook::getLastName() {
	return lastName;
}

int ContactBook::getIndex(){
	return index;
}

Contact ContactBook::getContact(int i) {
	return b[i];
}


//Menu Functions
void ContactBook::addNewContact() {
	
	if (index == SIZE - 1)
	{
		grow();
	}
	b[index].input();
	index++;
	
}

void ContactBook::addNewContact(string f, string l, string p, string e, Address a) {



	if (index == SIZE - 1)
	{
		grow();
	}

	b[index].setFirstName(f);
	b[index].setLastName(l);
	b[index].setPhoneNumber(p);
	b[index].setEmail(e);
	b[index].setAddress(a);
	index++;

}

void ContactBook::deleteContact(int i) {
	if (index == i) {
		index--;
	}
	else for (int t = i; t < index; t++) {
		b[t] = b[t + 1];
	}
	index--;
}


void ContactBook::displayAll() {
	
	sort();
	for (int i = 0; i < index; i++) {
		cout << "\nCONTACT #" << i << endl;
		cout << "==========" << endl;
		b[i].output();
	};
	
}


int ContactBook::displayContactInfo(string f, string l) {
	for (int i = 0; i < index; i++) {
		if (b[i].getFirstName() == f && b[i].getLastName() == l) {
			b[i].output();
			return i;
		}
		else {
			cout << "\nINVALID INPUT\n";
			return -1;
		}
	}
	cout << "NO USERS IN CONTACTBOOK! " << endl;
	return -1;
}

void ContactBook::updateContactInfo(string f, string l) {
	int index = -1;
	index = displayContactInfo(f, l);
	if (index == -1 || index > 9) {
		cout << "No contact named: " << f << " " << l << endl;
	}
	else {
		cout << "###EDITING###" << endl; 
		b[index].input();
	}
}


void ContactBook::grow() {
	int newSIZE = SIZE * 2;
	Contact* tempList;
	tempList = new Contact  [newSIZE];


	for (int i = 0; i < SIZE - 1; i++) {
		tempList[i] = b[i];
	}
	b = tempList;
	SIZE = newSIZE;
}

void ContactBook::sort() {

	selectionSort(b, SIZE);

}

void ContactBook::selectionSort(Contact* array, int size) {
	int i=0, j=0, imin = 0;
	for (i = 0; i < size - 1; i++) {
		imin = i;
		for (j = i + 1; j < index; j++) {
			if (array[j] < array[imin])
				imin = j;
		}
		if (imin != i)
			swapping(array[i], array[imin]);
	}
}

void ContactBook::swapping(Contact& i, Contact& imin) { 
	Contact temp;
	temp = i;
	i = imin;
	imin = temp;
}


//Big Three
ContactBook::~ContactBook() {
	if (b != NULL) {
		delete[] b;
		b = NULL;
	}
}

ContactBook::ContactBook(const ContactBook& copy) {
	firstName = copy.firstName;
	lastName = copy.lastName;
	index = copy.index;
	SIZE = copy.SIZE;

	int newSIZE = SIZE * 2;
	b = new Contact  [newSIZE];

	for (int i = 0; i < SIZE - 1; i++) {
		b[i] = copy.b[i];
	}

}


ContactBook& ContactBook::operator = (const ContactBook& copy) {

	if (this != &copy) {
		firstName = copy.firstName;
		lastName = copy.lastName;
		index = copy.index;
		SIZE = copy.SIZE;

		int newSIZE = SIZE++;
		b = new Contact  [newSIZE];

		for (int i = 0; i < SIZE - 1; i++) {
			b[i] = copy.b[i];
		}
	}

	return *this;

}



