#ifndef TEXT_H
#define TEXT_H
#include "file.h"


class Text : public File {

	public:
		Text();
		Text(string, int);

		//Getters
		virtual int getSize();
		int getCount();

		//Display
		virtual void display();

	private:
		int c;


};
#endif