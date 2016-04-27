
class BSTree {

	BTNode root;
	BTNode cursor = root;

	public int add(int n){
		BTNode node = new BTNode(n,null,null,null);
				
		if(root == null){
			root = node;
			cursor = root; //reroute cursor to root
		}else{
			if(n<cursor.getData()){
				if(cursor.getLeft()==null){
					cursor.setLeft(node);
					node.setParent(cursor);
				}else{
				cursor = cursor.getLeft();
				 return add(n);
				}
			}else if(n>cursor.getData()){
				if(cursor.getRight()==null){
					cursor.setRight(node);
					node.setParent(cursor);
				}else{
				cursor = cursor.getRight();
				return add(n);
				}
			}
		}
		cursor = root;
		return node.getData();
	}
	public boolean remove(int n){
		cursor = root;
		BTNode newcursor = root;
		//check that tree isnt empty
		if(root==null){
			System.out.print(n + " doesn't exist!");
			return false;
		}
		//root is to be removed
		else if(newcursor.getData()==n){
			root.data = getRightMost(root.getLeft()).getData();
			(getRightMost(root.getLeft()).getParent()).setRight(null);
			return true;
		}else{
			newcursor = removeHelper(n);
			if(newcursor.left==null && newcursor.right==null){	//node at cursor has no children
				//is a right child, set parent right child to null
				if(newcursor.parent.getRight().getData()==n){
					newcursor.parent.right = null;
				}else{
					newcursor.parent.left = null;
				}							
			}else if(newcursor.left==null){ //right child, no left child
				if(newcursor.parent.getRight().getData()==n){
					newcursor.parent.setRight(newcursor.getRight());
				}else{
					newcursor.parent.setLeft(newcursor.getRight());
				}
			}else if(newcursor.right==null){ //left child, no right
				if(newcursor.parent.getLeft().getData()==n){
					newcursor.parent.setLeft(newcursor.getLeft());
				}else{
					newcursor.parent.setRight(newcursor.getLeft());
				}
			}else{	//has left and right child
				//set right to rightmost of left branch
				if(newcursor.getParent().getRight().getData()==n){
					newcursor.getParent().setRight(getRightMost(newcursor.getLeft()));
				}else{
					newcursor.getParent().setLeft(getRightMost(newcursor.getLeft()));
				}
			}
			cursor = root;
			return true;
		}
		
	}
	public BTNode removeHelper(int n){
		//looking for node
			if(n<cursor.data){	//check if data is less than current cursor position
				cursor = cursor.getLeft();	//if less, left
				removeHelper(n);	//recursive call
			}else if(n>cursor.data){
				cursor = cursor.getRight();	//else right
				removeHelper(n);	//recursive call
		}
			return cursor;
		
	}
	public BTNode getLeftMost(BTNode n){
		
		//cursor = root;
		//check if no leftmost, is root
		if(n.getLeft()==null){
			return n;
		}else{
			do{
				n = n.getLeft();
			}while(n.getLeft()!=null);
		
			return n;
		}
	}
	public BTNode getRightMost(BTNode n){
		
		//cursor = root;
		//check if no leftmost, is root
		if(n.getRight()==null){
			return n;
		}else{
			do{
				n = n.getRight();
			}while(n.getRight()!=null);
		
			return n;
		}
	}

	public void preOrder(BTNode n) {
		System.out.print(n.getData() + " ");
		
		if(n.hasLeft()){
			preOrder(n.getLeft());
		}
		if(n.hasRight()){
			preOrder(n.getRight());
		}

	}
	public void inOrder(BTNode n) {
		if(n.hasLeft()){
			inOrder(n.getLeft());
		}
		System.out.print(n.getData() + " ");
		if(n.hasRight()){
			 inOrder(n.getRight());
		}
	}
	public void postOrder(BTNode n) {
		if(n.hasLeft()){
			postOrder(n.getLeft());
		}
		if(n.hasRight()){
			 postOrder(n.getRight());
		}
		System.out.print(n.getData() + " ");
	}
	public void findPredecessor(int n){
		cursor = root;
		//first element has No predecessor
		if(getLeftMost(root).getData()==n){
			System.out.print("First Element has no Predecessor");
		}else{
			findPredecessorHelper(n);
			cursor = root;
		}
			return;

	}
	public void findPredecessorHelper(int n){
				
		if(n<cursor.getData()){
			cursor = cursor.getLeft();
			findPredecessorHelper(n);
		}else if(n>cursor.getData()){
			cursor = cursor.getRight();
			findPredecessorHelper(n);
		}else{
				if(cursor.hasLeft()){
					System.out.print(getRightMost(cursor.getLeft()).getData());
				}else{
					System.out.print(cursor.getParent().getData());
				}
			}
	}
	public void findSuccessor(int n){
		cursor = root;
		//last element has no successor
		if(getRightMost(root).getData()==n){
			System.out.print("Last Element has no Successor");
		}else{
			findSuccessorHelper(n);
			cursor = root;
		}
	}
	public void findSuccessorHelper(int n){
		if(n<cursor.getData()){
			cursor = cursor.getLeft();
			findSuccessorHelper(n);
		}else if(n>cursor.getData()){
			cursor = cursor.getRight();
			findSuccessorHelper(n);
		}else{

			if(cursor.hasRight()){
				System.out.print(getLeftMost(cursor.getRight()).getData());
			}else{
				System.out.print(cursor.getParent().getData());
			}
		}
	}
	class BTNode{
		int data;
		
		BTNode left;
		BTNode right;
		BTNode parent;
		
		public BTNode(int idata, BTNode ileft, BTNode iright, BTNode iparent){
			
			parent = iparent;
			data = idata;
			left = ileft;
			right = iright;
		}
		public boolean hasRight() {
			if(getRight()!=null){
				return true;
			}else
				return false;
		}
		protected int getData(){
			return data;
		}
		protected BTNode getLeft(){
			return left;
		}
		protected void setLeft(BTNode n){
			left = n;
		}
		protected void setRight(BTNode n){
			right = n;
		}
		protected BTNode getRight(){
			return right;
		}
		protected BTNode getParent(){
			return parent;
		}
		protected void setParent(BTNode p){
			parent = p;
		}
		protected boolean hasLeft(){
			if(getLeft()!=null)
				return true;
			else
				return false;
		}
	}


}
