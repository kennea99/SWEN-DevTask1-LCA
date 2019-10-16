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
	
	@Test(expected = IllegalArgumentException.class)
	public void validateTest()
	{
		//when int v <0
		LCA_DAG dag = new LCA_DAG(3);
		int V = -2;
		dag.validate(V);
		assertEquals("should throw exception", null);
		
		//when v is greater than the no. of vertices in the dag
		V = 5;
		dag.validate(V);
		assertEquals("should throw exception" , null);
		
		//when V is within the boundaries of the dag
		V = 2;
		dag.validate(V);
		assertEquals(2, V);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addEdgesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);
		//testing when vertex1 is out of bounds
		int x = 6; int y = 3;
		dag.addEdges(x, y);
		assertEquals("should throw exception", null); //cannot add this edge
		
		//testing when vertex2 is out of bounds
		x = 2; y = 6;
		dag.addEdges(x,  y);
		assertEquals(-1, y);//cannot add this edge.
		
		//testing when an edge can be created between two vertices
		x = 1; y =3;
		dag.addEdges(x, y);
		assertEquals(1, dag.edge); //number of edges
		
		//adding multiple edges to the DAG
		dag = new LCA_DAG(6);
		dag.addEdges(1, 2);
		dag.addEdges(2, 3);
		dag.addEdges(1, 4);
		assertEquals(3, dag.edge);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeEdgeTest()
	{
		LCA_DAG dag = new LCA_DAG(6);
		//testing when vertex1 is out of bounds
		int x = 6; int y = 3;
		dag.removeEdges(x, y);
		assertEquals("should throw exception", null); //cannot add this edge
		
		//testing when vertex2 is out of bounds
		x = 2; y = 7;
		dag.addEdges(x, y);
		assertEquals("exception thrown", null);//cannot add this edge.
		
		//testing when there is one edge in the DAG and we remove it
		x = 1; y= 3;
		dag.addEdges(x, y); //dag.edge = 1;
		//removing edge
		dag.removeEdges(x, y);
		assertEquals(0, dag.edge);
		
		//trying to remove an edge that isnt an edge
		dag.addEdges(x, y);
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
	
	@Test(expected = IllegalArgumentException.class)
	public void inDegTest()
	{
		LCA_DAG dag = new LCA_DAG(8);
		//out of scope
		int x = -2;
		dag.inDeg(x);
		assertEquals("should throw exception", null);
		
		//x > vertex
		x = 10;
		dag.inDeg(x);
		assertEquals("should throw exception", null);
		
		//in scope
		x = 4;
		dag.inDeg(x);
		assertEquals(4, dag.inDeg[x]);
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void outDeg()
	{
		LCA_DAG dag = new LCA_DAG(8);
		//out of scope
		int x = -3;
		dag.outDeg(x);
		assertEquals("should throw exception", null);
		
		//x > vertex
		x = 12;
		dag.outDeg(x);
		assertEquals("should throw exception", null);
		
		//in scope
		x = 6;
		dag.outDeg(x);
		assertEquals(6, dag.outDeg[x]);
		
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
	
	@Test(expected = IllegalArgumentException.class)
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
		//when x is not in bounds when there is a cycle(validate() should trigger an exception).
		assertEquals("throw exception", null, dag.LCA(11, 2));
		
		//when y is not in bounds when there is a cycle(validate() should trigger an exception).
		assertEquals("throw exception", null, dag.LCA(2,11));
		
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
		
		//when x is not in bounds using DAG.
		assertEquals("throw exception", null, dag.LCA(11, 2));
		
		//when y is not in bounds using DAG
		assertEquals("throw exception", null, dag.LCA(11, 9));
		
		
	}
}