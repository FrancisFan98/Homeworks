#!/usr/bin/python
class Random():
	
	def __init__(self, s):
		if not (1 <= s and s <= 2**32-2):
			raise ValueError("seed should between 1 and 2**32-2")

		elif isinstance(s, int):
			self.seed = s
		
		else:
			raise ValueError("seed shoud be a integer")
	
	def next(self, range):
		
		self.seed = (7**5 * self.seed) % (2**31-1)  

		return self.seed %range
	
	def choose(self, charactors):
		index = Random.next(self, len(charactors))
		return charactors[index]
	
import collections
class Words():
	
	def __init__(self,seed):
		
		self.__first = ""
		self.__follow = collections.defaultdict(list)
		self.__random = Random(seed)
		
	def add(self, word):
		self.__first += word[0]
		for i in range(len(word)-1):
			a = word[i]
			self.__follow[a].append(word[i+1])
			
	def make(self, size):
		if size == 0:
			return ""
			
		rs = [self.__random.choose(self.__first)]
		
		count = 1
		while count < size:
			
			cc = self.__follow[rs[-1]]
			if not cc:
				rs.append(self.__random.choose(self.__first))
			else:rs.append(self.__random.choose(cc))
			count += 1
		return "".join(rs)


def addNames(prez):
	prez.add('Washington') 
	prez.add('Adams') 
	prez.add('Jefferson') 
	prez.add('Madison') 
	prez.add('Monroe') 
	prez.add('Adams') 
	prez.add('Jackson') 
	prez.add('Vanburen') 
	prez.add('Harrison') 
	prez.add('Tyler') 
	prez.add('Polk') 
	prez.add('Taylor') 
	prez.add('Fillmore') 
	prez.add('Pierce') 
	prez.add('Buchanan') 
	prez.add('Lincoln') 
	prez.add('Johnson') 
	prez.add('Grant') 
	prez.add('Hayes') 
	prez.add('Garfield') 
	prez.add('Arthur') 
	prez.add('Cleveland') 
	prez.add('Harrison') 
	prez.add('Cleveland') 
	prez.add('Mckinley') 
	prez.add('Roosevelt') 
	prez.add('Taft') 
	prez.add('Wilson') 
	prez.add('Harding') 
	prez.add('Coolidge') 
	prez.add('Hoover') 
	prez.add('Roosevelt') 
	prez.add('Truman') 
	prez.add('Eisenhower') 
	prez.add('Kennedy') 
	prez.add('Johnson') 
	prez.add('Nixon') 
	prez.add('Ford') 
	prez.add('Carter') 
	prez.add('Reagan') 
	prez.add('Bush') 
	prez.add('Clinton') 
	prez.add('Bush') 
	prez.add('Obama') 
	prez.add('Trump')

prez = Words(180120)

addNames(prez) 
names = {}
print prez.make(5)	

