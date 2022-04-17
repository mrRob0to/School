###Robert Antenorcruz
###Shodan unix .csv parser

#How to Use: 
#Have shodan .csv file, keyWords.txt, and parse.py in the same directory. 
#In terminal type "python3 parse.py filename.csv". 
#It will output the data to a new .txt called FILTERED_DATA.txt
#Ex: python3 parse.py shodan_data_ssh.csv

import os
import sys

filename = sys.argv[1]
command = 'cut -d "," -f5 %s | grep -Evf keyWords.txt | awk "length($0) <30" |sort | grep [a-z] | grep -v [0-9] | grep -v "=" > FILTERED_DATA.txt' %(filename)
os.system(command)
print("Parsing complete")