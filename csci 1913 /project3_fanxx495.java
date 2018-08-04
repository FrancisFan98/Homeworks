

class BinarySearchTree{
	
	private class LinkedNode{
		private int value;
		private LinkedNode next;
		
		public LinkedNode(){
			value = 0;
			next = null;
		}
		
		public String toString(){
			if(next == null){
				return String.format("%05d", value);
			}
			else{
			String rs = "";
			rs = rs + String.format("%05d", value) + " " + next.toString();
			return rs;
			}	
			
		}
	}
	
	
	private class Node{
		private String name;
		private LinkedNode sequence;
		private Node left;
		private Node right;
		
		public Node(){
			name = null;
			sequence = new LinkedNode();
			left = null;
			right = null;
		}
		
		public void add(int num){
			LinkedNode l = new LinkedNode();
			l.value = num;
			LinkedNode temp = this.sequence;
			while(temp.value != 0 ){
				if(temp.value == num)
				return;
				if (temp.next == null) {
					temp.next = l;
					break;
				}
				temp = temp.next;

			}
				
		}
		
		public String toString(){	
			return (name + " " + sequence.toString());
		}
		
		public void traverse(){
			if(left != null)
			left.traverse();
			System.out.println(this.toString());
			if(right != null)
			right.traverse();
		}
		
	}
	
	public Node root;
	
	public BinarySearchTree(){
		root = new Node();
	}
	
	
	
	// return the Node object of the specific name, if not found, return null;
	
	public Node search(String name){
		Node current = root;
		if (current != null){
			if(current.name == name){
				return current;
			}
			else{
				if(name.compareTo(current.name) < 0) {
					current = current.left;
				}
				else {
					current = current.right;
				}
			}
		}
		return null;
	}
	
	public void add(int num, String name){
		if(num == 0 || name == null){
			throw new IllegalArgumentException();
		}
		Node current = root;
		if (current.name == null) {
			current.name = name;
			LinkedNode t = new LinkedNode();
			t.value = num;
			current.sequence = t;
			return;
		}
		while (current.name != null) {
			if(current.name == name){
				current.add(num);
				return;
			}
			else if(name.compareTo(current.name) < 0){
				if (current.left == null) {
					current.left = new Node();
					current.left.name = name;
					current.left.sequence = new LinkedNode();
					current.left.sequence.value = num;
					return;
				}
				else{
					current = current.left;
				}
			}
			else{
				if (current.right == null) {
					current.right = new Node();
					current.right.name = name;
					current.right.sequence = new LinkedNode();
					current.right.sequence.value = num;
					return;
				}
				else{
				current = current.right;
				}
			}
		}
		throw new IllegalStateException();
	}
	
	
	
	public String toString(){
		if(root.left != null)
		root.left.traverse();
		System.out.println(root);
		if(root.right != null)
		root.right.traverse();
		return "";
	}
	
}
// Nomenclator class cannot be run on my computer, so I created a test class myself
class Untitled {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(1, "Francis");
		bst.add(13, "Francis");
		bst.add(1211, "Francis");

		bst.add(2, "Daisy");
		bst.add(223, "Daisy");
		bst.add(22312, "Daisy");


		bst.add(3, "yuchen");
		bst.add(123,"zhenyu");
		bst.add(11, "Fan");
		bst.add(213, "Wang");
		System.out.println(bst);		
	}
}




