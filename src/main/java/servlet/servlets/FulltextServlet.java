package servlet.servlets;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import servlet.servlets.common.CompleteItem;
import servlet.servlets.common.ServerResponse;
import servlet.servlets.fulltext.FulltextService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class FulltextServlet extends HttpServlet {

    @Inject
    private FulltextService fulltextService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputRoom = request.getParameter("inputRoom");
        List<CompleteItem> menuTargets = fulltextService
                .listDestinationsByFullTextIndexing(inputRoom);
        String json = new Gson().toJson(
                ServerResponse.createBySuccess(menuTargets));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
