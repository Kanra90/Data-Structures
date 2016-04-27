import java.util.Random;
import java.util.Scanner;


public class Heap {
	
	public int[] heapArray;
	public int numSwaps = 0;
	public int count = 0;
	
	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		Random rd = new Random();
		
		int[] arr = new int[100];
		int randOrSeq;
		
		do{
			System.out.println("Please Select an Option:");
			System.out.print("1. Generate 100 Random Integers.\n"
					+ "2. Use Integers 1-100\n"
					+ "3. Exit Program\n");
			randOrSeq = kb.nextInt();
			
			//make an array of 100 values, 0 to 99
			for(int i=0;i<=99;i++){
				arr[i]=i;
			}
			
			//make array of random integers
			if(randOrSeq==1){
				
				System.out.println("In this Simulation, an array of 100 elements\n between 0 and 99 was used.");
				int averageBasic = 0,
					averageOptimal = 0;
				
				//20 arrrays, 20 times
				for(int j=0;j<20;j++){
				
					int randomIndex;
					int randomVal;
					
					for(int i=0;i<arr.length;i++){
						//select random index
						randomIndex = rd.nextInt(arr.length);
						//swap
						randomVal = arr[randomIndex];
						arr[randomIndex] = arr[i];
						arr[i] = randomVal;
						//array has been shuffled
					}
					
					Heap newHeap = new Heap(100);
					//sequential insertions add
					newHeap.add(arr);
					averageBasic+=newHeap.getNumSwaps();
					//optimized add
					newHeap.addOptimal(arr);
					averageOptimal+=newHeap.getNumSwaps();
				}
				
				averageBasic /= 20;
				averageOptimal /= 20;
				
				System.out.println("The average Number of Swaps for Basic Insertion: " + averageBasic);
				System.out.println("The average Number of Swaps for Optimized Insertion: "+ averageOptimal);
			//make array of 1-100;
			}else if(randOrSeq==2){
				
				Heap thisHeap = new Heap(100);
				
				System.out.println("In this simulation an array of 0-99 was used.\nAfterwards, the root was removed 10 times");
				thisHeap.add(arr);
				System.out.print("Basic Insertion Heap: ");
				thisHeap.printHeap();
				thisHeap.addOptimal(arr);
				System.out.print("Optimized Insertion Heap: ");
				thisHeap.printHeap();
				
				for(int n=1;n<=10;n++){
					thisHeap.remove();
				}
				System.out.println("10 Removal Performed, Array is now: ");
				thisHeap.printHeap();

				
			}else if(randOrSeq==3){
				System.out.println("Thank you!");
				return;
			}
	
		}while(randOrSeq!=3);
	}
	public Heap(int n){
		heapArray = new int[n];
	}
	//adds values one at a time
	public void add(int [] newArr){
		count = 0;
		numSwaps = 0;
		
		//gets value from the new array
		for(int j=0;j<=heapArray.length-1;j++){
			//choose a value
			int n= newArr[j];
			//put value at next position
			heapArray[count]=n;
			
			//reheap as it is added
			reheapifyUpward(n);
			count++; //increment count cursor at next position to reheap
		}
	}
	
	public void reheapifyUpward(int n){
		int currentPos = count;		
		for(int parent=(currentPos-1)/2; currentPos >0; parent=(currentPos-1)/2){	//starts at n's parent, increments to next parent each time			
			if(n>heapArray[parent]){
				swap(currentPos, parent);
				//resets previous position
				currentPos = parent;
			}else{
				heapArray[currentPos]=n;
				break;
			}
		}
	}
	//adds all values to the tree, to be sorted after
	public void addOptimal(int[] newArr){
		count = heapArray.length-1;
		numSwaps = 0;
		//heapArray already made
		heapArray = newArr;
		
		int n = heapArray.length-1;
		
		for(int i=heapArray.length-1;i>=0;i--){
			reheapifyDownward(heapArray[i]);
			count--;
		}
	}
	public void reheapifyDownward(int n){
		int currentPos = count;
		do{
			int left = 2*currentPos+1;
			int right = 2*currentPos+2;
			
			if(left<heapArray.length && right<heapArray.length){//left and right within index

				if(heapArray[left]>heapArray[right]){ //left greater than right
					if(heapArray[left]>heapArray[currentPos]){ //compare left and element
						swap(left,currentPos);
						currentPos = left;
					}else
						return;
					
				}else{	//right greater than left
					if(heapArray[right]>heapArray[currentPos]){	//compare right to root
						swap(right,currentPos); //if right is greater, swap, current pos changes
						currentPos = right;
					}else
						return;
				}
			}else if(left<heapArray.length){ //has left, no right
				//compare left with root
				if(heapArray[left]>heapArray[currentPos]){ //left is greater than root, swap
					swap(left, currentPos);
					currentPos = left;
					return; //if has left and no right, is last element
				}else
					return;

			}else{ //has no left or right(out of index), is leaf
				break;
			}
			

		}while(currentPos<heapArray.length);
		
	}
	public void remove(){
		
		int lastNum = heapArray[heapArray.length-1];
		count = 0;
		heapArray[0]=lastNum;
		
		int[]newArray = new int [heapArray.length-1];
		
		//new array of size-1
		for(int i=0;i<newArray.length;i++){
			newArray[i] = heapArray[i];
		}
		heapArray = newArray;		
		reheapifyDownward(lastNum);
		
	}
	public int getNumSwaps(){
		return numSwaps;
	}
	public void printHeap(){
		for(int i=0;i<10;i++){
			System.out.print(heapArray[i] + " ");
		}
		System.out.println();
	}
	public void swap(int x,int y){
		int holder = heapArray[x];
		heapArray[x]=heapArray[y];
		heapArray[y]=holder;
		numSwaps++;
	}
}
