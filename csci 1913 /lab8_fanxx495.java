class RunnyStack<Base>{
	
	public class Run{
		public Base base;
		public int length;
		private Run next;
		
		private Run(Base base, Run next){
			
			this.base = base;
			this.length = 1;
			this.next = next;
			
		}
		
	}
	
	public Run top;
	private int depth;
	private int numOfRuns;
	
	public RunnyStack(){
		top = null;
		depth = 0;
		numOfRuns = 0;
	}
	
	public int depth(){
		return depth;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public Base peek(){
		if(isEmpty())
		{
			throw new IllegalStateException();
		}
		else{
			return top.base;
		}
	}
	
	public void pop(){
		if (isEmpty())
		{
			throw new IllegalStateException();
		}
		else
		{
			top.length -= 1;
			depth -= 1;
			if(top.length == 0)
			{
				top = top.next;
				numOfRuns -= 1;
			}
		}
	}
	
	public boolean isEqual(Object a, Object b){
		if (a == null || b == null){
			return a == b;
		}
		else{
			return a.equals(b);
		}
	}
	
	
	public void push(Base object){
		depth += 1;
		if(isEqual(top, null)){
			numOfRuns += 1;
			top = new Run(object, null);
		}
		else if(isEqual(top.base, object)){
			top.length += 1;
		}
		else{
			numOfRuns += 1;
			top = new Run(object, top);
			
		}
	}
	
	public int runs(){
		return numOfRuns;
	}
}


class Camembert
{
	public static void main(String [] args)
	{
		RunnyStack<String> s = new RunnyStack<String>();

		System.out.println(s.isEmpty());         //  true       1 point
		System.out.println(s.depth());           //  0          1 point
		System.out.println(s.runs());            //  0          1 point

		try
		{
			s.pop();
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("No pop");          //  No pop     1 point
		}

		try
		{
			System.out.println(s.peek());
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("No peek");         //  No peek    1 point
		}
 
		s.push("A");
		System.out.println(s.peek());            //  A          1 point
		System.out.println(s.depth());           //  1          1 point
		System.out.println(s.runs());            //  1          1 point

		System.out.println(s.isEmpty());         //  false      1 point

		s.push("B");
		System.out.println(s.peek());            //  B          1 point
		System.out.println(s.depth());           //  2          1 point
		System.out.println(s.runs());            //  2          1 point

		s.push("B");
		System.out.println(s.peek());            //  B          1 point
		System.out.println(s.depth());           //  3          1 point
		System.out.println(s.runs());            //  2          1 point

		s.push("B");
		System.out.println(s.peek());            //  B          1 point
		System.out.println(s.depth());           //  4          1 point
		System.out.println(s.runs());            //  2          1 point

		s.push("C");
		System.out.println(s.peek());            //  C          1 point
		System.out.println(s.depth());           //  5          1 point
		System.out.println(s.runs());            //  3          1 point

		s.push("C");
		System.out.println(s.peek());            //  C          1 point
		System.out.println(s.depth());           //  6          1 point
		System.out.println(s.runs());            //  3          1 point

		s.pop();
		System.out.println(s.peek());            //  C          1 point
		System.out.println(s.depth());           //  5          1 point
		System.out.println(s.runs());            //  3          1 point

		s.pop();
		System.out.println(s.peek());            //  B          1 point
		System.out.println(s.depth());           //  4          1 point
		System.out.println(s.runs());            //  2          1 point

		s.pop();
		System.out.println(s.peek());            //  B          1 point
		System.out.println(s.depth());           //  3          1 point
		System.out.println(s.runs());            //  2          1 point

		s.pop();
		s.pop();

		System.out.println(s.peek());            //  A          1 point
		System.out.println(s.depth());           //  1          1 point
		System.out.println(s.runs());            //  1          1 point

		s.pop();
		
		System.out.println(s.isEmpty());         //  true       1 point
		System.out.println(s.depth());           //  0          1 point
		System.out.println(s.runs());            //  0          1 point

		try
		{
			System.out.println(s.peek());
		}
		catch (IllegalStateException ignore)
		{
			System.out.println("No peek");         //  No peek    1 point
		}
	}
}

