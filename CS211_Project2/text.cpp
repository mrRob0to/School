#include "text.h"


Text::Text() {
	setName("");
	setType("txt");
	c = 0;
}

Text::Text(string t, int n) {
	setName(t);
	setType("txt");
	c = n;
}

int Text::getSize() {
	return c;
}

int Text::getCount() {
	return c;
}

void Text::display() {
	cout << "\n\nFile Name: " << getName() << endl;
	cout << "File Type: " << getType() << endl;
	cout << "File Size: " << getSize() << " bytes" << endl;
}