
class PriorityQueue<Base> 
{ 
	public class Node 
	{ 
		public Base object; 
		public int  rank; 
		public Node left; 
		public Node right; 
 
		private Node(Base object, int rank) 
		{ 
			this.object = object; 
			this.rank = rank; 
			left = null; 
			right = null; 
		} 
	} 
 
	public Node root;  //  Root node of the BST. 
 	
	public PriorityQueue(){
		root = new Node(null, 4);
	}

	

	public void enqueue(Base object, int rank){
		if(rank < 0 || rank > 7){
			throw new IllegalArgumentException();
		}
		
		if(rank < root.rank){
			if(root.left == null){
				root.left = new Node(object, rank);
			}
			else{
				PriorityQueue temp = new PriorityQueue();
				temp.root = root.left;
				temp.enqueue(object, rank);
				root.left = temp.root;
			}
		}
		else if(rank >= root.rank){
			if(root.right == null){
				root.right = new Node(object, rank);
			}
			else{
				PriorityQueue temp = new PriorityQueue();
				temp.root = root.right;
				temp.enqueue(object, rank);
				root.right = temp.root;
			}
		}
		
	}
	

	
	public Node smallest(Node root){
		Node current = root;
		while (current.left != null) {
			current = current;
		}
		return current;
	}
	
	public Node secondFormer(int rank){
		Node current = root;
			while(current != null){
				if(current.right.rank == rank||current.left.rank == rank){
					return current;
				}
				else if(rank < current.rank){
					current = root.left;
				}
				else{
					current = root.right;
				}
				
			}
		return null;
	}
	
	public Node findTarget(int rank){
		Node current = root;
		
		while(current != null){
			if(current.rank == rank && current.object != null){
				return current;
			}
			else if (current.rank < rank){
				current = current.right;
			}
			else{
				current = current.left;
			}
		}
		return null;
	}
	
	public Base dequeue(){
		if(root.left == null && root.right == null){
			throw new IllegalStateException();
		}
		
		for (int i = 0; i <= 7; i++) {
			Node target = findTarget(i);
			if(target == null){
				continue;
			}
			
			Node outFormer = secondFormer(i);

			if(target.left == null && target.right == null){
				if(outFormer.left == target){
					Node temp = outFormer.left;
					outFormer.left = null;
					return temp.object;
				}
				else{
					Node temp = outFormer.right;
					outFormer.right = null;
					return temp.object;
				}
			}
			
			else if(target.left == null){
				if(outFormer.left == target){
					Node temp = outFormer.left;
					outFormer.left = target.right;
					return temp.object;
				}
				else{
					Node temp = outFormer.right;
					outFormer.right = target.right;
					return temp.object;
				}
			}
			
			else if(target.right == null){
				if(outFormer.right == target){
					Node temp = outFormer.right;
					outFormer.right = target.left;
					return temp.object;
				}
				else{
					Node temp = outFormer.left;
					outFormer.left = target.right;
					return temp.object;
				}
			}
			
			else{
				Node min = smallest(target.right);
				
				Node above = target.right;
				if (min == above){
					outFormer.left = min;
					return target.object;
				}
				while (above != null) {
					if (above.left != min){
						above = above.left;
					}
				}
				min.left = target.left;
				min.right = target.right;
				outFormer.left = min;
				above.left = null;
				return target.object;
			}
		}
		throw new IllegalStateException();
	}
	public boolean isEmpty(){
		if(root.left == null && root.right == null){
			return true;
		}
		else{
			return false;
		}
	}

}


class Snobbery
{

//  MAIN. Queue them up.

	public static void main(String[] args)
	{
		PriorityQueue<String> queue = new PriorityQueue<String>();

		System.out.println(queue.isEmpty());  //  true        2 points

		try
		{
			System.out.println(queue.dequeue());
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("Blimey!");      //  Blimey!     2 points
		}

		queue.enqueue("Lancelot",  5);
		queue.enqueue("Fawlty",    7);
		queue.enqueue("Elizabeth", 0);
		queue.enqueue("Charles",   1);
		queue.enqueue("Turing",    7);

		try
		{
			queue.enqueue("Zeus", -100);
		}
		catch (IllegalArgumentException ignore)
		{
			System.out.println("No gods!");     //  No gods!    2 points
		}

		System.out.println(queue.isEmpty());  //  false       2 points
		
		System.out.println(queue.dequeue());  //  Elizabeth   2 points
		System.out.println(queue.dequeue());  //  Charles     2 points
		System.out.println(queue.dequeue());  //  Lancelot    2 points
		System.out.println(queue.dequeue());  //  Turing      2 points
		System.out.println(queue.dequeue());  //  Fawlty      2 points
		
		
	}

}
