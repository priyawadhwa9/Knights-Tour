package codes;

public class Grid {
	private Node root;
	private int dimension;
	public int solutions = 0;
	public Grid(int size)
	{
		// set grid dimension
		this.dimension = size;
		// Creating the first node
		root = new Node(0);
		// track horizontal movement
		Node marker = root;
	
		// Creating the first row
		for (int x = 0; x < dimension - 1; x++) 
		{
			Node temp = new Node(0);
			marker.setRight(temp);
			temp.setLeft(marker);
			marker = temp;
		}
		// track vertical movement
		Node rowMarker = root;
	
		for (int y = 0; y < dimension - 1; y++) 
		{
			Node temp = new Node(0);
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);
		
			for (int x = 0; x < dimension - 1; x++) 
			{
				marker = temp;
				temp = new Node(0);
				marker.setRight(temp);
				temp.setLeft(marker);
				temp.getLeft().getUp().getRight().setDown(temp);
				temp.setUp(temp.getLeft().getUp().getRight());
			}
			
				rowMarker = rowMarker.getDown();
		
		}
		rowMarker = root;
		// set the 8 paths for each node if they exist
		while(rowMarker != null)
		{
			Node temp = rowMarker;
			while(temp != null)
			{
				//path 1: up up left
				if(temp.getUp()!= null && temp.getUp().getUp()!=null && temp.getUp().getUp().getLeft()!=null)
					temp.setPath1(temp.getUp().getUp().getLeft());
				// path two: up up right
				if(temp.getUp()!= null && temp.getUp().getUp()!=null && temp.getUp().getUp().getRight()!=null)
					temp.setPath2(temp.getUp().getUp().getRight());
				// path  three: up right right
				if(temp.getUp()!= null && temp.getUp().getRight()!=null && temp.getUp().getRight().getRight()!=null)
					temp.setPath3(temp.getUp().getRight().getRight());
				// path four: down right right
				if(temp.getDown()!= null && temp.getDown().getRight()!=null && temp.getDown().getRight().getRight()!=null)
					temp.setPath4(temp.getDown().getRight().getRight());
				// path five: down down right
				if(temp.getDown()!= null && temp.getDown().getDown()!=null && temp.getDown().getDown().getRight()!=null)
					temp.setPath5(temp.getDown().getDown().getRight());
				// path six: down down left
				if(temp.getDown()!= null && temp.getDown().getDown()!=null && temp.getDown().getDown().getLeft()!=null)
					temp.setPath6(temp.getDown().getDown().getLeft());
				// path seven: down left left
				if(temp.getDown()!= null && temp.getDown().getLeft()!=null && temp.getDown().getLeft().getLeft()!=null)
					temp.setPath7(temp.getDown().getLeft().getLeft());
				// path eight: up left left
				if(temp.getUp()!= null && temp.getUp().getLeft()!=null && temp.getUp().getLeft().getLeft()!=null)
					temp.setPath8(temp.getUp().getLeft().getLeft());
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
		}
		

	}

	public void display() 
	{
		Node rowMarker = root;
	
		while (rowMarker != null) 
		{
			Node temp = rowMarker;
		
			while (temp != null)
			{
				System.out.print(temp.getData() + "\t");
				temp = temp.getRight();
			}
			System.out.println();
			rowMarker = rowMarker.getDown();
		}
	}
	
	
	public void tour(Node temp, int counter)
	{
		temp.setData(counter);
		counter++;
		//check if the board is a complete tour
		if(counter == 65)
		{
			display();
			solutions++;
			System.out.println("solutions: " + solutions);
		}
		// path one: up up left
		if(temp.getPath1()!= null && temp.getPath1().getData() == 0)
			tour(temp.getPath1(),counter);
		// path two: up up right
		if(temp.getPath2()!= null && temp.getPath2().getData() == 0)
			tour(temp.getPath2(),counter);
		// path  three: up right right
		if(temp.getPath3()!= null && temp.getPath3().getData() == 0)
			tour(temp.getPath3(),counter);
		// path four: down right right
		if(temp.getPath4()!= null && temp.getPath4().getData() == 0)
			tour(temp.getPath4(),counter);
		// path five: down down right
		if(temp.getPath5()!= null && temp.getPath5().getData() == 0)
			tour(temp.getPath5(),counter);
		// path six: down down left
		if(temp.getPath6()!= null && temp.getPath6().getData() == 0)
			tour(temp.getPath6(),counter);
		// path seven: down left left
		if(temp.getPath7()!= null && temp.getPath7().getData() == 0)
			tour(temp.getPath7(),counter);
		// path eight: up left left
		if(temp.getPath8()!= null && temp.getPath8().getData() == 0)
			tour(temp.getPath8(),counter);
		//once all paths are impossible, set node back to zero
		temp.setData(0);
	}

	public Node getRoot() {
		return root;
	}
}
	

