package fastalgorithm;

import java.util.ArrayList;

  public class Dijkstra {
  
     // Dijkstra's algorithm to find shortest path from s to all other nodes
     public static int [] dijkstra (EdgeWeightedGraph G, int s) {
        final double [] dist = new double [G.size()];  // shortest known distance from "s"
        final int [] pred = new int [G.size()];  // preceeding node in path
        final boolean [] visited = new boolean [G.size()]; // all false initially
  
        
        
         for (int i=0; i<dist.length; i++) {
           dist[i] = Double.MAX_VALUE;
        }
        dist[s] = 0;
        
  
        for (int i=0; i<dist.length; i++) {
           final int next = minVertex (dist, visited);
           if (next == -1){
               // No neighbors
               break;
           }
           visited[next] = true;
  
           // The shortest path to next is dist[next] and via pred[next].
  
           final int [] n = G.neighbors (next);
           if (n == null){
               // No neighbors
               return null;
           }
           //System.out.println(String.format("Neighbors of %d are %d",next,n.length));
           for (int j=0; j<n.length; j++) {
              final int v = n[j];
              final double d = dist[next] + G.getWeight(next,v);
              if (dist[v] > d) {
                 dist[v] = d;
                 pred[v] = next;
              }
           }
        }
        return pred;  // (ignore pred[s]==0!)
     }
  
     private static int minVertex (double [] dist, boolean [] v) {
        double x = Double.MAX_VALUE;
        int y = -1;   // graph not connected, or no unvisited vertices
        for (int i=0; i<dist.length; i++) {
           if (!v[i] && dist[i] < x){
               y=i;
               x=dist[i];
               //System.out.println(String.format("Min vertex found at %d",y));
           }
        }
        return y;
     }
  
     public static ArrayList<Integer> printPath (EdgeWeightedGraph G, int [] pred, int s, int e) {
        final java.util.ArrayList<Integer> path = new java.util.ArrayList<Integer>();
        int x = e;
        while (x!=s) {
           path.add (0,x);
           x = pred[x];
       }
        path.add (0, s);
        return path;
     }
  
  }