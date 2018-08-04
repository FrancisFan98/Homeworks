class AssociationList<Key, Value>{

	private Node head;
	
	public class Node{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key k, Value v){
			key = k;
			value = v;
			next = null;
			
		}
	}	


	public AssociationList(){
		head = null;
	}
		
	public void delete(Key key){
		
		
		Node left = head;
		Node right = left.next;
		
		while (right != null) {

			if (isEqual(left.key, key)){
				head = right;
				break;
			}
			else if (isEqual(right.key, key)){
				left.next = right.next;
				break;
			}
			else{
				left = right;
				right = right.next;
			}
			
			
		}
	}
	
	

	public Value get(Key key){
		Node current = head;
		while(current != null){
			if(isEqual(current.key, key)){
				return current.value;
			}
			else{
				current = current.next;
			}
			
		}
		throw new IllegalArgumentException();
	}
	
	private boolean isEqual(Key leftKey, Key rightKey){
		if(leftKey == null || rightKey == null){
			return leftKey == rightKey;
		}
		return leftKey.equals(rightKey);
	}
	
	
	public boolean isIn(Key key){
		Node current = head;
		while(current != null){
			if(isEqual(current.key, key)){
				return true;
			}
			else{
				current = current.next;
			}
			
		}
		return false;
	}

	public void put(Key key, Value value){
		
		if(head == null){
			head = new Node(key, value);
			return;
		}
		
		boolean found = false;
		Node current = head;
		while(current != null){
			if(isEqual(current.key, key)){
				found = true;
				current.value = value;
				return;
			}
			else{
				current = current.next;
			}
			
		}
		
		if(!found){
			Node right = head.next;
			head.next = new Node(key, value);
			head.next.next = right;
		}
		
	}
}

class Hogwarts
{

//  MAIN. Make an instance of ASSOCIATION LIST and test it.

	public static void main(String[] args)
	{
		AssociationList<String,String> list = new AssociationList<String,String>();

		System.out.println(list.isIn(null));         //  false         2 points.

		try
		{
			System.out.println(list.get(null));
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No null");             //  No null       2 points.
		}

		list.put(null,        "Wormtail");
		list.put("Ron",       "Lavender");
		list.put("Voldemort", null);
		list.put("Dean",      "Ginny");

		System.out.println(list.isIn("Dean"));       //  true          2 points.
		System.out.println(list.isIn("Ginny"));      //  false         2 points.
		System.out.println(list.isIn("Ron"));        //  true          2 points.
		System.out.println(list.isIn("Voldemort"));  //  true          2 points.
		System.out.println(list.isIn(null));         //  true          2 points.
		System.out.println(list.isIn("Joanne"));     //  false         2 points.

		System.out.println(list.get("Ron"));         //  Lavender      2 points.
		System.out.println(list.get("Dean"));        //  Ginny         2 points.
		System.out.println(list.get("Voldemort"));   //  null          2 points.
		System.out.println(list.get(null));          //  Wormtail      2 points.

		try
		{
			System.out.println(list.get("Joanne"));
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No Joanne");           //  No Joanne     2 points.
		}

		list.delete(null);

		System.out.println(list.isIn(null));         //  false         2 points.

		list.put(null,    null);
		list.put("Harry", "Ginny");
		list.put("Ron",   "Hermione");

		System.out.println(list.isIn(null));         //  true          2 points.
		System.out.println(list.get(null));          //  null          2 points.
		System.out.println(list.get("Harry"));       //  Ginny         2 points.
		System.out.println(list.get("Dean"));        //  Ginny         2 points.
		System.out.println(list.get("Ron"));         //  Hermione      2 points.

		list.delete("Dean");

		try
		{
			System.out.println(list.get("Dean"));
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No Dean");             //  No Dean       2 points.
		}
	}
}
