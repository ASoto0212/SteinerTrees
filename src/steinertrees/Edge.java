
package steinertrees;

public class Edge {
    private int weight, v1, v2;

    public Edge(int start, int end, int w){
        this.v1=start-1;
        this.v2=end-1;
        this.weight=w;
    }

    public Edge(){
        
    }
    
    public int get_start(){
        return this.v1;
    }
    
    public int get_end(){
        return this.v2;
    }
    
    public int get_weight(){
        return this.weight;
    }
}
