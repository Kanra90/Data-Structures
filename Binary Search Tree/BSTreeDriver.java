import java.util.*;
class BSTreeDriver {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		BSTree newTree = new BSTree();
		String H = "I  Insert a Value\n"
				+ "D  Delete a Value\n"
				+ "P  Find Predecessor\n"
				+ "S  Find Successor\n"
				+ "E  Exit the Program\n"
				+ "H  Display Message\n";
		
		Scanner kb1 = new Scanner(System.in);
		
		String n = null;
		System.out.println("Please enter the initial sequence of values:");
		n=kb1.nextLine();
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(n).useDelimiter(" ");
		while(kb.hasNext()){
			newTree.add(kb.nextInt());
		}
		System.out.print("Pre-Order: ");
		newTree.preOrder(newTree.root);
		System.out.println();
		printOrder(newTree);
		System.out.print("\nPost-Order: ");
		newTree.postOrder(newTree.root);
		//System.out.print(newTree.root.getLeft().getRight().getData());

		do{
			n = null;
			int val = 0;
			System.out.print("\nCommand?");
			n = kb1.nextLine();	
			
			if(n.equals("H")){
				System.out.print(H);
			}else if(n.equals("E")){
				System.out.print("Thank you for using the program!");	
			}else{
				String[] arr = n.split(" ");			
				n = arr[0];
				val = Integer.parseInt(arr[1]);
				
				if(n.equals("I")){
					newTree.add(val);	
				}else if(n.equals("D")){
					newTree.remove(val);
				}else if(n.equals("P")){
					newTree.findPredecessor(val);
					System.out.println();
				}else if(n.equals("S")){
					newTree.findSuccessor(val);
					System.out.println();
				}
			printOrder(newTree);
			}
		}while(!n.equals("E"));				
	}
	
	static void printOrder(BSTree newTree){
		System.out.print("In-Order: ");
		newTree.inOrder(newTree.root);
	}

}
