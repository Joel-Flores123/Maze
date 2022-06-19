import java.io.*;
import java.util.Scanner;

public class Main
{


	public static void main(String args [])
	{

		Scanner scan = new Scanner(System.in);

		int n;
		int m;
		
		//get n x m from user

		System.out.print("Enter the n for your n x m maze: ");
		n = scan.nextInt();

		System.out.print("Enter the m for your n x m maze: ");
		m = scan.nextInt();

		String maze[][] = generateMaze(n,m); //generate the maze

	}

	//generate a maze array

	public static String[][] generateMaze(int n, int m)
	{
	
		String maze [][] = new String [n] [m];
		
		//make the walls initially 

		for(int counter = 0; counter < n; counter++)
		{
		
			for(int count = 0; count < m; count++)
			{
			
				//generate walls

				if(count == 0)
				{
				
					maze[counter][count] = "|_|";
				
				}
				else
				{
				
					maze[counter][count] = "_|";
				
				}

			
			}
		
		}

		//make openings at the start and the end to make it clear

		maze[0][0] = " _|";
		maze[n - 1][m - 1] = " ";
		
		//print maze and return
		
		removeWalls(maze,n,m);
		return maze;

	}

	//remove walls

	private static void removeWalls(String maze[][], int n, int  m)
	{
	
		//make a Dset to know if cells can reach each other
	
		Dset set = new Dset(n * m);
		Graph graph = new Graph(n * m);

		// x and y coords to choose which wall we tear down

		int x;
		int y;
		int randC = 0;

		while(!set.checkConnection()) //while all cells are not connected	
		{
		
			//randomly get coordinates

			x = (int)(Math.random()*(n));
			y = (int)(Math.random()*(m));

			//if x is in the leftmost side

			if(y == 0)
			{
					
				randC = (int)(Math.random() * (maze[x][y].length() - 1) + 1) ;
					
			}
			else
			{
									
				randC = (int)(Math.random() * (maze[x][y].length())); //anywhere else we can just break other walls	
				
			}

			int eOne = (x * m) + y; //get the first element
			int eTwo = 0;	

			//after locating a wall to destroy we need to calculate what element it is and which parts to unify
			
			if(maze[x][y].charAt(randC) == '|' && y < ( m - 1) )
			{
				
				//calculate the second element to the right

				eTwo = ((x * m) + (y + 1));
				
			}	
			else
			if(maze[x][y].charAt(randC) == '_' && x < (n - 1))
			{
			
				eTwo = (((x + 1) * m) + y); //calculate bottom element
			
			}
			else //if neither mark it as do nothing
			{
			
				eTwo = -1;
			
			}

			//only unite them if they are not in the same class

			if(eTwo != -1 && set.find(eOne) != set.find(eTwo))
			{
				
				set.union(eOne, eTwo); //unite they are not in the same class
			
				//now set up the graph
				
				//figure out if it was a right or down wall we tore down

                       		if(maze[x][y].charAt(randC) == '|' && y < ( m - 1) )
                        	{

					graph.getVertex(eOne).setRight(graph.getVertex(eTwo));
					graph.getVertex(eTwo).setLeft(graph.getVertex(eOne));
		
                	        }
                       		else //otherwise we broke a down wall
                        	{
							
					graph.getVertex(eOne).setDown(graph.getVertex(eTwo));
					graph.getVertex(eTwo).setUp(graph.getVertex(eOne));

                        	}

				String temp = ""; //temp string to replace the maze counter

				for(int counter = 0; counter < maze[x][y].length(); counter++)
				{
			
					if(counter == randC && x != (n - 1))
					{
					
						temp += " "; //add empty space instead 
				
					}
					else
					if(counter == randC && x == (n - 1))
					{
					
						temp += "_"; //add underline instead 
					
					}
					else
					{
				
						temp += maze[x][y].charAt(counter); //characters from maze 
					
					}

				}

				maze[x][y] = temp; //replace maze with temp
			
			}

		}
		
		graph.findPath(0);
		printMaze(maze, n, m);
		System.out.println("");
		System.out.print("press enter to get solution:");
		Scanner scan = new Scanner (System.in);
		scan.nextLine();
		System.out.println("");
		printPath(graph.getVertex((n * m) - 1));
		System.out.println("");
		printSolvedMaze(maze,n,m,graph.getVertex((n * m) - 1));

	
	}
	
	//print the solved maze given the maze the dimensions and the ending node

	public static void printSolvedMaze(String maze [][], int n , int m, node end)
	{
	
		node cur = end;
		
		//coordinates
		
		int y;
		int x; 
		String temp;

		//while the current node has a previous visitor

		while(cur.getPrevVis() != null)
		{

			temp = "";

			//get the y and x coordinate

			y = cur.getVal() % m;
			x = (cur.getVal() - y) / m;

			if(cur.getPrevVis() == cur.getLeft() || cur.getPrevVis() == cur.getRight())
			{
			
				//put stars in the solution matrix

				for(int count = 0; count < maze[x][y].length() - 1; count++)
				{
				
					//if a vertical bar leave it alone	

					if(maze[x][y].charAt(count) == '|')
					{
					
						temp += maze[x][y].charAt(count); 
					
					}
					else
					if(maze[x][y].charAt(count) == '_')
					{
					
						temp += "◌̲";
					
					}
					else
					{
					
						temp += "*";
					
					}

				}
			
				maze[x][y] = temp + maze[x][y].charAt(maze[x][y].length() - 1);

			}
			else
			if(cur.getPrevVis() == cur.getUp() || cur.getPrevVis() == cur.getDown())
			{
			

				for(int c = 0; c < maze[x][y].length() - 1; c++)
				{

				

					//if a vertical bar leave it alone	

					if(maze[x][y].charAt(c) == '|' )
					{
					
						temp += maze[x][y].charAt(c); 
					
					}
					else
					if(maze[x][y].charAt(c) == '_')
					{
					
						temp += "◌̲";
					
					}
					else
					{
					
						temp += "*";
					
					}

				}
			
				maze[x][y] = temp + maze[x][y].charAt(maze[x][y].length() - 1);

			}

			cur = cur.getPrevVis(); //move to the next node

		}
		
		printMaze(maze,n,m);	

	}
	
	//given a node print the path recursively

	public static void printPath(node cur)
	{

		//if if the previous visitor is null that means this is the start so we stop

		if(cur.getPrevVis() != null)
		{
		
			//get the previous visitor

			printPath(cur.getPrevVis());
			
			if(cur.getPrevVis() == cur.getLeft())
			{
			
				System.out.print("E");

			}
			else
			if(cur.getPrevVis() == cur.getRight())
			{
			
				System.out.print("W");
			
			}
			else
			if(cur.getPrevVis() == cur.getUp())
			{
			
				System.out.print("S");

			}
			else
			if(cur.getPrevVis() == cur.getDown())
			{
			
				System.out.print("N");

			}	

		}

	}

	//print out maze

	public static void printMaze(String maze[][], int n, int m)
	{
	
		//print the top

		for(int counter = 0; counter < (m * 2); counter++)
		{
		
			//leave the entrance top open

			if(counter == 0)
			{
			
				System.out.print("  ");
			
			}
			else
			{
			
				System.out.print("_");
	
			}	

		}

		//put a newline

		System.out.println("");

		//now print out everything else

		for(int counter = 0; counter < n; counter++)
                {

                        for(int count = 0; count < m; count++)
                        {

				System.out.print(maze[counter][count]);

                        }

			System.out.println("");
                
		}

	}

}



