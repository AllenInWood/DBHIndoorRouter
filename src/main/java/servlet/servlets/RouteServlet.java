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
import servlet.servlets.common.ResponseCode;
import servlet.servlets.common.ServerResponse;
import servlet.servlets.common.VerifyUtil;
import servlet.servlets.coordinates.Coordinate;
import servlet.servlets.routing.RouterCalculator;

@Singleton
public class RouteServlet extends HttpServlet {

	@Inject
	private TransferService transferService;

	@Inject
	private RouterCalculator routerCalculator;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startRoomNo = request.getParameter("startNo");
		String destinationRoomNo = request.getParameter("destinationNo");
		System.out.println("!!!!!!!" + destinationRoomNo);
		if (!VerifyUtil.verify(startRoomNo, destinationRoomNo)) {
			String json = new Gson().toJson(
					ServerResponse.createByErrorCodeMessage(
					ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc()));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
			List<String> roomNoPaths = routerCalculator.getRoutingList(startRoomNo, destinationRoomNo);
			List<Coordinate> coordinatesPaths
					= transferService.transfer(roomNoPaths);
			String json = new Gson().toJson(
					ServerResponse.createBySuccess(coordinatesPaths));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}



}
