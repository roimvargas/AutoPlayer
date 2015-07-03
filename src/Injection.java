import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

    public class Injection {
    
        public static void main(String[] args) throws Exception {
    
            ObjectMapper mapper = new ObjectMapper();
            //InputStream is = Injection.class.getResourceAsStream("./jsonfile.txt");
            //List<Root> roots = mapper.readValue(is, TypeFactory.defaultInstance().constructCollectionType(List.class, Root.class));
    
            //Fix: Read from file without input stream.
            List<Root> roots = mapper.readValue(new File("./jsonfile.txt"), TypeFactory.defaultInstance().constructCollectionType(List.class, Root.class));
            Root root = roots.get(0);
    
            System.out.println("route: " + root.route);
    
            //Fix: Read the first Item of the stops list or iterates it.
            Map<String, String[]> arrivalTimes = root.stops.get(0).arrival_time;
            for (Map.Entry<String, String[]> entry: arrivalTimes.entrySet()) {
                System.out.println(entry.getKey());
                for (String time: entry.getValue()) {
                    System.out.println(time);
                }
            }
        }
    }