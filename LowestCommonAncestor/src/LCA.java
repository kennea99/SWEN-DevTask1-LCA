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
	//Using a binary tree as a data structure
	TreeNode root;
	static boolean keyPresent1 = false, keyPresent2 = false;
	
	//this function returns the LCA of two given values
	//the booleans are set to true if n1 or n2 is found 
	TreeNode findLCA(TreeNode node, int n1, int n2 )
	{
		if(node == null)
			return null;
		
		//store the first value in a temporary node in case there is a match and we need to search for the other key 
		TreeNode tempNode = null;
		
		//if n1 or n2 match root key then turn keyPresent1 or keyPresent2 to true respectively
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
		

		//looking for keys in subtrees to the left and right
		TreeNode lcaLeft = findLCA(node.left, n1, n2);
		TreeNode lcaRight = findLCA(node.right, n1, n2);
		
		if(tempNode != null)
			return tempNode;
		
		//if both calls are not null this node is the LCA
		if(lcaLeft != null && lcaRight != null)
			return node;
		
		//this if statement will check if the left or right subtrees are LCA
		if(lcaLeft != null)
		{
			return lcaLeft;
		}
		else
		{
			return lcaRight;
		}
	}
	//this will find LCA of two values with a node as a root
	TreeNode LCA(int n1, int n2)
	{
		//initalising as the key has not been found
		keyPresent1 = false;
		keyPresent2 = false;
		
		TreeNode lca = findLCA(root, n1, n2);
		
		//If both true return the LCA, if not return null;
		if(keyPresent1 && keyPresent2)
			return lca;
		else return null;
	}
	
	
	public static void main(String args[])
	{
		
	}
}
