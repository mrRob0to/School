#include "file.h"
using namespace std;


void File::setName(string t) {
	name = t;
}

void File::setType(string t) {
	type = t;
}

string File::getName() {
	return name;

}

string File::getType() {
	return type;
}
