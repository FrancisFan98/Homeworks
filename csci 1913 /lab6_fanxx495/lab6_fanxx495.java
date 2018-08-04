
class BinaryVsLinear
{

	private static int linearSearch(int key, int[] array)
	{
		int count = 0;
		for(int i : array){
			if (i == key){
				return count + 1;
			}
			count += 1;
		}
		return -1;

//  Your code goes here.

	}

	private static int binarySearch(int key, int[] array)
	{
		int count = 0;
		
		if (array.length == 1){
			return 1;
		}
		if (array.length == 0){
			return 0;
		}
		int first = 0;
		int last = array.length -1;
		while(true){
			if (first >= last){
				break;
			}
			int mid = (first + last) /2;

			count += 1;
			if(array[mid] == key){
				
				break;
			}
			
			count +=1;
			if (array[mid] > key){
				last = mid;
				continue;
			}
			
			count +=1;
			if (array[mid] < key){
				first = mid +1;
			}
			
		}
	return count;

	}


	public static void main(String[] args)
	{
		for (int length = 1; length <= 30; length += 1)
		{
			int[] array = new int[length];
			for (int index = 0; index < length; index += 1)
			{
				array[index] = index;
			}

			double linearTotal = 0.0;
			double binaryTotal = 0.0;
			for (int element = 0; element < length; element += 1)
			{
				linearTotal += linearSearch(element, array);
				binaryTotal += binarySearch(element, array);
			}

			double linearAverage = linearTotal / length;
			double binaryAverage = binaryTotal / length;
			System.out.println(length + " " + linearAverage + " " + binaryAverage);
		}
	}
}
