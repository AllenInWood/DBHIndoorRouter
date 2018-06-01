package servlet.servlets.routing;

import java.util.List;

public interface RouterCalculator {

    List<String> getRoutingList(String start, String destination);
}
