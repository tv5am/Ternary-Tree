/**
 * @author Samartha
 *
 */
public class TernarySearchTree {
	
	Node root;
	
	static class Node{
		int data;
		Node left;
		Node center;
		Node right;
		
		//parameterized constructor
		Node(int data){
			this.data = data;
			this.left = null;
			this.center = null;
			this.right = null;
		}
		 
		public void setLeft(Node left){
            this.left = left;
        }
		 
		public void setCenter(Node center){
		    this.center = center;
		}
        
		public void setRight(Node right){
            this.right = right;
        }
		
        public Node getLeft(){
            return this.left;
        } 
        
        public Node getCenter(){
        	return this.center;
        }
        
        public Node getRight(){
            return this.right;
        }
        
        public int getData(){
            return this.data;
        }
        
        public void setData(int value){
        	this.data = value;
        }
	}

	public TernarySearchTree() {
		// Constructor
		root = null;
	}
	
	public boolean isEmpty(){
		if(root == null)
			return true;
		return false;
			
	}
	
	public void insert(int value){
		root = insert(root, value);
	}
	
	private Node insert(Node root, int value){
		System.out.println("Inside Node Insert");
		if(root == null){
			root = new Node(value);
			root.setLeft(null);
			root.setRight(null);
			root.setCenter(null);
		}
		else {
			if(value < root.getData()){
				root.setLeft(insert(root.getLeft(), value));
			}
			if(value == root.getData()){
				root.setCenter(insert(root.getCenter(), value));
			}
			if(value > root.getData()) {
				root.setRight(insert(root.getRight(), value));
			}
		}
		return root;
	}
	
	//Delete a node
	public void delete(int value){
		root = delete(root, value);
		//root = delete(root, value);
	}
	
	private Node delete(Node root, int value){
		if(isPresent(root, value)){
			System.out.println("Entered if");
			if(root == null){
				return root;
			}
			if(value < root.getData()){
				root.setLeft(delete(root.getLeft(), value));
			}
			else if(value > root.getData()){
				root.setRight(delete(root.getRight(), value));
			}
			else  { //found element to delete
				System.out.println("Found element to delete");
				if(root.getLeft()==null && root.getCenter()==null && root.getRight()==null){
					root = null;
					return root;
				}
				if(root.center != null){
					root.setCenter(delete(root.getCenter(), value));
				}
				else {
					if(root.getLeft()!=null && root.getRight()!=null){
						//Find Largest element in left subtree
						//replace current Node's data with the Largest Left subtree data
						Node temp = findMax(root.getLeft());
						root.setData(temp.getData());
						root.setLeft(delete(root.getLeft(), temp.getData()));;
					}
					else if(root.getLeft()==null){
						root = root.getRight();
						return root;
					}
					else if(root.getRight() == null){
						root = root.getLeft();
						return root;
					}
					
				}
			}
			
		}
//		else {
//			System.out.println("Element not found to delete !!!");
//		}
		if(isPresent(root, value)){
			root=delete(root, value);
		}
		return root;
	}
	
	private Node findMax(Node root){
		if(root == null){
			return root;
		}
		else {
			while(root.getRight()!=null){
				root = root.getRight();
			}
		}
		System.out.println("Max elt = " + root.getData());
		return root;
	}
	
	private boolean isPresent(Node root, int value){
		if(root == null){
			return false;			
		}
		else if(value == root.getData()){
			System.out.println("Element found : " + root.getData());
			return true;
		}
				
		else if(value < root.getData()){
			return isPresent(root.getLeft(), value);
		}
		else if(value > root.getData()){
			return isPresent(root.getRight(), value);
		}
		return false;
	}
	
	//Inorder Tree Traversal
	public void traverseTree(Node root){
		if(root != null){
			traverseTree(root.getLeft());
			traverseTree(root.getCenter());
			System.out.print(root.getData()+ " ");
			traverseTree(root.getRight());
		}
		//System.out.println();
	
	}
	
	public void traverseTree(){
		System.out.println("Inside Traverse Tree");
		if(this.root == null){
			System.out.println("Empty Tree");
		}
		else traverseTree(root);
	}
	
	public static void main(String[] args) {
		TernarySearchTree TSTree = new TernarySearchTree();
		TSTree.insert(5);
		TSTree.insert(4);
		TSTree.insert(9);
		TSTree.insert(5);
		TSTree.insert(7);
		TSTree.insert(2);
		TSTree.insert(2);
		
		TSTree.traverseTree();
		
		TSTree.delete(2);
		TSTree.traverseTree();

	}

}
