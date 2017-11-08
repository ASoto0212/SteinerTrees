/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steinertrees;

import java.util.*;

/**
 *
 * @author spong_000
 */
public class Graph {
    
    private Set<Integer> nodes;
    private Map<Integer, Vertex> vertices;
    private Map<Integer, Map<Integer, Integer>> distances;
    public Graph(){
        nodes = new HashSet<>();
        vertices = new HashMap<>();
        distances = new HashMap<>();
    }
    
    public void add_node(int newNode){
        if(!this.nodes.contains(newNode)){
            Vertex newVertex = new Vertex(newNode);
            this.nodes.add(newNode);
            this.vertices.put(newNode, newVertex);
        }
    }
    
    public void add_edge(int start, int end, int weight){
        if(!this.nodes.contains(start))
            this.add_node(start);
        if(!this.nodes.contains(end))
            this.add_node(end);
        this.vertices.get(start).add_neighbors(vertices.get(end), weight);
        this.vertices.get(end).add_neighbors(vertices.get(start), weight);
        System.out.println("Start: " + start + " End: " + end + " Weight: " + weight);
    }
    
    public Vertex get_node(int vertex){
        if(this.nodes.contains(vertex))
            return this.vertices.get(vertex);
        else{
            return null;
        }
    }

    public Map<Integer, Vertex> get_nodes(){
        return this.vertices;
    }
    
    public Set<Integer> node_set(){
        return this.nodes;
    }
    
    public int get_distance(Vertex start, Vertex end){
        return start.get_distance() + end.get_distance();
    }
    
    public Stack<Vertex> Shortest_Distance(Vertex start, Vertex goal){
        Stack<Vertex> goalPath= new Stack<>();
        Set<Integer> minTree = new HashSet<>();
        Vertex current=start;
        current.set_distance(0);
        while(minTree.size()<=this.nodes.size()){
            for(Map.Entry<Vertex, Integer> next : current.get_neighbors().entrySet()){
                
            }
        }
        /*while(!unvisited.isEmpty()){
            if(current.is_visited()){
                for(Vertex next : current.get_neighbors().keySet()){
                    if(!next.is_visited()){
                        current=next;
                    }
                }
            }
            
            if(current.get_id()==goal.get_id()){
                goal_path.clear();
                goal_path.push(current);
                while(current.get_previous()!=null){
                    goal_path.push(current.get_previous());
                    current=current.get_previous();
                }
                return goal_path;
            }
            Map<Integer, Vertex> min_v = new HashMap<>();
            for(Vertex next : current.get_neighbors().keySet()){
                int newDist=current.get_distance() + current.get_weight(next);
                min_v.put(newDist, next);
                if(newDist<next.get_distance()){
                    next.set_distance(newDist);
                    next.set_previous(current);
                }
            }
            int min_w = Collections.min(min_v.keySet());
            
            if(unvisited.contains(current.get_id()))
                unvisited.remove(current.get_id());
            current.set_visited();
            current = min_v.get(min_w);
        }
        */
        return goalPath;
    }

}

