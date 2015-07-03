class Tuples{

   public int[] Rank = new int[2];
   
   Tuples(int a, int b){
     Rank[0] = a;
     Rank[1] = b;
   }
   
   public String toString(){
     return "("+Rank[0]+","+Rank[1]+");";
   }
}
