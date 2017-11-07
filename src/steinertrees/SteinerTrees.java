package steinertrees;
import java.io.*;
import java.util.*;

public class SteinerTrees {

    public static void main(String[] args) throws FileNotFoundException {
        File test= new File("U:\\My Documents\\NetBeansProjects\\SteinerTrees\\src\\steinertrees\\test1.txt");
        System.out.println(test.canRead());
        Scanner scan = new Scanner(test);
        String getNext;
        int nodes, edges, terminals, start, end, weight;
        while(scan.hasNextLine()){
            getNext=scan.nextLine();
            //System.out.println(next);
            switch(getNext){
                case "SECTION Graph":
                    System.out.println("AUSSIE AUSSIE AUSSIE");
                    scan.skip("Nodes ");
                    nodes=scan.nextInt();
                    System.out.println(nodes);
                    scan.nextLine();
                    scan.skip("Edges ");
                    edges=scan.nextInt();
                    System.out.println(edges);
                    getNext=scan.nextLine();
                    for(int i=0; i<nodes; i++){
                        String isE=scan.next();
                        if (isE.equals("E "))
                            scan.skip("E ");
                        start=scan.nextInt();
                        end=scan.nextInt();
                        weight=scan.nextInt();
                        System.out.println(start + end + weight);
                    }
            }
        }
    }
}
