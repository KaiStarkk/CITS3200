import java.util.*;


public class groundstester {


    public static void main(String [ ] args) {
        groundio ground = new groundio("/Users/Markcuz/Desktop/groundsio/grounds.txt");
        
        List<String[]> grounds;
        
        grounds = ground.read();
        
       /* for (String[] strArr : grounds) {
            System.out.println(Arrays.toString(strArr));
        }*/
        
        for (int i =0; i<grounds.size(); i++) {
            String[] strArr = grounds.get(i);
            System.out.println(Arrays.toString(strArr));
        }
        
    }

}