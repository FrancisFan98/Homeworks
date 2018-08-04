class Map<Key, Value>{
	private Key[] keys;
	private Value[] values;
	private int count; 
	private int putcount;
	
	public Map(int length){
		if(length < 0){
			throw new IllegalArgumentException();
		}
		else{
			keys = (Key[]) new Object[length];
			values = (Value[]) new Object[length];

			putcount = 0;
			count = 0;
		}
		
	}
	
	public Value get(Key key){
		count = 0;
		for(Key i : keys){
			if(i == key){
				return values[count];
			}
			else{
				count += 1;
			}
		}
		throw new IllegalArgumentException();
	}
	
	
	private boolean isEqual(Key leftKey, Key rightKey){
		if(leftKey == null || rightKey == null){
			return false;
		}
		return leftKey.equals(rightKey);
	}
	
	
	public boolean isIn(Key key){
		for(Key i : keys){
			if(i == key){
				return true;
			}
		}
		return false;
	}



	public void put(Key key, Value value){
		count = 0;
		
		
		for(Key i : keys){
			
			
			if(i == null && count <= putcount){
				keys[count] = key;
				values[count] = value;
				putcount+=1;
				break;
			}
			
			if(i == key){
				values[count] = value;
				break;
			}
			if (putcount > keys.length){
				throw new IllegalStateException();
			}
			count += 1;
			
			
		}
		
		
	}
	
	

	private int where(Key key){
		count = 0;

		for(Key i : keys){
			if(i == key){
				return count;
			}
			else{
				count +=1;
			}
		}
		return -1;
	}
}

class Hogwarts
{

//  MAIN. Make an instance of MAP and test it.

	public static void main(String [] args)
	{
		Map<String, String> map;

		try
		{
			map = new Map<String, String>(-5);
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No negatives");       //  No negatives  2 points.
		}

		map = new Map<String, String>(5);

		map.put("Harry",     "Ginny");
		map.put("Ron",       "Lavender");
		map.put("Voldemort", null);
		map.put(null,        "Wormtail");

		System.out.println(map.isIn("Harry"));      //  true          2 points.
		System.out.println(map.isIn("Ginny"));      //  false         2 points.
		System.out.println(map.isIn("Ron"));        //  true          2 points.
		System.out.println(map.isIn("Voldemort"));  //  true          2 points.
		System.out.println(map.isIn(null));         //  true          2 points.
		System.out.println(map.isIn("Joanne"));     //  false         2 points.

		System.out.println(map.get("Harry"));       //  Ginny         2 points.
		System.out.println(map.get("Ron"));         //  Lavender      2 points.
		System.out.println(map.get("Voldemort"));   //  null          2 points.
		System.out.println(map.get(null));          //  Wormtail      2 points.

		try
		{
			System.out.println(map.get("Joanne"));
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No Joanne");          //  No Joanne     2 points.
		}

		map.put("Ron",   "Hermione");
		map.put("Albus", "Gellert");
		map.put(null,    null);

		System.out.println(map.isIn(null));         //  true          2 points.
		System.out.println(map.isIn("Albus"));      //  true          2 points.

		System.out.println(map.get("Albus"));       //  Gellert       2 points.
		System.out.println(map.get("Harry"));       //  Ginny         2 points.
		System.out.println(map.get("Ron"));         //  Hermione      2 points.
		System.out.println(map.get("Voldemort"));   //  null          2 points.
		System.out.println(map.get(null));          //  null          2 points.

		try
		{
			map.put("Draco", "Pansy"); 
		}
		catch (IllegalStateException minnesota)
		{
			System.out.println("No Draco");           //  No Draco      2 points.
		}
	}
}

