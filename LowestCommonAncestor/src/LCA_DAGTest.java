import static org.junit.Assert.*;

import org.junit.Test;

public class LCA_DAGTest {

	@Test(expected = IllegalArgumentException.class)
	public void LCA_DAGTest()
	{
		int vertex = -1;
		LCA_DAG dag = new LCA_DAG(vertex);
		assertEquals("exception thrown", null);
	}
	@Test
	public void edgesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);
		dag.edge = 3;
		assertEquals(3, dag.edges());
	}
	@Test
	public void verticesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);
		assertEquals(5, dag.vertices());
	}
	
	@Test
	public void validateTest()
	{
		LCA_DAG dag = new LCA_DAG(3);
		//when V is within the boundaries of the dag
		int V = 2;
		dag.validate(V);
		assertEquals(2, V);
	}
	@Test(expected = IllegalArgumentException.class)
	public void validateTestException1()
	{
		//when int v < 0
		LCA_DAG dag = new LCA_DAG(3);
		int V = -2;
		dag.validate(V);
		assertEquals("should throw exception", null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void validateTestException2()
	{
		//when v is greater than the no. of vertices in the dag
		LCA_DAG dag = new LCA_DAG(3);
		int V = 5;
		dag.validate(V);
		assertEquals("should throw exception", null);
	}
	
	@Test
	public void addEdgesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);

		//testing when an edge can be created between two vertices
		int x = 1; int y =3;
		dag.addEdges(x, y);
		assertEquals(1, dag.edge); //number of edges
		
		//adding multiple edges to the DAG
		dag = new LCA_DAG(6);
		dag.addEdges(1, 2);
		dag.addEdges(2, 3);
		dag.addEdges(1, 4);
		assertEquals(3, dag.edge);
		
	}
	
	@Test
	public void removeEdgeTest()
	{
		LCA_DAG dag = new LCA_DAG(6);
		
		//testing when there is one edge in the DAG and we remove it
		
		dag.addEdges(1, 3); //dag.edge = 1;
		//removing edge
		dag.removeEdges(1, 3);
		assertEquals(0, dag.edge);
		
		//trying to remove an edge that isnt an edge
		dag.addEdges(1, 3);
		dag.removeEdges(3, 4); //since there is no edge the code will run and not do anything leaving the existing edges
		assertEquals(1, dag.edge);
		
		//removing multiple edges
		dag = new LCA_DAG(6);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		dag.addEdges(2, 4);
		dag.addEdges(4, 5); //dag.edge = 5 at this point.
		
		dag.removeEdges(1, 3);
		dag.removeEdges(4, 5);
		assertEquals(3, dag.edge);
		
		//removing multiple and trying to remove an edge that isn't there
		dag = new LCA_DAG(6);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		dag.addEdges(2, 4);
		dag.addEdges(4, 5); //dag.edge = 5 at this point.
				
		dag.removeEdges(1, 3);
		dag.removeEdges(1, 5); //edge doesn't exist.
		dag.removeEdges(4, 5);
		assertEquals(3, dag.edge);
	}
	
	@Test
	public void inDegTest()
	{
		LCA_DAG dag = new LCA_DAG(8);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		dag.addEdges(1, 2);
		//when the indegree is a valid vertex and has edges
		assertEquals(2, dag.inDeg(2));
		
		//indegree of a vertex with no edges connected to it
		assertEquals(0, dag.inDeg(7));
		

			
	}
	
	@Test
	public void outDeg()
	{
		LCA_DAG dag = new LCA_DAG(8);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		//out degree of a node with no edges
		assertEquals(0,  dag.outDeg(5));
		
		//out degree of a node with edges
		assertEquals(2, dag.outDeg(0));
		
	}
	
	@Test
	public void cycleOccur()
	{
		LCA_DAG dag = new LCA_DAG(10);
		//when a there is a a cycle
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 2);
		dag.addEdges(2, 4);
		dag.addEdges(4, 3);
		assertTrue(dag.cycleOccur());
		
		//when there isnt any cycle.
		dag = new LCA_DAG(8);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		dag.addEdges(2, 3);
		dag.addEdges(3, 4);
		assertFalse(dag.cycleOccur());
		
	}
	
	@Test
	public void LCATest()
	{
		LCA_DAG dag = new LCA_DAG(10);
		//when there is a cycle, should return -1 as cant find LCA the cycle that exists is:
		dag.addEdges(0, 1);//  1<---------3
		dag.addEdges(0, 2);//  |		  ^	
		dag.addEdges(1, 2);//  v		  |
		dag.addEdges(2, 4);//  2 -------> 4
		dag.addEdges(4, 3);//
		assertEquals(-1, dag.LCA(1, 2));

		
		//when it is a directed acyclic graph
		dag = new LCA_DAG(10);
		dag.addEdges(0, 1);
		dag.addEdges(0, 2);
		dag.addEdges(1, 3);
		dag.addEdges(2, 4);
		dag.addEdges(4, 5);
		dag.addEdges(3, 5);
		dag.addEdges(5, 6);
		assertEquals(5, dag.LCA(1, 2));
		
		//cant find LCA in a DAG(7 does not have any edges connected so there is no LCA for it)
		assertEquals(-1, dag.LCA(2, 7));
		assertEquals(-1, dag.LCA(7, 2));
		

		
	}
}
