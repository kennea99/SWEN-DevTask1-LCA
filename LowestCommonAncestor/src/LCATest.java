import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest {

	@Test
	public void ttest()
	{
		LCA t = new LCA();
		TreeNode node = null;
		t.findLCA(t.root, 1, 2);
		assertNull(node);
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		node = new TreeNode(2);
		node = t.findLCA(root, 2, 3);
		
		t = new LCA();
		t.root = new TreeNode(1);
		t.root.left = new TreeNode(2);
		t.root.right = new TreeNode(3);
		t.root.left.left = new TreeNode(4);
		t.root.left.right = new TreeNode(5);
		t.root.right.left = new TreeNode(6);
		t.root.right.right = new TreeNode(7);
		
		TreeNode lca = t.LCA(4,5);
		assertEquals(2, lca.data);
	}

}
