import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest {

	@Test
	public void findLCATest()
	{
		//testing if a tree is null
		LCA tree = new LCA();
		TreeNode node = null;
		tree.findLCA(tree.root, 1, 2);
		assertNull(node);
		
		//when LCA of a small tree
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		node = tree.findLCA(root, 2, 3);
		assertEquals(1, node.data);
		
		//LCA of a larger tree
		tree = new LCA();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);
		tree.root.right.left = new TreeNode(6);
		tree.root.right.right = new TreeNode(7);
		node = tree.findLCA(tree.root, 6, 7);
		assertEquals(3, node.data);
		
		//testing LCA when nodes are not directly connected to the same node.
		node = tree.findLCA(tree.root, 4, 7);
		assertEquals(1, node.data);
		
		//trying to find LCA of same node
		node = tree.findLCA(tree.root, 2, 2);
		assertEquals(2, node.data);
		
		
	}
	
	@Test 
	public void LCATest()
	{
		//testing various LCA of Tree
		LCA tree = new LCA();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);
		tree.root.right.left = new TreeNode(6);
		tree.root.right.right = new TreeNode(7);
		
		TreeNode lca = tree.LCA(4,5);
		assertEquals(2, lca.data);
		
		lca = tree.LCA(4, 7);
		assertEquals(1, lca.data);
		
		lca = tree.LCA(4, 3);
		assertEquals(1, lca.data);
		
		tree = new LCA();
		assertEquals(null, tree.LCA(10, 15));
		
		//keyPresent1 && keyPresent2 == false
		tree = new LCA();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		assertEquals(null, tree.LCA(4, 5));
		
		//just keyPresent1 == false
		tree = new LCA();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		assertEquals(null, tree.LCA(4, 3));
		
		//just keyPresent 2 == false
		tree = new LCA();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		assertEquals(null, tree.LCA(2, 5));
		
	}
	

}
