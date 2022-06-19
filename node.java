//node class to be used with the disjoint set class

public class node
{

	node left = null;
	node right = null;
	node up = null;
	node down = null;
	int val;
	int distance = 2147483647;
	node prevVis = null;

	//make a node and give it a value

	node(int v)
	{
		
		val = v;
	
	}

	//set val if needed

	public void setVal(int v)
	{
	
		val = v;

	}


	//set left

	public void setLeft(node l)
	{
	
		left = l;
	
	}
	
	//set right

	public void setRight(node r)
	{
	
		right = r;
	
	}

	//set up

	public void setUp(node u)
	{
	
		up = u;
	
	}

	//set down

	public void setDown(node d)
	{
	
		down = d;
	
	}
	
	//set previous visitor
	
	public void setPrevVis(node p)
	{
	
		prevVis = p;
	
	}

	//set the distance 
	
	public void setDistance(int d)
	{
	
		distance = d;
		
	}

	//getters

	public int getVal(){return val;} //get val
	public node getLeft(){return left;} //get left
	public node getRight(){return right;} //get right
	public node getUp(){return up;} //get up
	public node getDown(){return down;} //get down
	public int getDistance(){return distance;} //get the distance
	public node getPrevVis(){return prevVis;} //get previous visitor


}
