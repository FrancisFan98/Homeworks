#!/usr/bin/python

def left(e):
	return e[0]

def op(e):
	return e[1]

def right(e):
	return e[2]

operators = "+-*/"
reverOpers = "-+/*"

def oper(s):
	return reverOpers[operators.find(s)]
	

def isInside(v, e):
	result = False
	if v in "+-*/=":
		return False
	if v in e:
		result = True
		return result	
	
	if isinstance(e[0], tuple):
		result = isInside(v, e[0])
	try:	
		if isinstance(e[2], tuple):
			result = isInside(v, e[2])
	except:
		pass
	return result	
		


def solve(v, q):
	if isInside(v, left(q)):
		return solving(v, q)
	elif isInside(v, right(q)): 
		return solving(v, (right(q) , op(q) , left(q)))
	return None
	
puncs = "-+/*="

def solving(v,q):
	if left(q) == v:
		return q
	else:
		first1, first2, firstop, second, third = left(left(q)), right(left(q)), op(left(q)), op(q), right(q)
		if isInside(v, first1):
			new = (first1, "=", (third, oper(firstop), first2))
			return solve(v, new)
		if isInside(v, first2):
			if firstop in "*+":
				new = (first2, "=" , (third, oper(firstop), first1))
				return solve(v,new)
			else:
				new = (first2, "=", (first1, firstop, third))
				return solve(v, new)
		else:
			return None
		

def test():
	assert isInside('x', 'x') == True
	assert isInside('x', 'y') == False		                  
	assert isInside('x', ('x', '+', 'y')) == True
	assert isInside('x', ('a', '+', 'b')) == False           #  False  2 points
	assert isInside('+', ('a', '+', 'b')) == False            #  False  2 points
	assert isInside('x', (('m', '*', 'x'), '+', 'b')) == True #  True   2 points
	assert solve('x', (('a', '+', 'x'), '=', 'c')) == ('x', '=', ('c', '-', 'a'))
	assert solve('x', (('x', '+', 'b'), '=', 'c')) == ('x', '=', ('c', '-', 'b'))
	assert solve('x', (('x', '-', 'b'), '=', 'c')) ==  ('x', '=', ('c', '+', 'b'))
	assert solve('x', (('x', '-', 'b'), '=', 'c')) == ('x', '=', ('c', '+', 'b'))
	assert solve('x', (('a', '*', 'x'), '=', 'c')) == ('x', '=', ('c', '/', 'a'))
	assert solve('x', (('x', '*', 'b'), '=', 'c')) == ('x', '=', ('c', '/', 'b'))
	assert solve('x', (('a', '/', 'x'), '=', 'c')) == ('x', '=', ('a', '/', 'c'))
	assert solve('x', (('x', '/', 'b'), '=', 'c')) == ('x', '=', ('c', '*', 'b'))
	assert solve('y', ('y', '=', (('m', '*', 'x'), '+', 'b'))) == ('y', '=', (('m', '*', 'x'), '+', 'b'))
	assert solve('x', ('y', '=', (('m', '*', 'x'), '+', 'b'))) == ('x', '=', (('y', '-', 'b'), '/', 'm'))
	assert solve('a', (('b', '+', 'c'), '=', ('d', '*', (('a', '/', 'e'), '-', 'f')))) == ('a', '=', (((('b', '+', 'c'), '/', 'd'), '+', 'f'), '*', 'e'))
	
	return True
	
print test()
	

	









