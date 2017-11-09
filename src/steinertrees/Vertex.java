/*package steinertrees;

import java.util.*;

public class Vertex {

    private int id, distance;
    private Boolean visited;
    private Map<Vertex, Integer> neighbors;
    private Vertex prev;
    
    public Vertex(int name){
        id = name;
        neighbors = new HashMap<>();
        visited = false;
        distance = Integer.MAX_VALUE;
        prev = null;
    }
    
    public int get_id(){
        return this.id;
    }
    
    public void add_neighbors(Vertex next, int weight){
        this.neighbors.put(next, weight);
    }
    
    public Map<Vertex, Integer> get_neighbors(){
        return this.neighbors;
    }
    
    public int get_weight(Vertex neighbor){
        return this.neighbors.get(neighbor);
    }
    
    public int get_distance(){
        return this.distance;
    }
    
    public Vertex get_previous(){
        return this.prev;
    }
    
    public void set_previous(Vertex previous){
        this.prev=previous;
    }
    
    public void set_distance(int dist){
        this.distance=dist;
    }
    
    public void set_visited(){
        this.visited=true;
    }
    
    public Boolean is_visited(){
        return this.visited;
    }
}
*/