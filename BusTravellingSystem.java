//Algorithm for Bus Traveling System:
//
//        Input the source and destination from the user.
//        Read the bus route map and create a graph representation of the routes and their distances.
//        Use Dijkstra's algorithm to find the shortest path between the source and destination.
//        Print the shortest path and its total distance.
//        Steps for the algorithm:
//
//        Ask the user for the source and destination.
//        Read the bus route map and create a graph representation of the routes and their distances.
//        Initialize an empty dictionary "distances" to keep track of the shortest distance from the source to each node in the graph.
//        Initialize all distances to infinity except the distance to the source which is 0.
//        Initialize an empty dictionary "previous" to keep track of the previous node in the shortest path to each node in the graph.
//        Create an empty set "unvisited" to keep track of the nodes that haven't been visited yet. Add all nodes in the graph to the set.
//        While the "unvisited" set is not empty, do the following:
//        a. Find the node with the smallest distance from the source in the "unvisited" set. Call this node "current".
//        b. Remove "current" from the "unvisited" set.
//        c. For each neighbor of "current", calculate the distance from the source to the neighbor through "current".
//        If this distance is shorter than the current distance in the "distances" dictionary, update the distance and the "previous" dictionary.
//        Once the destination has been visited, construct the shortest path by starting at the destination and following the "previous"
//        dictionary backwards until the source is reached.
//        Print the shortest path and its total distance.
import java.util.*;

public class BusTravelSystem {
    static Scanner sc = new Scanner(System.in);
    static int[][] graph;
    static int vertices, edges;

    public static void main(String[] args) {
        System.out.print("Enter the number of vertices: ");
        vertices = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        edges = sc.nextInt();

        // initialize the graph
        graph = new int[vertices][vertices];

        // take input for edges
        for (int i = 0; i < edges; i++) {
            System.out.println("Enter edge " + (i + 1) + " details: ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();

            // add the edge to the graph
            graph[src][dest] = cost;
            graph[dest][src] = cost; // if the graph is undirected
        }

        // take input for source and destination
        System.out.print("Enter the source vertex: ");
        int source = sc.nextInt();
        System.out.print("Enter the destination vertex: ");
        int dest = sc.nextInt();

        // find the shortest path using Dijkstra's algorithm
        int[] dist = dijkstra(graph, source);

        // print the shortest distance and path
        System.out.println("Shortest distance from " + source + " to " + dest + " is: " + dist[dest]);
        System.out.println("Path is: " + getPath(dist, source, dest));
    }

    // Dijkstra's algorithm
    static int[] dijkstra(int[][] graph, int source) {
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int u = findMinDist(dist, visited);
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist;
    }

    //  method to find the vertex with minimum distance
    static int findMinDist(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    //  method to find the shortest path
    static String getPath(int[] dist, int source, int dest) {
        StringBuilder path = new StringBuilder();

        while (dest != source) {
            path.insert(0, dest + " ");
            for (int v = 0; v < vertices; v++) {
                if (graph[dest][v] != 0 && dist[dest] == dist[v] + graph[dest][v]) {
                    dest = v;
                    break;
                }
            }
        }
        path.insert(0, source + " ");
        return path.toString();
    }
}
