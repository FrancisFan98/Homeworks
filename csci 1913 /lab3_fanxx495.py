#!/usr/bin/python

def odd(i):
	return i % 2 == 1
	
def sqr(N):
	return N * N

def countbool(P, S):
	if len(S) == 0:
		return []
	return [P(S[0])] + countbool(P, S[1:])
	
def most(P,S):
	
	return True if sum(countbool(P, S)) > len(countbool(P, S))/2 else False
	
	
def sigma(F, B, E):
	if B > E:
		return 0
	if B == E:
		return F(B)
	else: return F(range(B, E)[0]) + sigma(F, B+1,E)
		

def test():
	assert most(odd, []) == False      #  False    2 points
	assert most(odd, [0]) == False       #  False    2 points
	assert most(odd, [1]) == True      #  True     2 points
	assert most(odd, [1, 2]) == False     #  False    2 points
	assert most(odd, [1, 2, 3]) == True  #  True     2 points



	assert sigma(sqr, 0, 0) == 0    #  0          2 points
	assert sigma(sqr, 1, 0) == 0    #  0          2 points
	assert sigma(sqr, 0, 4) ==30    #  30         2 points
	assert sigma(sqr, 1, 1) == 1    #  1          2 points
	assert sigma(sqr, 2, 100) ==338349  #  338349     2 points
	
	print "Test pass"
	
test()

