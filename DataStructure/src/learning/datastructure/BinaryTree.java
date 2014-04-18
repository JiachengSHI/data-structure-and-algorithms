package learning.datastructure;

import java.util.Scanner;

/*
 * Binary Tree
 * 		construct by sort value
 * 
 * each Tree Node can have left & right two child Nodes at most
 * 
 * most deep left Node (left leaf) is the smallest Node in Tree (subTree)
 * most deep right Node (right leaf) is the largest Node in Tree (subTree)
 * 
 * most deep rightNode (right leaf) in left subTree < sub-root <  most deep leftNode (left leaf) in right subTree
 * 
 * 
 * Insert TreeNode from root, always recursively add to leaf Node at last.
 * 
 * Remove TreeNode by conditions:
 * 1. no childNode, (leaf Node), just delete leaf Node, delete it from its parent's links.
 * 2. has childNode: uplevel "the most close Node" to "replace" it in its parent's links.
 *  				1. has 1 childNode, just uplevel its only childNode, replace itself by childNode in its parent's links.
 *  				2. has 2 childNodes:	most right leaf in left-subTree < sub-root < most left leaf in right-subTree
 * 										1. uplevel the most deep rightNode (right leaf) in left subTree.
 * 										2. uplevel the most deep leftNode (left leaf) in right subTree.
 * 
 * search is similar to insert, if cannot find equal one, return null.
 * 
 * */
public class BinaryTree {
	//create Node class
	static class TreeNode {
		int item;
		TreeNode parent;
		TreeNode left;
		TreeNode right;
		int level;
		
		//init
		public TreeNode(int item, TreeNode parent, int level){
			this.item = item;
			this.parent = parent;
			this.left = null;
			this.right = null;
			this.level = level;
		}
		
		//insert recursively
		public void insert(int item) {
			if (item <= this.item) {
				if (this.left == null){
					this.left = new TreeNode(item, this, this.level+1);
				} else {
					this.left.insert(item);
				}
			} else if (item > this.item) {
				if (this.right == null){
					this.right = new TreeNode(item, this, this.level+1);
				} else {
					this.right.insert(item);
				}
			}
		}
		
		public TreeNode search(int item) {
			if (item == this.item) {
				return this;
			} else if (item < this.item) {
				if (this.left == null) {
					return null;
				} else {
					/*
					 * In Java, return type method must add "return" keyword when recall itself.
					 * 
					 * In Python, u can only use "self", "self" = "this", without "return" keyword for recall itslef.
					 * */
					return this.left.search(item);
				}
			} else {
				if (this.right == null) {
					return null;
				} else {
					return this.right.search(item);
				}
			}
		}
		
		public static TreeNode findleaf(int direction, TreeNode n) {
			//"0" for left, "1" for right.
			if (direction == 0) {
				//most left leaf
				while (n.left != null) {
					n = n.left;
				}
			} else {
				//most right leaf
				while (n.right != null) {
					n = n.right;
				}
			}
			return n;
		}
		
		//remove by conditions
		public void remove(TreeNode n) {
			//remove non-root Node
			if (n.item <= n.parent.item) {
				// TreeMode is left child
				//no childNode
				if (n.left == null && n.right == null) {
					n.parent.left = null;
				} else if (n.left != null && n.right == null) {
					//1 left child
					n.parent.left = n.left;
					n.left.parent = n.parent;
				} else if (n.left == null && n.right != null) {
					//1 right child
					n.parent.left = n.right;
					n.right.parent = n.parent;
				} else {
					//2 childNodes
					TreeNode r = findleaf(1, n.left);
					r.parent.right = null;
					n.parent.left = r;
					r.parent = n.parent;
					
					r.left = n.left;
					r.right = n.right;
					r.left.parent = r;
					r.right.parent = r;
				}
			} else {
				//TreeNode is right child
				//no childNode
				if (n.left == null && n.right == null) {
					n.parent.right = null;
				} else if (n.left != null && n.right == null) {
					//1 left child
					n.parent.right = n.left;
					n.left.parent = n.parent;
				} else if (n.left == null && n.right != null) {
					//1 right child
					n.parent.right = n.right;
					n.right.parent = n.parent;
				} else {
					//2 childNodes
					TreeNode r = findleaf(1, n.left);
					r.parent.right = null;
					n.parent.right = r;
					r.parent = n.parent;
					
					r.left = n.left;
					r.right = n.right;
					r.left.parent = r;
					r.right.parent = r;
				}
			}
		}	
	}
	
	//init
	private TreeNode root;
	public BinaryTree(int item) {
		this.root = new TreeNode(item, null, 0);
	}
	
	//insert
	public void insert(int item) {
		this.root.insert(item);
	}
	
	//search
	public void search(int item) {
		if (this.root.search(item) == null) {
			System.out.println("Not exists in the Tree.");
		} else {
			TreeNode t = this.root.search(item);
			System.out.println("level " + t.level + "---" + t.item);
		}
	}
	
	//remove
	public void remove(int item) {
		if (this.root.search(item) == null) {
			System.out.println("Not exists in the Tree.");
		} else if (this.root.search(item).parent == null) {
			//remove root Node
			//no childNode
			if (root.left == null && root.right == null) {
				root = null;
			} else if (root.left != null && root.right == null) {
				//1 left child
				root.left.parent = null;
				root = root.left;
			} else if (root.left == null && root.right != null) {
				//1 right child
				root.right.parent = null;
				root = root.right;
			} else {
				//2 childNodes
				TreeNode r = TreeNode.findleaf(1, root.left);
				r.parent.right = null;
				r.parent = null;
				r.left = root.left;
				r.right = root.right;
				r.left.parent = r;
				r.right.parent = r;
				root = r;
			}
		} else {
			//remove non-root Node
			this.root.remove(this.root.search(item));
		}
	}
	
	//display recursively
	public static void display(TreeNode n) {
		if (n == null) {
			return;
		}
		System.out.println("level " + n.level + "---" + n.item);
		display(n.left);
		display(n.right);
	}
	
	public static void main(String[] args) {
		BinaryTree BT = new BinaryTree(5);
		
		for (int i=0; i<10; i++) {
			BT.insert(i);
		}
		display(BT.root);
		
		Scanner s = new Scanner(System.in);
		BT.search(Integer.parseInt(s.nextLine()));
		
		System.out.println();
		
		Scanner s2 = new Scanner(System.in);
		BT.remove(Integer.parseInt(s.nextLine()));
		display(BT.root);
	}
}
