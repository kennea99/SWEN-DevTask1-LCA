import java.util.ArrayList;
import java.util.List;

class TreeNode
{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int key)
	{
		data = key;
		left = right = null;
	}
}

public class LCA
{
	TreeNode root;
	static boolean keyPresent1 = false, keyPresent2 = false;
	
	TreeNode findLCA(TreeNode node, int n1, int n2 )
	{
		if(node == null)
			return null;
		
		TreeNode tempNode = null;
		
		if(node.data == n1)
		{
			keyPresent1 = true;
			tempNode = node;
		}
		if(node.data == n2)
		{
			keyPresent2 = true;
			tempNode = node;
		}
		

		
		TreeNode lcaLeft = findLCA(node.left, n1, n2);
		TreeNode lcaRight = findLCA(node.right, n1, n2);
		
		if(tempNode != null)
			return tempNode;
		
		if(lcaLeft != null && lcaRight != null)
			return node;
		
		if(lcaLeft != null)
		{
			return lcaLeft;
		}
		else
		{
			return lcaRight;
		}
	}
	TreeNode LCA(int n1, int n2)
	{
		keyPresent1 = false;
		keyPresent2 = false;
		
		TreeNode lca = findLCA(root, n1, n2);
		
		if(keyPresent1 && keyPresent2)
			return lca;
		else return null;
	}
	
	public static void main(String args[])
	{
		LCA tree = new LCA();
	}
}
