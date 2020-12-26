//Robert Antenorcruz/
//Prof. Alayev CS211
#include <iostream>
#include <string>
#include <stdlib.h>
#include <vector>
#include <fstream>
#include "text.h"
#include "image.h"

using namespace std;


void output(vector<File*> x, int n) {

	if (n == 0) {
		x[n]->display();
	}

	else {
		output(x, n - 1);
		x[n]->display();
	}
}


vector<File*> type(vector<File*> x, string t, int n) {

	if (n == 0) {
		vector<File*> temp;
		if (x[0]->getType() == t) {
			temp.push_back(x[0]);
		}
		return temp;
	}

	vector <File*> temp = type(x, t, n - 1);
	if (x[n]->getType() == t) {
		temp.push_back(x[n]);
	}

	return temp;
}


int main() {

	int input;
	bool flag = true;

	vector<File*>vector;

	while (flag == true) {

		cout << "\n\n\nCONTACTS MENU" << endl;
		cout << "==============" << endl;
		cout << "1. READ FILE" << endl;
		cout << "2. CREATE IMG" << endl;
		cout << "3. CREATE TXT" << endl;
		cout << "4. DISPLAY ALL" << endl;
		cout << "5. DISPLAY IMGs" << endl;
		cout << "6. DISPLAY TXTs" << endl;
		cout << "7. DELETE FILE" << endl;
		cout << "8. EXIT" << endl;
		cout << "\nInput # for selection: ";
		cin >> input;


		if (input == 1) {
			ifstream in;
			string line;
			in.open("Files.txt");
			while (!in.eof()) {
				getline(in, line);
				if (line.empty()) continue;
				else if (line.compare("txt") == 0) {
					string name;
					getline(in, name);
					int chars;
					in >> chars;
					vector.push_back(new Text(name, chars));
					cout << "PUSHING TXT" << endl;
				}
				else if (line.compare("gif") == 0) {
					string name;
					getline(in, name);
					string temp;
					int rows;
					int cols;
					int color;
					in >> rows;
					in >> temp;
					in >> cols;
					in >> color;
					vector.push_back(new Image(name, rows, cols, color));
					cout << "PUSHING IMG" << endl;
				}
				else {
					cout << "ERROR!" << endl;
					exit(1);
				}
			}

			cout << "File IMPORT SUCCESS" << endl;
			continue;

		}

		if (input == 2) {
			string name;
			int data;
			cout << "Enter name for img file: " << endl;
			cin >> name;
			Image* x = new Image(name);
			cout << "Enter # of rows: " << endl;
			cin >> data;
			x->setRows(data);
			cout << "Enter # of cols: " << endl;
			cin >> data;
			x->setColumns(data);
			cout << "Enter color depth: " << endl;
			cin >> data;
			x->setColor(data);
			vector.push_back(x);
			continue;
		}

		else if (input == 3) {
			string name;
			int chars;
			cout << "New txt file: " << endl;
			cin >> name;
			cout << "Enter # of chars: " << endl;
			cin >> chars;
			vector.push_back(new Text(name, chars));
			continue;
		}

		else if (input == 4) {
			int n = vector.size() - 1;
			output(vector,n);
		}

		else if (input == 5) {
			int n = vector.size() - 1;
			std::vector<File*>tempGif=type(vector, "gif", n);
			n = tempGif.size() - 1;
			output(tempGif, n);
		}

		else if (input == 6){
			int n = vector.size()-1;
			std::vector<File*>tempTxt=type(vector, "txt", n);
			n = tempTxt.size() - 1;
			output(tempTxt, n);
		}

		else if (input == 7) {
			string name, type = "";
			cout << "Enter name of file to delete: " << endl;
			cin >> name;

			cout << "Is this a txt or gif? " << endl;
			cin >> type;

			int i = 0;
			while (i < vector.size()) {
				if (vector[i]->getName().compare(name) == 0 && vector[i]->getType().compare(type) == 0) {
					cout << "FILE FOUND" << endl;
					break;
				}
				++i;
			}
			if (i >= vector.size()) {
				cout << "ERROR File not found" << endl;
				continue;
			}
			else {
				File* temp5 = vector[i];
				while (i < vector.size() - 1) {
					vector[i] = vector[i + 1];
					++i;
				}
				vector.resize(vector.size() - 1);
				cout << "FILE DELETED" << endl;
				continue;
			}
		}

		if (input == 8) {
			exit(3);
		}
	}
	
	return 0;
}