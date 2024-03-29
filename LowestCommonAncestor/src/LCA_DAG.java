
public class LCA_DAG {
	 int vertex;
	 int edge;
	 int[] outDeg;
	 int[] inDeg;
	 int[] visit;
	 int[][] adjList;
	
	public LCA_DAG(int vertex)
	{
		if(vertex < 0)
			throw new IllegalArgumentException("number of vertices cannot be less than 0"); // cant use a vertex that is out of bounds
		else {
		this.vertex = vertex;
		this.edge = 0;
		inDeg = new int[vertex];
		outDeg = new int[vertex];
		visit  = new int[vertex];
		adjList = new int[vertex][vertex];
		//initialising the graph in an empty
		for(int i = 0; i < vertex; i++)
			for(int j =0; j < vertex; j++)
				adjList[i][j] = 0;
		}
	}
	
	//return number of edges
	public int edges()
	{
		return edge;
	}
	
	//return number of vertices
	public int vertices() {
		return vertex;
	}
	
	public void validate(int v)	// see if vertex that is attempting to be inserted is not within scope of graph.
	{
		if(v < 0 || v>= vertex)
		{
			throw new IllegalArgumentException("not a valid vertex");
			
		}
	}

	//adds a directed edge to graph x to y.
	public void addEdges(int x, int y)
	{
		validate(x);
		validate(y);
		adjList[x][y] = 1;
		inDeg[y]++;
		outDeg[x]++;
		edge++;
	}
	//removes edge x to y from graph
	public void removeEdges(int x, int y)
	{
		validate(x);
		validate(y);
		if(adjList[x][y] == 1) //if an edge already exists
		{
		adjList[x][y] = 0;
		inDeg[y]--;
		outDeg[x]--;
		edge--;
		}
		else
			return;
	}
	//returns the indegree of vertex x(number of edges incoming to a vertex)
	public int inDeg(int x)
	{
		validate(x);
		return inDeg[x];
	
	}
	//returns the outdegree of a vertex(number of outgoing edges of a vertex).
	public int outDeg(int x)
	{
		validate(x);
		return outDeg[x];
	
	}
	//returns whether a cycle occurs in a directed graph, graph must be acyclic to find LCA
	public boolean cycleOccur()
	{
		boolean cycle = false;
		int count = 0;
		for(int i = 0; i < vertex; i++)
		{
			visit[count] = i;
			for(int j = 0; j < vertex; j++)
			{
				for(int k = 0; k < vertex; k++)
				{
					if(visit[k] == j && adjList[i][j] == 1) //if visit[k] is equal to the vertex j and i and j have an edge between them 
					{
						cycle = true; // there is a cycle
						return cycle;
					}
				}
			}
			count++; 
		}
		return cycle;
	}
	//finds the LCA of vertices x and y
	public int LCA(int x, int y)
	{
		validate(x);
		validate(y);
		boolean cycle;
		cycle = cycleOccur();
		if(edge > 0 && cycle == false) //if there are no edges or there is a cycle, it cannot be a directed acyclic graph
			return LCAHelp(x, y);
		else
		{
			return -1;  //not an acyclic graph so cant find LCA
		}
	}
	
	private int LCAHelp(int x, int y)
	{
		int[] xPath = new int[edge]; //create a first path
		int[] yPath = new int[edge]; //create a second path
		
		boolean[] xVisited = new boolean[vertex]; //an array of booleans which stores which vertices have been visited
		boolean[] yVisited = new boolean[vertex]; //an array of booleans which stores which vertices have been visited
		int countX = 0;
		int countY = 0;
		xPath[countX] = x;
		yPath[countY] = y;
		for(int i = 0; i < vertex; i++)
		{
			xVisited[i] = false;		//setting every vertex to not visited at the start of the search
			yVisited[i] = false;		//setting every vertex to not visited at the start of the search
		}
		for(int j = 0; j < vertex; j++)
		{
			xVisited[x] = true;
			yVisited[y] = true;
			for(int k = 0; k<vertex; k++)
			{
				if(adjList[j][k] == 1 && xVisited[j])
				{
					countX++;
					xPath[countX] = k;		// this becomes the current LCA
					xVisited[k] = true;
				}
				if(adjList[j][k] == 1 && yVisited[j])
				{
					countY++;
					yPath[countY] = k;		//becomes the current LCA
					yVisited[k] = true;
				}
				if(yPath[countY] == xPath[countX]) //if both of these are equal then we have found the LCA of x and y 
				{
					return yPath[countY];
				}
			}
		}
		return -1; // no ancestor so return -1.
				
	}
	
	public static void main(String[] args)
	{

	}
	
	
}

