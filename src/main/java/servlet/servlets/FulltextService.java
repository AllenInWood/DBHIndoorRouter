package servlet.servlets;

import servlet.servlets.common.CompleteItem;

import java.util.List;

public interface FulltextService {

    List<CompleteItem> listDestinationsByFullTextIndexing(String currentRoom, String query);

    String getDestinationID(String destination);
}
