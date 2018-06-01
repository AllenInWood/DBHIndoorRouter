package servlet.servlets.routing;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class RouterCalculatorImpl implements RouterCalculator{

    private Map<String, Map<String, Double>> floor;

    @Inject
    public RouterCalculatorImpl(@Named("DBHMap") Map<String, Map<String, Double>> DBHMap) {
        this.floor = DBHMap;
    }

    public List<String> getRoutingList(String start, String destination) {
        Map<String, Double> distance = new HashMap<String, Double>();
        Map<String, String> lastNode = new HashMap<String, String>();
        LinkedList<String> res = new LinkedList<String>();
        Queue<String> q = new LinkedList<String>();
        distance.put(start, Double.valueOf(0));
        lastNode.put(start, "");
        q.offer(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++ i) {
                String node = q.poll();
                Double curDist = distance.get(node);
                Iterator iter = floor.get(node).entrySet().iterator();
                while (iter.hasNext()) {
                    HashMap.Entry<String, Double> entry = (HashMap.Entry) iter.next();
                    String nextHop = entry.getKey();
                    Double dist = entry.getValue();
                    if (distance.containsKey(nextHop)) {
                        if (distance.get(nextHop) > curDist + floor.get(node).get(nextHop)) {
                            distance.put(nextHop, curDist + floor.get(node).get(nextHop));
                            lastNode.put(nextHop, node);
                            q.offer(nextHop);
                        }
                    } else {
                        distance.put(nextHop, curDist + floor.get(node).get(nextHop));
                        lastNode.put(nextHop, node);
                        q.offer(nextHop);
                    }
                }
            }
        }
        String hop = destination;
        while (!hop.equals("")) {
            res.addFirst(hop);
            hop = lastNode.get(hop);
        }
        return res;
    }
}
