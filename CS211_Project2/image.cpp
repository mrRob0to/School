#include "image.h"


Image::Image() {
	setName("");
	setType("gif");
	row = 0;
	col = 0;
	color = 0;

}

Image::Image(std::string name) {
	setName(name);
	setType("gif");
	row = 0;
	col = 0;
	color = 0;

}

Image::Image(string n, int r, int c, int clr) {
	setName(n);
	setType("gif");
	row = r;
	col = c;
	color = clr;
}

void Image::setRows(int r) {
	row = r;
}

void Image::setColumns(int c) {
	col = c;
}

void Image::setColor(int clr) {
	color = clr;
}


int Image::getRows() {
	return row;
}

int Image::getColumns() {
	return col;
}

int Image::getColors() {
	return color;
}

int Image::getSize() {
	return row * col * color / 8;
}


void Image::display() {
	cout << "\n\nFile Name: " << getName() << endl;
	cout << "File Type: " << getType() << endl;
	cout << "File Size: " << getSize() << " bytes" << endl;
	cout << "Dimensions: " << "[Rows:" << getRows() << "px]" 
		<< " [Columns: " << getColumns() << "px]" 
		<< " [Colors: " << getColors() << "bit]" << endl;
	

}


