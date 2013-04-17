package com.cop.mobi.rest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/mycar")
@Produces("application/json;charset=UTF-8")
public class MyCarAction extends AbstractAction {

	private static final String Tag = "MyCarAction";

	private static MyCarService myCarService;

	static {
		try {
			myCarService = (MyCarService) SpringApplicationContext
					.getBean("myCarService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@POST
	@Path("/info")
	public Response carInfo(@FormParam("mcid") int mcid) {
		Result result = null;
		try {
			result = myCarService.getMyCarById(mcid);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "info request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/driveroutes")
	public Response driveRoutes(@FormParam("mcid") int mcid,
			@FormParam("begin_time") long beginTime,
			@FormParam("end_time") long endTime) {
		Result result = null;
		try {
			result = myCarService.getDriveRoutes(mcid, beginTime, endTime);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "status request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);

		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/uploaddriveroutes")
	public Response uploadDriveRoutes(@FormParam("mcid") int mcid,
			@FormParam("routes") String routes) {
		Result result = null;
		try {
			List<DriveRoutePo> drs = parseDriveRoutes(routes);
			result = myCarService.uploadDriveRoutes(drs);

		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag,
					"upload drive routes data request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/diagnose")
	public Response diagnose(@FormParam("codes") String codes) {

		return null;
	}

	private List<DriveRoutePo> parseDriveRoutes(String routes) throws Exception {
		StringTokenizer st = new StringTokenizer(routes, "|");
		if (st.countTokens() <= 0) {
			return null;
		}
		List<DriveRoutePo> drPos = new ArrayList<DriveRoutePo>();
		while (st.hasMoreTokens()) {
			try {
				StringTokenizer subSt = new StringTokenizer(st.nextToken());
				String route = subSt.nextToken();
				long beginTime = Long.parseLong(subSt.nextToken());
				long endTime = Long.parseLong(subSt.nextToken());
				DriveRoutePo drPo = new DriveRoutePo();
				drPo.setRoute(route);
				drPo.setBeginTime(beginTime);
				drPo.setEndTime(endTime);
				drPos.add(drPo);
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag,
						"parse upload drive routes data error"), e);
			}
		}
		return drPos;
	}
}
