clc
clear

%% figure of learned word quality and its connection
mat = load('qualityScore.txt');

figure(1);

[r, c] = size(mat(:,2));
x = 1:1:r;
plot(x, mat(:,2));
title('Learned sentiment word quality v.s. the connection quantity');
ylabel('Connection Quantity');