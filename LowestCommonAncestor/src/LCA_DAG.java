
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
			return; // cant use a vertex that is out of bounds
		else {
		this.vertex = vertex;
		this.edge = edge;
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
	
	//adds an edge to graph x to y.
	public void addEdges(int x, int y)
	{
		validate(x);
		validate(y);
		if(x == -1 || y == -1)
			return;
		adjList[x][y] = 0;
		inDeg[y]++;
		outDeg[x]++;
		edge++;
	}
	
	public void removeEdges(int x, int y)
	{
		validate(x);
		validate(y);
		if(x == -1 || y == -1)
			return;
		adjList[x][y] = 0;
		inDeg[y]--;
		outDeg[x]--;
		edge--;
	}
	
	public int inDeg(int x)
	{
		validate(x);
		return inDeg[x];
	
	}
	
	public int outDeg(int x)
	{
		validate(x);
		return outDeg[x];
	
	}
	
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
					if(visit[k] == j && adjList[i][j] == 1)
					{
						cycle = true;
						return cycle;
					}
				}
			}
			count++;
		}
		return cycle;
	}
	
	public int LCA(int x, int y)
	{
		validate(x);
		validate(y);
		if(x == -1 || y == -1)
			return -1;
		boolean cycle;
		cycle = cycleOccur();
		if(edge < 0 && cycle == false)
			return LCAHelp(x, y);
		else
		{
			return -1;  //not an acyclic graph so cant find LCA
		}
	}
	
	private int LCAHelp(int x, int y)
	{
		int[] xPath = new int[edge];
		int[] yPath = new int[edge];
		
		boolean[] xVisited = new boolean[vertex];
		boolean[] yVisited = new boolean[vertex];
		int countX = 0;
		int countY = 0;
		xPath[countX] = x;
		yPath[countY] = y;
		for(int i = 0; i < vertex; i++)
		{
			xVisited[i] = false;
			yVisited[i] = false;
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
					xPath[countX] = k;
					xVisited[k] = true;
				}
				if(adjList[j][k] == 1 && yVisited[j])
				{
					countY++;
					yPath[countY] = k;
					yVisited[k] = true;
				}
				if(yPath[countY] == xPath[countX])
				{
					return yPath[countY];
				}
			}
		}
		return -1; // no ancestor
				
	}
	
	public static void main(String[] args)
	{
		
	}
	
	
}

