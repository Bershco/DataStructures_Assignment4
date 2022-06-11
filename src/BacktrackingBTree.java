import java.util.LinkedList;
import java.util.List;

public class  BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}

	/**
	 * This method backtracks the insertion method in B Trees
	 */
	public void Backtrack() {
		if(root!=null) {
			if (root.numOfChildren == 0) {
				if (root.getNumberOfKeys() == 1) {
					root.numOfKeys = 0;
					root = null;
				} else {
					root.removeKey((T) backtrackingDLL.getLast()[0]);
					backtrackingDLL.removeLast();
				}
			} else { //inserted inside a leaf
				Object[] arr = backtrackingDLL.removeLast();
				if (!(Boolean) arr[2]) {
					root.removeKey((T) backtrackingDLL.getLast()[0]);
					backtrackingDLL.removeLast();
					return;
				}
				while ((Boolean) arr[2]) {
					Object[] splitted = backtrackingDLL.removeLast();
					T value = (T) arr[0];
					Node<T> left = (Node) splitted[3];
					Node<T> right = (Node) splitted[4];
					Node<T> parent = (Node) splitted[5];
					if(parent == null){
						//merge left right and value
						left.parent=null;
						root =left;
					}
					else{
						parent.removeKey((T)splitted[0]);
						//merge left right and value;
						parent.removeChild(this.getNode(value));
						parent.addChild(left);
					}
				}
				size--;
			}
		}

    }

	/**
	 * This method is an example of a B tree that after backtracking insertion is different from deleting the inserted Node
	 * @return a list of Integers that is representing the tree
	 */
	public static List<Integer> BTreeBacktrackingCounterExample(){
		List<Integer> difference = new LinkedList<>();
		int curr = 1;
		while (curr < 7) {
			difference.add(curr++);
		}
		return difference;
	}
}
