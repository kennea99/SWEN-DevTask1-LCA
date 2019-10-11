
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
			vertex = -1;
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
	
	void validate(int v)	// see if vertex that is attempting to be inserted is not within scope of graph.
	{
		if(v < 0 || v>= vertex)
			v = -1;
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
}

