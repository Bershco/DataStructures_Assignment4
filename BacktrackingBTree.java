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

	//You are to implement the function Backtrack.
	public void Backtrack() {

		if (!backtrackingDLL.isEmpty()) { //we can not do backtrack if we did not add any keys to the tree

			if (root.isLeaf() && root.getNumberOfKeys() == 1) { //there was one insert only
				root.numOfKeys = 0;
				root = null;
				backtrackingDLL.removeFirst();
			}
			else {
				Object[]arr = backtrackingDLL.getFirst();
				T val = (T)arr[0]; //if there were more than one split during insert

				Node<T> not = getNode((T)arr[0]); //delete the val from the tree
				not.removeKey((T)arr[0]);

				if (arr[1] != null) {
					while (arr[1] != null& val.equals(arr[0])) {
						backtrackingDLL.removeFirst();
						Node<T> node = getNode((T) arr[1]); //the value that went up after the split
						int index = node.indexOf((T) arr[1]);
						//right and left as for the right side and the left side after the node was split
						Node<T> nodeLeft = node.getChild(index);
						Node<T> nodeRight = node.getChild(index+1);
						node.removeKey((T) arr[1]);//remove the value that went up

						int numKeysR = nodeRight.getNumberOfKeys();
						for (int i = 0; i < numKeysR; i++) { //move the keys of the right node to the  left node
							nodeLeft.addKey(nodeRight.getKey(i));
						}
						nodeLeft.addKey((T) arr[1]);
						if (!nodeRight.isLeaf()) { //move yhe children from right node to the left node
							int numKChildrenR = nodeRight.getNumberOfChildren();
							for (int i = 0; i < numKChildrenR; i++) {
								nodeLeft.addChild(nodeRight.getChild(i));
							}
						}
						if (nodeLeft.parent.numOfKeys==0) { //set the new root
							nodeLeft.parent = null;
							root = nodeLeft;
						}
						else { //delete the right node as it is not needed anymore
							node.removeChild(index + 1);
						}
						arr = backtrackingDLL.getFirst();
					}
				}
				else{
					backtrackingDLL.removeFirst(); //in case we did not enter the if and the loop
				}
			}
			size--;
		}
	}

		//Change the list returned to a list of integers answering the requirements
		public static List<Integer> BTreeBacktrackingCounterExample () {
			List<Integer> difference = new LinkedList<>();
			int curr = 1;
			while (curr < 7) {
				difference.add(curr++);
			}
			return difference;
		}
	}
