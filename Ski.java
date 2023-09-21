/**
 * This class represents the path of the skier
 * @author Enikeeva Alina
 */
public class Ski {
    //creating the private instance variable for the start of the path
    private BinaryTreeNode<SkiSegment> root;

    
    /**
	 * Constructor creates the tree of the path
	 * @param data
	 */
    public Ski(String[] data) {
    	
        SkiSegment[] segments;
        segments = new SkiSegment[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                segments[i] = null;
            }
            else if (data[i].contains("jump")) segments[i] = new JumpSegment(String.valueOf(i), data[i]);
             	else if (data[i].contains("slalom")) segments[i] = new SlalomSegment(String.valueOf(i), data[i]);
             		else segments[i] = new SkiSegment(String.valueOf(i), data[i]);
            
        }
        TreeBuilder<SkiSegment> tree;
		tree = new TreeBuilder<>();
		//initializing the root of the path		
        root = tree.buildTree(segments).getRoot();
    }

    /**
	 * Accessory method to get the root
	 * @return root of the tree
	 */
    public BinaryTreeNode<SkiSegment> getRoot() {
        return root;
    }

    /**
	 * This method determine the next node to access from the node passed as parameter
	 * @param node the skier is currently at; the path he accomplished 
	 */
    public void skiNextSegment(BinaryTreeNode<SkiSegment> node, ArrayUnorderedList<SkiSegment> sequence) {
        sequence.addToRear(node.getData());
        BinaryTreeNode<SkiSegment> left = node.getLeft();
		BinaryTreeNode<SkiSegment> right = node.getRight();
        if (left == null && right == null) return;
       // recursion continuing the path to the next node
        else skiNextSegment(chooseNextSegmant(node), sequence);
    }

    

    /**
	 * Helper method determine the next node to access from the node passed as parameter
	 * @param node the skier is currently at
	 */
    private BinaryTreeNode<SkiSegment> chooseNextSegmant(BinaryTreeNode<SkiSegment> node) {
    	//base case when the path is done
    	if (node.getLeft() == null) {
            return node.getRight();
        }
        if (node.getRight() == null) {
            return node.getLeft();
        }
        //initializing right and left child of the tree
        SkiSegment rightTurn = node.getRight().getData();
        SkiSegment leftTurn = node.getLeft().getData();
        
        //prioritizing the next turn:
         
        if (rightTurn instanceof JumpSegment && leftTurn instanceof JumpSegment) {
            JumpSegment rightJump = (JumpSegment) rightTurn;
            JumpSegment leftJump = (JumpSegment) leftTurn;
            if (rightJump.getHeight() >= leftJump.getHeight()) return node.getRight();
            else  return node.getLeft();
            
        }
     
        if (rightTurn instanceof JumpSegment) {
            return node.getRight();
        }
        if (leftTurn instanceof JumpSegment) {
            return node.getLeft();
        }
        if (rightTurn instanceof SlalomSegment && leftTurn instanceof SlalomSegment) {
            SlalomSegment leftSlalom = (SlalomSegment) leftTurn;
            if (leftSlalom.getDirection().equals("L"))  return node.getLeft();
            else return node.getRight();
            
        }
        
        if (rightTurn instanceof SlalomSegment || leftTurn instanceof SlalomSegment) {
            if (leftTurn instanceof SlalomSegment) {
                SlalomSegment leftSlalom = (SlalomSegment) leftTurn;
                if (!leftSlalom.getDirection().equals("L")) return node.getRight();
                else return node.getLeft();
            }
            else {
            	
                SlalomSegment rightSlalom = (SlalomSegment) rightTurn;
                if (rightSlalom.getDirection().equals("L")) return node.getRight();
                else return node.getLeft();    
            }
        }
        //if non of the segment is prioritized than the skier turn right
        return node.getRight();
    }
}