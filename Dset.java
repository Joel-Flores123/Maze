//disjoint set class
//joel flores

public class Dset
{

	int[] arr;
	int[] sizes;

	//given the amount of elements makes a set for each one
	
	Dset(int elements)
	{
	
		arr = new int[elements];
		sizes = new int[elements];

		//make nodes

		for(int counter = 0; counter < elements; counter++)
		{

			arr[counter] = counter;
			sizes[counter] = 1; //only one element per set at the start

		}

	}

	//find the root

	public int find(int s)
	{
	
		int res;

		//base case

		if(arr[s] == s)
		{
		
			res = s;

		}
		else //recursive case
		{
		
			res = find(arr[s]);  //find the parent
				
			//path compression
		
			arr[s] = res; //connect to the root so checking if cells are in the same set really easily

		}

		return res;

	}

	//unite 2 sets

	public void union(int set1, int set2)
	{
	
		//get root of first set

		int r1 = find(set1);
			
		//get root of set 2

		int r2 = find (set2);
		
		//check which is smaller

		if(sizes[r1] < sizes[r2])
		{
		
			arr[r2] = r1; //make smaller elements the parent
		
		}
		else
		{
		
			arr[r1] = r2; //make smaller element the parent
		
		}
		
		sizes[r1] = sizes[r2] + sizes[r1]; //update size
		sizes[r2] = sizes[r1]; //update size

	
	}

	//check if all the sets are connected
	
	boolean checkConnection()
	{
	
		//if every node shares this root that means that everything is connected

		int r = find(arr[0]);
		boolean valid = true;
		int counter = 0;

		while(valid && counter < arr.length)
		{
			
			//if root is not shared not connected

			if(find(arr[counter]) != r)
			{
			
				valid = false;
			
			}
			
			counter++;
		
		}

		return valid;

	}

}
