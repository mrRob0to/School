#making multihistogram
#histo_10.py
#prints both LINEAR and LOG-LOG plot of PDF
#log-log fit with slope
#can export ascii file, def=n

import math
import matplotlib.pyplot as plt
from scipy import stats
import numpy as np

class Fitlog:
    def __init__(self,xxlog,yylog,title):
        self.xxlog=xxlog
        self.yylog=yylog
        self.title=title
        self.w=np.ones(len(xxlog)) #first try at weights
        self.makeplot()
#        self.w=np.log10(xxlog) #weights!
#        print ('\n self.w=  ',self.w)
    def makeplot (self):
        slope,intercept=np.polyfit(self.xxlog,self.yylog,1,w=self.w)
        sloperound=round(slope,3)
        print ('\n'+self.title+'  SLOPE=   ',slope)
        fitx1=self.xxlog[0]
        fitx2=self.xxlog[-1]
        fity1=slope*fitx1+intercept
        fity2=slope*fitx2+intercept
        xfit=[fitx1,fitx2]
        yfit=[fity1,fity2]
        xfita=np.array(xfit)
        yfita=np.array(yfit)
        plt.figure()
        plt.plot(self.xxlog,self.yylog,color='k',marker='o',linewidth='0')
        plt.xlabel('log10 (size) [bytes]',fontsize='14')
        plt.ylabel('log10 (PDF) [#(x,x+dx) / (dxN)]', fontsize='14')
        plt.plot(xfit,yfit,'r',linewidth='3')
        title=self.title + ',  slope=  '+str(sloperound)
        plt.title(title, fontsize='12')
        plt.show(block=False)

def filein (fname):
    numlines=0
    xin=[]
    f=open(fname,'r')
    for line in f:
        #print (line, end='')
        xin.append(line)
        numlines=numlines+1
    f.close()
    return xin,numlines

def fileout (filename,filedata):
    f2=open(filename,'w')
    f2.write(filedata)
    f2.close()
    
def getx (fname):
    data,numlines=filein(fname)
    #print ('\ndata\n',data,'\nlines',numlines)
    x=['0' for i in range(numlines)]
    for i in range(numlines):
        x[i]=data[i].replace('\n','')
        x[i]=eval(x[i])
    return x,numlines

def getxn(fname):
    data,numlines=filein(fname)
#print (numlines)
#print (data)
    dataline=['0' for i in range(numlines)]
    for i in range(numlines):
        x=data[i]
        y=x.split('\t')
        y[-1]=y[-1].replace('\n','')
        dataline[i]=y
    #print ('\n\nascii-input',dataline)
    xdata=dataline[:]
    for i in range (numlines):
        inline=len(dataline[i])
        for j in range (inline):
            if xdata[i][j] != '':
                xdata[i][j]=eval(xdata[i][j])
            else:
                xdata[i][j]=None
    #print ('dataline',dataline)
    #print ('xdata',xdata)
    return xdata, numlines
    
def outmake(x):
    outstring=''
    personso=len(x)
    timeslotso=len(x[0])
    for i in range (personso):
        for j in range (timeslotso):
            outstring=outstring + str(x[i][j]) + '\t'
        outstring=outstring+'\n'
    outstring=outstring[0:-1]
    return outstring

#PROGRAM 
#READ IN DATA
fname=input('\nfilename =  (e.g. files.txt) ')
data,numdata=getx(fname)
#NOTE reads in 1.656e+2 as 165.6 and 0.01e-1 as 0.001 - so ALL OK

#OK this is the computational part of the program
smallest=min(data)
d1=eval(input('\nsmallest bin size (0=the program decides)'))

if (d1==0):
        d1=2*smallest

ntotal=0
x=[]
y=[]
#initial conditins for one bin size
for i1 in range (1000):
    pin=0.
    pout=0.
    d=d1*(2**(i1))
    bin=np.zeros(21)
    lbin=np.zeros(21)
    lbin=[float(i)*d+d/2. for i in range (21)]
#evaluate the number in each bin at that bin size 
    for i2 in range (numdata):
        k=int(data[i2]/d)
#I think above is correct, don't need +1 but not sure
        if (k>=20):
            bin[20]=bin[20]+1
            pout=pout+1
        else:
            bin[k]=bin[k]+1
            pin=pin+1
    print ('\nd,pin,pout,ptotal',d,pin,pout,numdata)
#computer the PDF at that bin size
    for i3 in range (1,20):
        if (bin[i3]>0):
            ntotal=ntotal+1
            x.append(lbin[i3])
            y.append(bin[i3]/(d*numdata))
    btest=bin[1]*bin[2]*bin[3]
    if (btest==0): break
#print out results
for i4 in range (ntotal):
    print ('\ncenter,PDF', x[i4],y[i4])
#prepare to save to a file
xout=np.zeros((ntotal, 2))

for i5 in range (ntotal):
    xout[i5][0]=x[i5]
    xout[i5][1]=y[i5]
zo=outmake(xout)
#save to file
print ('\noutmake ',zo)
#WRITING TO FILE
outtest=input('Do you want to write PDF vx. data to a file (y/n), Def.=n ')
outtest=outtest.lower()
# if outtest.find('n') == -1:
if outtest == 'y':
    filenameout=input ('Please give me filename, e.g. PDF.txt ')
    fileout (filenameout,zo)
    print ('\nfile saved')

#PLOT LINEAR GRAPH (procedureal)
xx=np.zeros(ntotal)
yy=np.zeros(ntotal)
xxlog=np.zeros(ntotal)
yylog=np.zeros(ntotal)
xout = sorted(xout, key=lambda xout_entry: xout_entry[0])
for i6 in range(ntotal):
    xx[i6]=xout[i6][0]
    yy[i6]=xout[i6][1]
    xxlog[i6]=math.log10(xx[i6])
    yylog[i6]=math.log10(yy[i6])
plt.figure(1)
plt.xlabel('size [bytes]',fontsize='14')
plt.ylabel('PDF [#(x,x+dx) / (dxN)]', fontsize='14')
title=fname
plt.title(title, fontsize='12')
plt.plot(xx,yy,color='r',marker='o',mec='r',linewidth='0')
plt.show(block=False)

#PLOT LOG-LOG GRAPH (object oriented)
title=fname
ps1=Fitlog(xxlog,yylog,title)
    

