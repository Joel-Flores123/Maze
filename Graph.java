import java.util.LinkedList;

//Joel Flores
//graph class so we can find the shortest path
//

public class Graph
{

	node arr [];

	Graph(int size)
	{
	
		//make array of nodes

		arr = new node[size];

		//populate the array with nodes

		for(int counter = 0; counter < size; counter++)
		{
		
			arr[counter] = new node(counter);
		
		}
	
	}
	
	//get a specified vertex

	public node getVertex(int n){return arr[n];}

	//reset the nodes for a find path

	private void reset()
	{
	
		//reset distnace and previousVis

		for(int counter = 0; counter < arr.length; counter++)
		{
		
			arr[counter].setDistance(2147483647);
			arr[counter].setPrevVis(null);
		
		}
	
	}

	//given start find the shortest path to any node

	public void findPath(int start)
	{
	
		node cur = null; //the end node

		//make a queue

		LinkedList<node> queue = new LinkedList<node>();	
		
		arr[start].setDistance(0); //set distance
		queue.add(arr[start]); //add the first node into the queue

		//while queue is not empty

		while(queue.peekFirst() != null)
		{
	
			cur = queue.poll();

			//check if the edges are null 

			if(cur.getLeft() != null) //check left
			{
			
				if(cur.getLeft().getDistance() > (cur.getDistance() + 1))
				{
				
					cur.getLeft().setDistance(cur.getDistance() + 1);
					cur.getLeft().setPrevVis(cur);
					queue.add(cur.getLeft()); //throw onto the queue
				
				}


			}
			
			//check up

			if(cur.getUp() != null)
			{
			
				if(cur.getUp().getDistance() > (cur.getDistance() + 1))
				{
				
					cur.getUp().setDistance(cur.getDistance() + 1);
					cur.getUp().setPrevVis(cur);
					queue.add(cur.getUp()); //throw onto the queue
				
				}	
			
			}
			
			//check down

			if(cur.getDown() != null)
			{
			
			
				if(cur.getDown().getDistance() > (cur.getDistance() + 1))
				{
				
					cur.getDown().setDistance(cur.getDistance() + 1);
					cur.getDown().setPrevVis(cur);
					queue.add(cur.getDown()); //throw onto the queue
				
				}	

			}
			
			//check right

			if(cur.getRight() != null)
			{
			
				if(cur.getRight().getDistance() > (cur.getDistance() + 1))
				{
				
					cur.getRight().setDistance(cur.getDistance() + 1);
					cur.getRight().setPrevVis(cur);
					queue.add(cur.getRight()); //throw onto the queue
				
				}	
			
			}	

		}


	}


}
