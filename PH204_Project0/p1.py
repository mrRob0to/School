import csv

#f= open("newout.csv", "a+")

with open('newOut.csv', 'a', newline='') as file:
	writer = csv.writer(file)

	with open('out.csv') as csv_file:
		csv_reader = csv.reader(csv_file, delimiter=',')

		for row in csv_reader:
			for column in row:
				if column == "":
					print("SPAM")
				else: 
					writer.writerow(column[0])
		#print(row)





# import matplotlib.pyplot as plt
# plt.plot([1,2,3,4])
# plt.ylabel('some numbers')
# plt.show()