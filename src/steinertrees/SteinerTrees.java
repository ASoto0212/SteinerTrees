package steinertrees;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.Dimension;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.*;

public class SteinerTrees {

    public static void main(String[] args) throws FileNotFoundException {
        String path = new File("src/steinertrees/test1.txt").getAbsolutePath();
        UndirectedSparseGraph mst = new UndirectedSparseGraph();
        File test = new File(path);
        Scanner scan = new Scanner(test);
        String getNext;
        Graph graph= new Graph();
        Set<Integer> term = new HashSet<>();
        int nodes, edges, terminals, start, end, weight;
        while(scan.hasNextLine()){
            getNext=scan.nextLine();
            switch(getNext){
                case "SECTION Graph":
                    scan.skip("Nodes ");
                    nodes=scan.nextInt();
                    scan.nextLine();
                    scan.skip("Edges ");
                    edges=scan.nextInt();
                    getNext=scan.nextLine();
                    graph.set_edges(edges);
                    graph.set_nodes(nodes);
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
                case "SECTION Terminals":
                    scan.skip("Terminals ");
                    terminals=scan.nextInt();
                    scan.nextLine();
                    for(int i=0; i<terminals; i++){
                        String isT=scan.next();
                        if (isT.equals("T "))
                            scan.skip("T ");
                        term.add(scan.nextInt());
                    }
                break;    
            }
        }
        System.out.println(graph.edges.size());
        for(int i=0;i<graph.numEdges;i++){
            System.out.println("Start: " + (graph.edges.get(i).get_start()) + 
                                " End: " + (graph.edges.get(i).get_end()) + 
                                " Weight: " + graph.edges.get(i).get_weight());
        }
        Edge [] minm = graph.MST();
        for(int i=0;i<graph.numNodes;i++){
            mst.addVertex(minm[i].get_start());
            mst.addVertex(minm[i].get_end());
        }
        for(int i=0;i<minm.length;i++){
            mst.addEdge(i, minm[i].get_start(), minm[i].get_end());
        }
        
        BasicVisualizationServer vs = new BasicVisualizationServer(new FRLayout(mst), new Dimension (1000,1000));
        
        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
