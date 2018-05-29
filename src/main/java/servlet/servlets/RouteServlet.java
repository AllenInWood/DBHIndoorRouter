package servlet.servlets;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Singleton;
import servlet.servlets.coordinates.Coordinate;

@Singleton
public class RouteServlet extends HttpServlet {

	@Inject
	private RoomNoCoordinatesTransferService roomNoCoordinatesTransferService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Coordinate> coordinatesPaths = roomNoCoordinatesTransferService.transfer();
		String json = new Gson().toJson(coordinatesPaths);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
