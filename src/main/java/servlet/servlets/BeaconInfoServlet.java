package servlet.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import servlet.servlets.beacon.BeaconInfoReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class BeaconInfoServlet extends HttpServlet {

    @Inject
    private BeaconInfoReader beaconInfoReader;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json =  beaconInfoReader.readBeaconFromSourceFile();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
