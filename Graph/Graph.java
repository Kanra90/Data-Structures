import java.io.*;
import java.util.*;

public class Graph
{	
    static Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
	static Vertex[] vertices = new Vertex[20];
	
	public static void main(String args[]) throws FileNotFoundException {
		//data processed, array of vertices filled.
		ProcessData();
		
		Scanner kb = new Scanner(System.in);
		String option = null;
		
		System.out.print("Command?");
		option = kb.nextLine();
		while(!option.equals("E")){
			if(option.equals("H")){
				
				System.out.println("Q   Query the city information by entering the city code.");
				System.out.println("D   Find the minimum distance between two cities.");
				System.out.println("I   Insert a road by entering two city codes and distance.");
				System.out.println("R   Remove an existing road by entering two city codes.");
				System.out.println("H   Display this message.");
				System.out.println("E   Exit.");
					
			}else if(option.equals("Q")){
				String query;
				System.out.print("Please enter the city code:");
				query = kb.nextLine();
				
				Vertex v = vertexMap.get(query);
				System.out.println(v.cityNum + " " + v.cityInit + " " + v.cityName + " " + v.cityPop + " " + v.cityElv);
			}else if(option.equals("D")){
				String first;
				String second;
				
				System.out.print("City Codes: ");
				String line = kb.nextLine();
				
				Scanner newScan = new Scanner(line);
				first = newScan.next();
				second = newScan.next();
				//get vertices
				Vertex from = vertexMap.get(first);
				Vertex to = vertexMap.get(second);
				Dijkstra(from);
				System.out.println("The minimum distance between " + from.cityName + " and " + to.cityName + " is " + to.minDistance);
				System.out.print("The Path: ");
				System.out.print(getShortestPathTo(to));
				System.out.println();
				
			}else if(option.equals("I")){

				System.out.print("City Codes and Distance: ");
				String line = kb.nextLine();
				
				Scanner newScan = new Scanner(line);
				String first = newScan.next();
				String second = newScan.next();
				String dist = newScan.next();
				
				int weight = Integer.parseInt(dist);
				//get vertices
				Vertex from = vertexMap.get(first);
				Vertex to = vertexMap.get(second); 
				Edge e = new Edge(to, weight);
				from.addEdge(e);
				System.out.println("You have inserted a road from " + from.cityName + " to " + to.cityName);
				
			}else if(option.equals("R")){
				System.out.print("City Codes: ");
				String line = kb.nextLine();
				
				Scanner newScan = new Scanner(line);
				String first = newScan.next();
				String second = newScan.next();
				//get vertices
				Vertex from = vertexMap.get(first);
				Vertex to = vertexMap.get(second);
				
				System.out.println("Sorry! this is not functional at the Moment!");
				//from.edges.remove(index)
				
				//from.removeEdge(to);
				
				//get city code
				//from.edges.remove(to);
								
			}
			System.out.print("Command?");
			option = kb.nextLine();
		}
		
		System.out.print("Thank you!");
    }
    public static void Dijkstra(Vertex source)
    {
        source.minDistance = 0;
        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();
      	Q.add(source);

      	while (!Q.isEmpty()) {
		//while isn't empty inquire head of Queue
      		Vertex inquiry = Q.poll();
      			//for(int i=0;i<inquiry.edges.length;i++)
      			// Visit each edge, until ledge is at destination
      			for (Edge e : inquiry.edges)
      			{
      				//System.out.print(e.target);
      					Vertex v = e.target;
      					double weight = e.weight;
      					//possible edge distance is weight + current minDistance
      					double currentEdgeDist = inquiry.minDistance + weight;
                
      					//if this edge is better than current min,
      					if (currentEdgeDist < v.minDistance) {
      						//System.out.println("min changed!");
      						//remove from Queue
      						Q.remove(v);
      						//current vertex is minimum, change minDistance
      						v.minDistance = currentEdgeDist;
      						//link inquiry to v
      						v.previous = inquiry;
      						Q.add(v);
      				}
      			}
        }
    }
    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous){
            path.add(vertex);
        	Collections.reverse(path);
        }   	
        	return path;
    }
    public static void ProcessData() throws FileNotFoundException{
    	Scanner newFile = new Scanner(new File("cities.dat"));    	
    	while(newFile.hasNext()){
    		
    		int num = newFile.nextInt();
    		
    		String finalName;
    		String code = newFile.next();
    		String name = newFile.next();
    		String secondname;
    		//these are the particular vertices that have names with two words in them. 
    		if(num==4 || num== 5|| num==6|| num==7||num==9||num==11||num==12||num==13|| num==14||num==16||num==17){
    			secondname = newFile.next(); 
    			finalName = name.concat(" ");
    			name = finalName.concat(secondname);
    			
    		}
    		String population = newFile.next();
    		String elevation = newFile.next();

    		newFile.nextLine();

    		
    		vertices[num-1] = new Vertex(num,code,name,population,elevation);
    			
    	}
    	Scanner anotherFile = new Scanner(new File("roads.dat"));
    	while(anotherFile.hasNextInt()){
    		int from = anotherFile.nextInt();
    		int to = anotherFile.nextInt();
    		int weight = anotherFile.nextInt();
    		Edge e= new Edge(vertices[to-1],weight);
    		vertices[from-1].addEdge(e);
    	}
    	
    	for(Vertex v: vertices){
    		//Dijkstra(v);
    		vertexMap.put(v.cityInit,v);
    	}
    }
}
class Vertex implements Comparable<Vertex>
{
	public List<Edge> edges = new ArrayList<Edge>();
	//public Edge[] edges;
	public int cityNum;
    public String cityInit;
    public String cityName;
    public String cityPop;
    public String cityElv;
    int counter = 0;
    //list of edges
    //set current distance to infinity
    public double minDistance = Double.POSITIVE_INFINITY;
    //link to previously chosen node
    public Vertex previous;
    
    //set name
    public Vertex(int num, String c, String n, String pop, String ele){
    	cityNum = num;
    	cityInit = c;
    	cityName = n;
    	cityPop = pop;
    	cityElv = ele;
    
    }
    //get name
    public String toString(){
    	return cityName;
    }
    public void addEdge(Edge e){
    	edges.add(e);
    }
    public void removeEdge(Edge e){
    	edges.remove(e);
    }
    //compare vertices
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge
{
    public Vertex target;
    public double weight;
    
    public Edge(Vertex t, double w){
    	target = t;
    	weight = w;
    }
}
