What is the distribution of SIZES of FILES on YOUR computer?a) Determine the file sizesb) Histogram: of HOW MANY files you have from (size x) to (size x+dx).c) Histogram: of Log(HOW MANY) files you have from Log[(size x) to size(x+dx)].The result can also be represented by a Probability Density Function, PDF(x) = [number of files from (size x) to (size x+dx)] / [(dx) (total number of files)].  This can be shown on a linear-linear plot of PDF(x) vs. x or on a logarithmic-logarithmic plot of log[PDF(x)] vs. log[x].  The following program will construct both those plots from an input file of your data.PROGRAM:
histo_10.py created in Python 3.4.1 (also works in Python 3.7.1)
by Larry S. Liebovitch
Liebovitch et al. 1999. Phys Rev E59: 3312-3319.
Brown & Liebovitch. 2010. Fractal Analysis QASS-165 Sage.
revised August 9, 2019
Python script is provided in the Recitation_2 directory.

ALGORITHM:
From a set of input values, this program constructs the PDF.  It first constructs histograms of different bin size, computes the PDF of each histogram, and then combines the PDFs into a single plot.  It works very well for "long-tailed" or "fat-tailed" input values that span a very large range of values.  It works less well, or not at all, for data with a normal (Gaussian) distribution of values.

INPUT FILE:
ASCII text file with the list of file sizes each separated by a return.
There should be no 0 values in the input file.
Sample input file: data2.txt is provided in the Recitation_2 directory.

RUN THE SCRIPT:
1. when asked, "filename =  (e.g. files.txt) ", type the filename, for example: data2.txt
2. when asked, "smallest bin size (0=the program decides)", type: 0
3. when asked, "Do you want to write PDF vs. data to a file (y/n), Def.=n ", type: n
Possible issues:
  If 0=the program decides doesn't work, choose a number about twice the size of the smallest value in the input data.
  Do you want to write PDF vs. data to a file. . ." Type: y to write the output data [x, PDF(x)] to an ASCII text file.

OUTPUT:
Figure 1 is a linear-linear plot of PDF (x) vs. x.
Figure 2 is a logarithmic-logarithmic plot of log10[PDF(x)] vs. log10[x].
Sample files: data2linear.png and data2log.png of these figures are provided in the Recitation_2 directory.