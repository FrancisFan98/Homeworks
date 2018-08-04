class Deque<Base>{
	
	private class Node{
		
		private Node left;
		private Node right;
		private Base object;
		
		public Node(Base value){
			left = null;
			right = null;
			object = value;
		}
	}
	private Node head;
	
	public Deque(){
		head = new Node(null);
		head.left = head;
		head.right = head;
	}
	
	public void enqueueFront(Base object){
		Node mid = new Node(object);
		mid.left = head.left;
		mid.right = head;	
		head.left.right = mid;	
		head.left = mid;
		
	}
	
	public void enqueueRear(Base object){
		Node mid = new Node(object);
		mid.right = head.right;
		mid.left = head;
		head.right.left = mid;
		head.right = mid;
	}
	
	public Base dequeueFront(){
		if(isEmpty()){
			throw new IllegalStateException();
		}
		Base temp = head.left.object;
		head.left = head.left.left;
		head.left.right = head;
		return temp;
	}
	
	public Base dequeueRear(){
		if(isEmpty()){
			throw new IllegalStateException();
		}
		Base temp = head.right.object;
		head.right = head.right.right;
		head.right.left = head;
		return temp;
	}
	
	public boolean isEmpty(){
		return head.right == head.left && head.right.object == null;
	}
	
}

class ObservationDeque
{

//  MAIN. Test the DEQUE on various example arguments.

	public static void main(String [] args)
	{
		Deque<String> deque = new Deque<String>();

		System.out.println(deque.isEmpty());       // true                2 points.

		try
		{
			System.out.println(deque.dequeueFront());
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("No dequeueFront.");  //  No dequeueFront.   2 points.
		}

		try
		{
			System.out.println(deque.dequeueRear());
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("No dequeueRear.");   //  No dequeueRear.    2 points.
		}

//  Enqueueing to the rear and dequeueing from the rear makes the DEQUE act
//  like a stack.

		deque.enqueueRear("A");
		deque.enqueueRear("B");
		deque.enqueueRear("C");

		System.out.println(deque.isEmpty());       //  false              2 points.

		System.out.println(deque.dequeueRear());   //  C                  2 points.
		System.out.println(deque.dequeueRear());   //  B                  2 points.
		System.out.println(deque.dequeueRear());   //  A                  2 points.

		System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the rear and dequeueing from the front makes the DEQUE act
//  like a queue.

		deque.enqueueRear("A");
		deque.enqueueRear("B");
		deque.enqueueRear("C");

		System.out.println(deque.dequeueFront());  //  A                  2 points.
		System.out.println(deque.dequeueFront());  //  B                  2 points.
		System.out.println(deque.dequeueFront());  //  C                  2 points.

		System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the front makes the DEQUE act
//  like a stack.

		deque.enqueueFront("A");
		deque.enqueueFront("B");
		deque.enqueueFront("C");

		System.out.println(deque.dequeueFront());  //  C                  2 points.
		System.out.println(deque.dequeueFront());  //  B                  2 points.
		System.out.println(deque.dequeueFront());  //  A                  2 points.

		System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the rear makes the DEQUE act
//  like a queue.

		deque.enqueueFront("A");
		deque.enqueueFront("B");
		deque.enqueueFront("C");

		System.out.println(deque.dequeueRear());   //  A                  2 points.
		System.out.println(deque.dequeueRear());   //  B                  2 points.
		System.out.println(deque.dequeueRear());   //  C                  2 points.

		System.out.println(deque.isEmpty());       //  true               2 points.
	}
}
