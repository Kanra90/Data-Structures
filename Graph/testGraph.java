import java.util.*;
public class testGraph<E> {
    private boolean[][] edges; // edges[i][j] is true if there is a vertex from i to j
    private E[] labels; // labels[i] contains the label for vertex i

    public static void main(String[]args){
    	
    	testGraph <Object> newGraph = new testGraph<Object>(5);
    	
    	
    	
    }
    // Constructor: initialize a Graph with n vertices, no edges, and null labels
    public testGraph(int n) {
        edges = new boolean[n][n]; // All values initially false
        labels = (E[]) new Object[n]; // All values initially null
    }

    // Add an edge
    public void addEdge(int source, int target) {
        edges[source][target] = true;
    }

    // Accessor method to get the label of a vertex of this Graph 
    public E getLabel(int vertex) {
        return labels[vertex];
    }

    // Test whether an edge exists
    public boolean isEdge(int source, int target) {
        return edges[source][target];
    }

    // Obtain a list of neighbors of a specified vertex of this Graph
    public int[] neighbors(int vertex) {
        int i;
        int count = 0;
        int[] answer;

        for (i = 0; i < labels.length; i++) {
            if (edges[vertex][i])
                count++;
        }
        answer = new int[count];
        count = 0;
        for (i = 0; i < labels.length; i++) {
            if (edges[vertex][i])
                answer[count++] = i;
        }
        return answer;
    }

    // Remove an edge
    public void removeEdge(int source, int target) {
        edges[source][target] = false;
    }

    // Change the label of a vertex of this Graph
    public void setLabel(int vertex, E newLabel) {
        labels[vertex] = newLabel;
    }

    // Accessor method to determine the number of vertices in this Graph
    public int size() {
        return labels.length;
    }
    public void ShortestPath(int start, int end){
    	
    }
}