package servlet.servlets.fulltext;

import servlet.servlets.common.CompleteItem;

import java.util.List;

public interface FulltextService {

    List<CompleteItem> listDestinationsByFullTextIndexing(String query);

    String getDestinationID(String destination);
}
