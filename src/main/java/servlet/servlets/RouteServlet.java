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
import servlet.servlets.coordinates.Coordinate;
import servlet.servlets.localize.LocalizerUtil;

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
		String startRoomNo = request.getParameter("startNo");
		String destinationRoomNo = request.getParameter("destinationNo");
		if (!isValidNo(startRoomNo) || !isValidNo(destinationRoomNo)) {
			String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc()));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
			LocalizerUtil.setCurLocation(startRoomNo);
			LocalizerUtil.setDestination(destinationRoomNo);
			List<Coordinate> coordinatesPaths = roomNoCoordinatesTransferService.transfer();
			String json = new Gson().toJson(ServerResponse.createBySuccess(coordinatesPaths));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	private boolean isValidNo(String roomNo) {
		if (roomNo == null || roomNo == "") {
			return false;
		}
		for (int i = roomNo.length() - 1; i >= 0; i--) {
			if (!Character.isDigit(roomNo.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
