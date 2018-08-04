class FamilyTree{
	private Node root;
	
	private class Node{
		private Node father;
		private Node mother;
		private String name;
	
		public Node(String a){
			father = null;
			mother = null;
			name = a;
		}	
	}
	
	public FamilyTree(String ego){
		root = new Node(ego);
	}
	
	public Node find(String name){
		if(root.name.equals(name)){
			return root;
		}
		else{
			Node fatherN = find(name, root.father);
			Node motherN = find(name, root.mother);
			
			if (fatherN == null){
				return motherN;
			}
			else{
				return fatherN;
			}
		}
		
	}


	public Node find(String name, Node root){
		if (root == null){
			return null;
		}
		if(root.name.equals(name)){
			return root;
		}
		Node fatherN = null;
		Node motherN = null;
		
		if (root.father != null) {
			fatherN = find(name, root.father);
		}
		if (root.mother != null) {
			motherN = find(name, root.mother);
		}
		
		if (fatherN == null){
			return motherN;
		}
		else{
			return fatherN;
		}
		
	}
	
	

	public void addParents(String ego, String father, String mother){
		Node target = find(ego);
		if(target == null){
			throw new IllegalArgumentException();
		}
		else{
			target.father = new Node(father);
			target.mother = new Node(mother);
		}
	}
	
	public boolean isDescendant(String ego, String ancestor){
		Node a = find(ancestor);
		if (a == null){
			return false; 
		}
		
		if(root.name == ego){
			return isDescendant(root, a);
		}
		
		Node e = find(ego);
		
		if(a == null || e == null){
			return false;
		}
		
		if(e.name.equals(a.name)){
			return true;
		}
		
		

		else{
			boolean father = isDescendant(e.mother, a);
			boolean mother = isDescendant(e.father, a);
			
			if(father || mother){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public boolean isDescendant(Node root, Node ancestor){
		if(root == ancestor){
			return true;
		}
		
		if(root == null){
			return false;
		}
		
		
		else{
			boolean father = isDescendant(root.father, ancestor);
			boolean mother = isDescendant(root.mother, ancestor);
			
			if(father || mother){
				return true;
			}
			else{
				return false;
			}
			
		}
		
	}
}

class Pottery
{

//  MAIN. Harry Potter and the Hairier Pottery.

	public static void main(String [] args) 
	{
		FamilyTree family = new FamilyTree("Al");

		family.addParents("Al",    "Harry",  "Ginny");
		family.addParents("Harry", "James",  "Lily" );
		family.addParents("Ginny", "Arthur", "Molly");

		try
		{
			family.addParents("Joanne", "Peter", "Anne");
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No Joanne.");  //  2 No Joanne.
		}

		System.out.println(family.isDescendant("Joanne", "Joanne"));  //  2 false

		System.out.println(family.isDescendant("Al", "Al"));          //  2 true
		System.out.println(family.isDescendant("Al", "Harry"));       //  2 true
		System.out.println(family.isDescendant("Al", "Ginny"));       //  2 true
		System.out.println(family.isDescendant("Al", "James"));       //  2 true
		System.out.println(family.isDescendant("Al", "Lily"));        //  2 true
		System.out.println(family.isDescendant("Al", "Arthur"));      //  2 true
		System.out.println(family.isDescendant("Al", "Molly"));       //  2 true
		System.out.println(family.isDescendant("Al", "Joanne"));      //  2 false

		System.out.println(family.isDescendant("Harry", "Harry"));    //  2 true
		System.out.println(family.isDescendant("Harry", "Al"));       //  2 false
		System.out.println(family.isDescendant("Harry", "James"));    //  2 true
		System.out.println(family.isDescendant("Harry", "Lily"));     //  2 true
		System.out.println(family.isDescendant("Harry", "Ginny"));    //  2 false
		System.out.println(family.isDescendant("Harry", "Arthur"));   //  2 false
		System.out.println(family.isDescendant("Harry", "Molly"));    //  2 false
		System.out.println(family.isDescendant("Harry", "Joanne"));   //  2 false

		System.out.println(family.isDescendant("Ginny", "Arthur"));   //  2 true
		System.out.println(family.isDescendant("Arthur", "Ginny"));   //  2 false
	}  
}
