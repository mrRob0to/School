#ifndef FILE_H
#define FILE_H
#include <string>
#include <iostream>

using namespace std;

class File {

	public:
		
		//Setters
		void setName(string t);
		void setType(string t);
		
		
		//Getters
		string getName();
		string getType();
		virtual int getSize() = 0;

		//Display
		virtual void display() = 0;


	private:

		string name;
		string type;


};

#endif
