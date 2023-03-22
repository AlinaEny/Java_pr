public class TreeBuilder<T> {
	
	
	//initialization of private variable
	private LinkedBinaryTree<T> tree;

	
	//building a tree
	public TreeBuilder(T[] data) {
	
		
	   //initialize dataQueue
       LinkedQueue<T> dataQueue = new LinkedQueue<T>();
       
       
       //all elements (from T[ ] parameter) to be added in the nodes in order
       for(int i=0;i<data.length;i++)
       {
           dataQueue.enqueue(data[i]);
       }
      
       
       LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>();
       
       BinaryTreeNode<T> root = new BinaryTreeNode<T>(dataQueue.dequeue());
       
       //enqueue the root node on parentQueue
       parentQueue.enqueue(root);
      
       
       while(!dataQueue.isEmpty())
	       {
	           T a = dataQueue.dequeue(); // dequeue from dataQueue
	           T b = dataQueue.dequeue(); // dequeue from dataQueue
	          
	           //parent = dequeue from parentQueue
	           BinaryTreeNode<T> parent = parentQueue.dequeue();
	          
	           
	           // add node with a as left child of parent and enqueue on parentQueue
	           if(a != null) 
	           {
	               
	               BinaryTreeNode<T> node = new BinaryTreeNode<T>(a);
	               parent.setLeft(node); 
	               parentQueue.enqueue(node); 
	           }
	          
	           
	           //add node with b as right child of parent and enqueue on parentQueue
	           if(b != null) 
	           {
	               
	               BinaryTreeNode<T> node = new BinaryTreeNode<T>(b);
	               parent.setRight(node); 
	               parentQueue.enqueue(node);
	           }
	       }
       
       //initialize the LinkedBinaryTree instance variable with the root node from above
       tree = new LinkedBinaryTree<T>(root);
       }
	
	//Return the tree
	public LinkedBinaryTree<T> getTree(){
		return tree;
	}
}

   

