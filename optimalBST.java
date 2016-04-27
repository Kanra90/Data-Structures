
import java.io.*;
import java.util.*;
class optimalBST{
	
	static int counter = 0;
	static int size;
	static String[]keyWords;
	static Node[]nodeArray = new Node[size+1];
	static int p[];
	static int r[][] = new int[10][10];
	static Integer cost[][] = new Integer[10][10];
	static Integer A[][];
	static Integer[][]sums = new Integer[10][10];
	
	public static void main(String[]args){
		
		int input = 0;
		Scanner kb = new Scanner(System.in);
		
		do{
			System.out.println("~~~~~Menu~~~~~");
			System.out.println("choose the instance by typing numerical \ncorrespondance to that Instance.");
			System.out.println("Please Choose between instances 1, 2, and 3\nType 4 to exit");
			System.out.print("instance:");
			input = kb.nextInt();
			
			if(input==1){
				String[]keyWords1 = {"Don", "Isabelle","Ralph","Wally"};
				keyWords = keyWords1;
				int [] freq = {3,3,1,1};
				first(freq,8,4);
			}else if(input==2){
				counter=0;
				String[]keyWords2 = {"A","B","C","D"};
				keyWords = keyWords2;
				int[] freq = {1,2,3,4};
				first(freq,10,4);
			}else if(input==3){
				counter=0;
				String[]keyWords3 = {"A","B","C","D","E","F","G"};
				keyWords = keyWords3;
				int[]freq = {1,1,1,1,1,1,1};
				first(freq,7,7);
			}else if(input>4 || input<1){
				System.out.println("Input Invalid. Please Try Again");
			}
			
		}while(input!=4);
		
	}
	private static void first(int[]freq,int lcd,int n) {
		//set largest common denom
		size = n;
		optimalBST tree = new optimalBST(freq,size);
		System.out.print("Average Key Comparisons:");
		double s = MemFunction(0,size-1);
		System.out.print(s/lcd);
		System.out.println();

		tree.tree(1,size);	
		System.out.println();
	}
	public optimalBST(int[]p, int size){
		this.p = p;
		this.size = size;
		//fill r array part way
		for(int i = 1;i<=size;i++){
			cost[i][i-1] = 0;
			cost[i][i] = p[i-1];
			r[i][i+1] = 0;
			r[i][i] = i;
		}
		cost[size+1][size] = 0;
		r[0][1] = 0;
	}
	public static Integer MemFunction(int i,int j){
		A = new Integer[size+1][size+1];

		if(A[i][j] == null){
			int n = recursiveCost(i,j);
			A[i][j] = n;
			
			return n;
			
		}else{
			return A[i][j];
		}
	}
	
	public static int recursiveCost(int i, int j){
		//base case, last item
		int root;
		
		if(j==i){
			return p[i];
		}
		if(j<i){	
			return 0;						
		}
		//calculate frequency sum of this tree		
		int s = sum(p, i,j);
		//set a big value current min
		int min_Value = 999999;
		
		for(int k = i;k<=j;k++){
			int c = recursiveCost(i,k-1) + recursiveCost(k+1,j);
						
			cost[i][j] = s;
			if(c<min_Value){
				min_Value = c;
				cost[i][j+1] = s;
				r[j+1][i+1] = k+1;
			}
		}
		return s + min_Value;	
	}
	
	static int sum(int freq[], int i, int j){
		int s = 0;
		
		if(sums[i][j] == null){	
			//no memory for this sum, calculate
		    for (int k = i; k <=j; k++){
			       s += freq[k];
			    }
		}else{
			s = sums[i][j];
		}
		return s;
	}
	public void printTable(){
		System.out.println("Cost Table: ");
		System.out.printf("% 8d %8d %8d %8d %8d",0,1,2,3,4);
		System.out.println();
		for(int j=0;j<6;j++){
			System.out.print(j);
			for(int i=0; i<6;i++){
				System.out.printf("%8d", r[i][j]);
			}
			System.out.println();
		}
	}
	
	public static Node tree(int i, int j){	
		
		//theoretically if even only 2 choices, 
		//print ( or print ( and name
		Node p;
		Integer k = r[j][i];
		
		//invalid tree, terminate
		if(k==0){
			counter++;
			//is null
			return null;
		}else{
			p = new Node();
			//check if its a left
			if(counter==0){
				System.out.print(keyWords[k-1]);
				counter++;
			}
			else if(counter%2==0){
				System.out.print("(" + keyWords[k-1] + ",");			
			}else{
				System.out.print(keyWords[k-1] + ")");
			}
			counter++;			
			p.key = keyWords[k-1];
			p.left = tree(i,k-1);
			p.right = tree(k+1,j);	
		}
		return p;
	}
	static class Node{
		String key;
		Node left;
		Node right;
	}
}
