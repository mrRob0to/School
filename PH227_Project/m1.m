clear
clc
close all
 
[x,fs] = audioread('SampleMusic.wav'); 
[y,fs] = audioread('NoisySampleMusic.wav');
 
dt = .1;
N=length(x); 
t=(1:N)/fs;
 
%Question A
figure('Name','Question A')
plot(t,x);
 
%Question B
X = fft(x);
figure('Name','Question B')
plot(t,X);
 
%Question C
figure('Name','Question C')
plot(t,y);
axis([0 2*pi -3 3])
 
%Question D
Y = fft(y);
figure('Name', 'Question D')
plot(t,Y);
 
%Question E
figure('Name', 'Question E')
subplot(2,1,1)
plot(t,y), hold on
plot(t,x)
axis([0 2*pi -3 3])
 
PSD = Y.*conj(Y)/N;
freq = 1/(dt*N)*(0:N);
L = 1:floor(N/2);
 
subplot(2,1,2);
plot(freq(L), PSD(L)), hold on
 
indices = PSD>40;
PSDclean = PSD.*indices;
V = indices.*Y;
ffilt = ifft(V);
 
plot(freq(L),PSDclean(L), '-','Color',[.5 .1 0], 'LineWidth', 2.5)
 
