class Poly{
	
	private Term head;
	
	private class Term{
		private int coef;
		private int expo;
		private Term next;
		
		private Term(){
			coef = 0;
			expo = -1;
			next = null; 
		}
		public String toString(){
			return String.valueOf(coef) + "x" + String.valueOf(expo);
		}
	}
	
	
	
	public Poly(){
		head = null;
	}
	
	public Poly term(int coef, int expo){
		if (expo < 0){
			throw new IllegalArgumentException();
		}
		Term temp = new Term();
		Poly rs = new Poly();
		if(head == null){
			temp.next = head;
			temp.coef = coef;
			temp.expo = expo;
			rs.head = temp;
			return rs;
		}
		Term left = head;
		Term right = head.next;
		if(left.expo < expo){
			temp.next = left;
			temp.coef = coef;
			temp.expo = expo;
			rs.head = temp;
			return rs;
		}
		else {
			while (true) {
				if(right != null){
					if(right.expo == expo){
						throw new IllegalArgumentException();
					}
					else if(right.expo < expo){
						temp.next = right;
						temp.expo = expo;
						temp.coef = coef;
						left.next = temp;
						rs.head = head;
						return rs;
					}
					else{
						left = right;
						right = right.next;
					}
					
				}
				else{
					temp.next = right;
					temp.coef = coef;
					temp.expo = expo;
					left.next = temp;
					rs.head = head;
					return rs;
				}
			}
		}
		
		
 	}
	
	

	public Poly plus(Poly that){
		Poly rs = new Poly();
		
		Term current = head;
		while (current != null) {
			rs = rs.term(current.coef, current.expo);
			current = current.next;
		}
		current = that.head;
		while (current != null) {
			rs.add(current.coef, current.expo);
			current = current.next;
		}
		return rs;
		
	}
	
	

	private void add(int coef, int expo){
		Term current = head;
		
		while (true) {
			if(current != null){
				if(current.expo == expo){
					current.coef = current.coef + coef;
					current = current.next;
									}
				else{
					current = current.next;
				}
			}
			else{
				break;
			}
		}
		
	}

	
	public Poly minus(){
		Poly rs = new Poly();
		
		Term current = head;
		while (current != null) {
			rs = rs.term(-current.coef, current.expo);
			current = current.next;
		}
		return rs;
	}

//  APPEND EXPONENT. Append Unicode subscript digits for EXPO to BUILDER.

	private void appendExponent(StringBuilder builder, int expo)
	{
		if (expo < 0)
		{
			throw new IllegalStateException("Bad exponent " + expo + ".");
		}
		else if (expo == 0)
		{
			builder.append('⁰');
		}
		else
		{
			appendingExponent(builder, expo);
		}
	}

//  APPENDING EXPONENT. Do all the work for APPEND EXPONENT when EXPO is not 0.

	private void appendingExponent(StringBuilder builder, int expo)
	{
		if (expo > 0)
		{
			appendingExponent(builder, expo / 10);
			builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
		}
	}



	public String toString(){
		if (head == null){
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		Term current = head;
		while(current != null){
			String str = sb.toString();
			
			if (!str.equals("") && str.substring(str.length()-1, str.length()).equals("+") && current.coef < 0){
				sb = sb.delete(str.length()-1, str.length());
			}
			
				
			
			if (current.coef == -1)
				sb.append("-");
			else if(current.coef != 1)
				sb.append(current.coef);
				
			sb.append("x");
			if(current.expo != 1)
			appendExponent(sb, current.expo);
			
			sb.append("+");
			current = current.next;
		}
		return sb.toString().substring(0, sb.toString().length()-1);
		
	}


}




class PollyEsther 
{ 
	public static void main(String[] args) 
	{ 
		Poly p0 = new Poly(); 
		Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2); 
		Poly p2 = new Poly().term(2, 1).term(3, 2); 
		Poly p3 = p2.minus(); 
 
		System.out.println(p0);           //  0 
		System.out.println(p1);           //  1x3 + 1x2 + 1x1 
		System.out.println(p2);           //  3x2 + 2x1 
		System.out.println(p3);           //  −3x2 − 2x1 
 
		System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1 
		System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1 
		
		System.out.println(p3.minus());   //  3X2 +2x1
		
	} 
}

