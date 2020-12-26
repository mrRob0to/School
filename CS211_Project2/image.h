#ifndef IMAGE_H
#define IMAGE_H
#include "file.h"

class Image : public File {


	public:
		//Constructors
		Image();
		Image(string name);
		Image(string name, int row, int col, int color);

		//Setters
		void setRows(int);
		void setColumns(int);
		void setColor(int);

		//Getters
		int getRows();
		int getColumns();
		int getColors();
		virtual int getSize();

		//Display
		virtual void display();


	private:
		int row;
		int col;
		int color;


};










#endif