#!/usr/bin/python

class Zillion():
	def __init__(self, digits):
		if digits == "": raise RuntimeError
		dit = False
		for i in digits:
			if i not in "1234567890, ": raise RuntimeError
		for i in digits:
			if i in "123467890":
				dit = True
		if not dit:raise RuntimeError		
			
		self.degit = [i for i in digits if i != " " if i != ","]
		return None
		
	def increment(self):
		count = -1
		try:
			while self.degit[count] == "9":
				self.degit[count] = "0"
				count -= 1
			else:
				self.degit[count] = str(int(self.degit[count]) + 1)
		except IndexError:
			self.degit = ["1"] + ["0"]*len(self.degit)		
	def isZero(self):
		for i in self.degit:
			if i != "0":
				return False
		else:
			return True		
		
	def toString(self):
		return "".join(self.degit)

