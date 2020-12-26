//Robert Antenorcruz
//CS211 - Prof. Alayev
//Project 1 
#ifndef CONTACT_H
#define CONTACT_H
#include "Address.h"
#include <regex>
#include <string>
using namespace std;

class Contact{

    public:

        //Constructors
        Contact();
        Contact(string f, string l, string p, string e, Address a);
        

        //IO Functions
        void input();
        void output();
       
        //Setters
        void setLastName(string t);
        void setFirstName(string t);
        void setAddress(Address t);
        void setPhoneNumber(string t);
        void setEmail(string t);
       
        //Getters
        string getLastName();
        string getFirstName();
        Address getAddress();
        string getPhoneNumber();
        string getEmail();

        //Operator Overload 
        friend istream& operator >> (istream& in, Contact& c);
        friend ostream& operator << (ostream& out, const Contact& c);

        bool operator < (const Contact& c);
        bool operator > (const Contact& c);
        bool operator == (const Contact& c);
        bool operator != (const Contact& c);
        bool operator <= (const Contact& c);
        bool operator >= (const Contact& c);


    private:
        string lastName;
        string firstName;
        string address;
        string phoneNumber;
        string email;
        Address a;

};
#endif