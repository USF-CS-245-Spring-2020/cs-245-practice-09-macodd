package Practice09;

import java.util.List;
import java.util.ArrayList;

/**
 * Mark Codd
 * Prof. David Guy Brizan
 * TA: Razan Said
 * CS 245-01
 *
 * Practice09 - Topological sort (Graphs)
 * --------------------------------------
 * Graph implementation that allows for topological sorting
 *
 */

public class GraphImplementation implements Graph {

    private final int[][] adjacencyMatrix;  // 2D array representation of graph

    // Constructor
    public GraphImplementation(int vertices){
        adjacencyMatrix = new int [vertices][vertices];  // initial size of graph
    } // end constructor

    /**
     * Function : addEdge
     * ------------------
     * Adds a path between two vertices.
     * Represented by source being the first index and
     * target being the second index.
     * @param source : source vertex
     * @param target : destination vertex
     * @throws Exception : out of bounds
     */
    public void addEdge(int source, int target) throws Exception {
        if (((source < 0) || (source > adjacencyMatrix.length)) ||
                ((target < 0) || (target > adjacencyMatrix.length))){
            throw new Exception("Out of bounds");
        } // endif
        if (source != target)
            adjacencyMatrix[source][target] = 1;  // create path in 2D array
    }  // end addEdge

    /**
     * Function : neighbors
     * --------------------
     * List containing all the vertices connected to
     * passed vertex.
     * @param vertex : index of vertex in graph
     * @return : arraylist of connected vertices
     * @throws Exception : out of bounds
     */
    public List<Integer> neighbors(int vertex) throws Exception{
        if (vertex < 0 || vertex > adjacencyMatrix.length)
            throw new Exception("Out of bounds");

        List<Integer> adjacent = new ArrayList<>();

        for(int i = 0; i < adjacencyMatrix.length; i++)
            if(adjacencyMatrix[vertex][i] == 1)
                adjacent.add(i);  // adds index of adjacent vertex
        return adjacent;
    }  // end neighbors

    /**
     * Function : topologicalSort
     * --------------------------
     * sorts list based on vertices found with no incoming edges.
     * prints arraylist after completed.
     * @return arraylist of sorted items
     */
    public List<Integer> topologicalSort(){
        List<Integer> arr = new ArrayList<>();

        int[] incidents = setIncidents(); // sets array with current edges

        for (int i = 0; i < incidents.length; i++) {
            int v = noIncidents(incidents);  // returns first vertex with no incidents
            if (v != -1) {
                arr.add(v);
                incidents[v] = -1;  // removes incident from array
                for(int j = 0; j < incidents.length; j++)
                    if (adjacencyMatrix[v][j] == 1)
                        incidents[j] -= 1;  // removes the edge from vertices
            }  // endif
        } // endfor
        System.out.println(arr);  // prints (topological) sorted array
        return arr;
    }  // end topologicalSort

    /**
     * Function : setIncidents
     * -----------------------
     * checks the index of the array and assigns the number of
     * outgoing edges that it has.
     * @return array with vertices attached to each index
     */
    private int[] setIncidents(){
        int[] incidents = new int[adjacencyMatrix.length];
        for (int v = 0; v < adjacencyMatrix.length; v++)
            for (int w = 0; w < adjacencyMatrix.length; w++)
                incidents[v] += adjacencyMatrix[w][v];

        return incidents;
    }  // end setIncidents

    /**
     * Function : noIncidents
     * ----------------------
     * finds first vertex index with value zero
     * @param incidents : array of current values
     * @return index or unfound ( -1)
     */
    private int noIncidents (int [] incidents) {
        for (int i = 0; i < incidents.length; i++)
            if (incidents[i] == 0)
                return i;
        return -1;
    } // end noIncidents
}  // end Graph Implementation
