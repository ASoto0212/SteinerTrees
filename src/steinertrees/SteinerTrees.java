package steinertrees;
import java.io.*;
import java.util.*;

public class SteinerTrees {

    public static void main(String[] args) throws FileNotFoundException {
        String path = new File("src/steinertrees/test1.txt").getAbsolutePath();
        File test = new File(path);
        Scanner scan = new Scanner(test);
        String getNext;
        Graph graph= new Graph();
        int nodes, edges, terminals, start, end, weight;
        while(scan.hasNextLine()){
            getNext=scan.nextLine();
            //System.out.println(next);
            switch(getNext){
                case "SECTION Graph":
                    scan.skip("Nodes ");
                    nodes=scan.nextInt();
                    scan.nextLine();
                    scan.skip("Edges ");
                    edges=scan.nextInt();
                    getNext=scan.nextLine();
                    for(int i=0; i<edges; i++){
                        String isE=scan.next();
                        if (isE.equals("E "))
                            scan.skip("E ");
                        start=scan.nextInt();
                        end=scan.nextInt();
                        weight=scan.nextInt();
                        graph.add_node(start);
                        graph.add_node(end);
                        graph.add_edge(start, end, weight);
                    }
                    break;
            }
        }
        Scanner inputs= new Scanner(System.in);
        int startv, endv;
        System.out.println("Enter start");
        startv=inputs.nextInt();
        System.out.println("Enter end");
        endv=inputs.nextInt();
        graph.Shortest_Distance(graph.get_node(startv), graph.get_node(endv));
    }
    
}
