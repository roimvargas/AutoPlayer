
//Test Edit

public class Main {
  
  private static final String IP_PORT_PATTERN = 
    "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}" +
    "([01]?\\d\\d?|2[0-4]\\d|25[0-5]):" +
       "0*(?:6553[0-5]|" +  
       "655[0-2][0-9]|" +
       "65[0-4][0-9]{2}|" +
       "6[0-4][0-9]{3}|" +
       "[1-5][0-9]{4}|" +
       "[1-9][0-9]{1,3}|" +
       "[0-9])";
  
  private static final String CACHED_PATTERN_1 = 
    "("+IP_PORT_PATTERN+"|"+IP_PORT_PATTERN+":cached|cached)";
  
  private static final String CACHED_PATTERN_2 = 
    "^(?!$)("+IP_PORT_PATTERN+")?(((?<!^):)?cached)?$";
  

  public static void main(String[] args) {
    
    
    
    //String regex = CACHED_PATTERN_1;
    String regex = CACHED_PATTERN_2;
    String str;
    
    System.out.println(regex);
    
    System.out.println("\nThose must pass...");
    str = "100.100.100.100:100";  
    System.out.println(str+"? "+ str.matches(regex));
    str = "10.251.251.251:65535";  
    System.out.println(str+"? "+ str.matches(regex));
    str = "10.251.251.251:65535:cached";  
    System.out.println(str+"? "+ str.matches(regex));
    str = "cached";
    System.out.println(str+"? "+ str.matches(regex));
    
    System.out.println("\nThose must fail...");
    str = ":cached";  
    System.out.println(str+"? "+ str.matches(regex));
    str = "10A251B251C251:65535";  
    System.out.println(str+"? "+ str.matches(regex));
  }

}
