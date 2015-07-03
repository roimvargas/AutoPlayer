import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class Root {
    
        public List<Stops> stops = new ArrayList<Stops>();
        public String route;
        public String direction; //Fix: Make public 
    
        public static class Stops {
    
            //Fix: Change attribute name to match input file arrival_time.
            public Map<String, String[]> arrival_time = new HashMap<String, String[]>();
            //Fix: Change attribute name to match input file stop_name.
            public String stop_name;
    
        }
    }