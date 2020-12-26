//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#include <iostream>
#include "Address.h"
#include "Contact.h"
#include "ContactBook.h"
#include <fstream>
#include <sstream>
using namespace std;

void menuContacts(ContactBook& book);
void mergeBooks(ContactBook& book1, ContactBook& book2);
void removeBook(ContactBook book[], int& bookDelete, int& BOOKSIZE);


int main() {

	ContactBook book[5];
	string tempString;
	string tempArray[100];
	string token;
	int i = 0;
	int bookNumber = 0;
	int BOOKSIZE = 0;
	bool flag = true;
	int temp= 0;

	ifstream myfile;
	cout << "Importing Contactbooks..." << endl;
	myfile.open("contactbooks.txt");


	while (myfile.good()) {

		if (tempString == "endofbook|") {
			bookNumber++;
			i = 0;
		}

		getline(myfile, tempString, '\n');
		istringstream ss(tempString);
		while (getline(ss, token, '|')) {
			tempArray[i] = token;
			if (i == 0) {
				book[bookNumber].setFirstName(tempArray[i]);
			}
			if (i == 1) {
				book[bookNumber].setLastName(tempArray[i]);
			}
			i++;

			if (i == 12) {

				string f;
				string l;
				string p;
				string e;
				Address a;

				f = tempArray[2];
				l = tempArray[3];
				p = tempArray[4];
				e = tempArray[5];
				a.setHome(tempArray[6]);
				a.setStreet(tempArray[7]);
				a.setApt(tempArray[8]);
				a.setCity(tempArray[9]);
				a.setState(tempArray[10]);
				a.setZip(tempArray[11]);

				book[bookNumber].addNewContact(f, l, p, e, a);
				i = 2;
			}
		}
	}
	myfile.close();
	BOOKSIZE = bookNumber - 1;

	cout << "Contact BOOKSIZE is: " << BOOKSIZE << endl;
	cout << "Import Complete" << endl;


	
	while (flag) {

		string firstNameBook1, lastNameBook1, firstNameBook2, lastNameBook2;
		string bookDeleteFirst, bookDeleteLast;
		int firstBookIndex=0, secondBookIndex=0;
		int bookDelete = 0;
		bool flag2 = false;
		bool merge1 = false;
		bool merge2 = false;

		int input = 0;
		cout << "\n\nCONTACTBOOK MENU" << endl;
		cout << "=================" << endl;
		cout << "1. CREATE NEW CONTACTBOOK" << endl;
		cout << "2. REMOVE CONTACTBOOK" << endl;
		cout << "3. DISPLAY ALL CONTACTBOOKS" << endl;
		cout << "4. MERGE CONTACTBOOK" << endl;
		cout << "5. SELECT CONTACTBOOK" << endl;
		cout << "6. EXIT" << endl;
		cout << "\nInput # for selection: ";
		cin >> input;
		cin.clear();
		cin.ignore();
		cout << endl;


		switch (input) {

		case 1:
			if (BOOKSIZE == 4) {
				cout << "MAX SIZE REACHED" << endl;
				break;
			}
			else {
				book[BOOKSIZE+1].setIndex(0);
				menuContacts(book[++BOOKSIZE]);
			}
			break;

		case 2:

			cout << "Enter First name of book you would like to delete: " << endl;
			cin >> bookDeleteFirst;
			cout << "Enter Last name of book you would like to delete: " << endl;
			cin >> bookDeleteLast;


			for (int i = 0; i <= BOOKSIZE; i++) {
				if (book[i].getFirstName() == bookDeleteFirst && book[i].getLastName() == bookDeleteLast) {
					cout << "First Book Match" << endl;
					cout << "i is : " << i << endl;
					cout << "BOOKSIZE IS " << BOOKSIZE << endl;
					bookDelete = i;
					flag2 = true;
					break;
				}
				else cout << "No match in Book " << i << endl;
			}

			removeBook(book, bookDelete, BOOKSIZE);
	
			break;

		case 3:

			for (int i = 0; i <= BOOKSIZE; i++) {
				cout << "\n==============" << endl;
				cout << "CONTACTBOOK #" << i << endl;
				cout << "Owner: " << book[i].getFirstName() << " " << book[i].getLastName() << endl;
				cout << "==============" << endl;
				book[i].displayAll();
			}
			break;

		case 4:

			cout << "Enter First name of DESTINATION book: " << endl;
			cin >> firstNameBook1;
			cout << "Enter Last name of DESTINATION book: " << endl;
			cin >> lastNameBook1;
			cout << "Enter First name of SOURCE book: " << endl;
			cin >> firstNameBook2;
			cout << "Enter Last name of SOURCE book: " << endl;
			cin >> lastNameBook2;
			for (int i = 0; i <= BOOKSIZE; i++) {
				if (book[i].getFirstName() == firstNameBook1 && book[i].getLastName() == lastNameBook1) {
					cout << "DESTINATION name match book: " << i << endl;
					firstBookIndex = i;
					merge1 = true;
					break;
				}
				else cout << "DESTINATION - No name match in Book: " << i << endl;
			}
			for (int i = 0; i <= BOOKSIZE; i++) {
				if (book[i].getFirstName() == firstNameBook2 && book[i].getLastName() == lastNameBook2) {
					cout << "SOURCE name match book: " << i << endl;
					secondBookIndex = i;
					merge2 = true;
					break;
				}
				else cout << "SOURCE - No name match in Book: " << i << endl;
			}
			if (merge1 == true && merge2 == true) {
				mergeBooks(book[firstBookIndex], book[secondBookIndex]);
				cout << "DELETING SOURCE BOOK" << endl;
				removeBook(book, secondBookIndex, BOOKSIZE);
				cout << "MERGE COMPLETE" << endl;
			}
			else break;

		case 5:

			break;

		case 6:
			cout << "Goodbye";
			flag = false;



		}
	}

	return 0;
}




void removeBook(ContactBook book[], int& bookDelete, int& BOOKSIZE) {
	
	
	int temp = book[BOOKSIZE].getIndex();
	if (BOOKSIZE == bookDelete) {
		for (int i = 0; i < temp; i++) {
			book[BOOKSIZE].deleteContact(i);
		}
		BOOKSIZE--;
		return;
	}
	else for (bookDelete; bookDelete < BOOKSIZE; bookDelete++) {
		book[bookDelete] = book[bookDelete + 1];
	}
	for (int i = 0; i < temp; i++) {
		book[bookDelete].deleteContact(i);
	}

	BOOKSIZE--;

}



void mergeBooks(ContactBook& book1, ContactBook& book2) {

	string fName, lName, pNum, eMail;
	Address add;

	cout << "MERGING BOOKS..." << endl;

	int indexBook1 = book1.getIndex();
	int indexBook2 = book2.getIndex();

	for (int index = 0; index <= indexBook2-1; index++) {
		fName = book2.getContact(index).getFirstName();
		lName = book2.getContact(index).getLastName();
		pNum = book2.getContact(index).getPhoneNumber();
		eMail = book2.getContact(index).getEmail();
		add = book2.getContact(index).getAddress();

		book1.addNewContact(fName, lName, pNum, eMail, add);
	}

}


void menuContacts(ContactBook& book) {

	string BookFirstName, BookLastName;
	cout << "Creating new contactbook..." << endl;
	cout << "Enter owner's first name: ";
	cin >> BookFirstName;
	cout << "Enter owner's last name: ";
	cin >> BookLastName;

	book.setFirstName(BookFirstName);
	book.setLastName(BookLastName);
	

	bool flag = true;

	while (flag) {

		string firstName, lastName;
		int input = 0;
		cout << "\n\n\nCONTACTS MENU" << endl;
		cout << "==============" << endl;
		cout << "1. ADD NEW CONTACT" << endl;
		cout << "2. DELETE CONTACT" << endl;
		cout << "3. SEARCH FOR CONTACT" << endl;
		cout << "4. UPDATE CONTACT INFO" << endl;
		cout << "5. DISPLAY ALL" << endl;
		cout << "6. COMPARE" << endl;
		cout << "7. EXIT" << endl;
		cout << "\nInput # for selection: ";
		cin >> input;
		cin.clear();
		cin.ignore();
		cout << endl;



		switch (input) {

		case 1:
			book.addNewContact();
			break;

		case 2:
			int p;
			book.displayAll();
			cout << "Which contact # would you like to delete? ";
			cin >> p;
			book.deleteContact(p);
			break;

		case 3:
			cout << "###SEARCH###" << endl;
			cout << "Enter First Name: " << endl;
			cin >> firstName;
			cout << "Enter Last Name: " << endl;
			cin >> lastName;
			book.displayContactInfo(firstName, lastName);
			break;

		case 4:
			cout << "###EDIT###" << endl;
			cout << "Enter First Name: " << endl;
			cin >> firstName;
			cout << "Enter Last Name: " << endl;
			cin >> lastName;
			book.updateContactInfo(firstName, lastName);
			break;

		case 5:
			book.displayAll();
			break;

		case 6:
			int index1, index2;
			cout << "Input first index number: " << endl;
			cin >> index1;
			cout << "Input second index number: " << endl;
			cin >> index2;

			if (book.getContact(index1) == book.getContact(index2))
				cout << "Both contacts are the same" << endl;
			else if (book.getContact(index1) != book.getContact(index2)) {
				cout << "Contacts are not the same" << endl;
				if (book.getContact(index1) < book.getContact(index2))
					cout << "Contact " << index1 << " is less than " << index2;
				else if (book.getContact(index1) > book.getContact(index2))
					cout << "Contact " << index1 << " is greater than " << index2;

			}
			break;


		case 7:
			cout << "Goodbye";
			flag = false;

		}
	}
}