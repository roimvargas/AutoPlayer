import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class SortTuples {

//Test commit 2
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<Tuples> tupleArr = new ArrayList<Tuples>();

        tupleArr.add(new Tuples(0,1));
        tupleArr.add(new Tuples(1,1));
        tupleArr.add(new Tuples(2,1));
        tupleArr.add(new Tuples(0,1));
        tupleArr.add(new Tuples(1,1));
        tupleArr.add(new Tuples(2,1));
        tupleArr.add(new Tuples(0,3));
        tupleArr.add(new Tuples(0,2));
        tupleArr.add(new Tuples(2,-2));
        tupleArr.add(new Tuples(2,0));

        Tuples[] s = tupleArr.toArray(new Tuples[]{});

        Arrays.sort(s,new Comparator<Tuples>() {

            public int compare(Tuples a, Tuples b) {

                /*if(a.Rank[0]==b.Rank[0]){
          if((a.Rank[1]<b.Rank[1])){
            return 1;
          }else{
            return 0;
          }
        }else{
          if(a.Rank[0]<b.Rank[0]){
            return 1;
          }else{
            return 0;
          }
        }*/

                int i = 
                    (a.Rank[0]==b.Rank[0])?
                            ((a.Rank[1]==b.Rank[1])?
                                    0:
                                        ((a.Rank[1]<b.Rank[1])?
                                                -1:
                                                    1
                                        ))
                                        :((a.Rank[0]<b.Rank[0])?
                                                -1:
                                                    1);

                            /*if(a.Rank[0]==b.Rank[0]){ 
          if(a.Rank[1]==b.Rank[1]){ // a == b
            return 0;
          }else if(a.Rank[1]<b.Rank[1]){ // a < b
            return -1;
          }else{ // a > b
            return 1;
          }
        }else{ // a != b
          if(a.Rank[0]<b.Rank[0]){ // a < b
            return -1;
          }else{ //a > b
            return 1;
          }
        } */
                            return i;

                            //return (a.Rank[0]==b.Rank[0]) ? ((a.Rank[1]<b.Rank[1]) ? 1:0) :((a.Rank[0]<b.Rank[0])? 1:0);
            }
        });

        for(Tuples t :s){
            System.out.println(t.toString());
        }

    }

    /*public int compare(Tuples a, Tuples b) {
        if(a.Rank[0]==b.Rank[0]){ 
            if(a.Rank[1]==b.Rank[1]){ // a == b
                return 0;
            }else if(a.Rank[1]<b.Rank[1]){ // a < b
                return -1;
            }else{ // a > b
                return 1;
            }
        }else{ // a != b
            if(a.Rank[0]<b.Rank[0]){ // a < b
                return -1;
            }else{ //a > b
                return 1;
            }
        } 
    }*/
    
    public int compare(Tuples a, Tuples b) {
       return (a.Rank[0]==b.Rank[0])?((a.Rank[1]==b.Rank[1])?0:((a.Rank[1]<b.Rank[1])?-1:1)):((a.Rank[0]<b.Rank[0])?-1:1);
    }

    /*    int i = 
        (a.Rank[0]==b.Rank[0])?
            ((a.Rank[1]==b.Rank[1])?
                0:
            ((a.Rank[1]<b.Rank[1])?
                -1:
                 1
             ))
        :((a.Rank[0]<b.Rank[0])?
            -1:
             1);
     */  
}
