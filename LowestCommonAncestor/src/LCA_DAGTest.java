import static org.junit.Assert.*;

import org.junit.Test;

public class LCA_DAGTest {

	@Test
	public void LCA_DAGTest()
	{
		int vertex = -1;
		LCA_DAG dag = new LCA_DAG(vertex);
		assertEquals(-1, vertex);
	}
	
	public void edgesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);
		dag.edge = 3;
		assertEquals(3, dag.edges());
	}
	
	public void verticesTest()
	{
		LCA_DAG dag = new LCA_DAG(5);
		assertEquals(5, dag.vertices());
	}
	
	public void validateTest()
	{
		//when int v <0
		LCA_DAG dag = new LCA_DAG(3);
		int V = -2;
		dag.validate(V);
		assertEquals(-1, V);
		
		//when v is greater than the no. of vertices in the dag
		V = 5;
		dag.validate(V);
		assertEquals(-1 , V);
		
		//when V is within the boundaries of the dag
		V = 2;
		dag.validate(V);
		assertEquals(2, V);
	}
}
