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
import com.cop.mobi.mycar.entity.Span;
import com.cop.mobi.mycar.service.DiagnoseService;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;

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
	private static DiagnoseService diagnoseService;

	static {
		init();
	}

	private static void init() {
		try {
			myCarService = (MyCarService) SpringApplicationContext
					.getBean("myCarService");

			diagnoseService = (DiagnoseService) SpringApplicationContext
					.getBean("diagnoseService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@POST
	@Path("/get")
	public Response driveRoutes(@FormParam("token") String strToken,
			@FormParam("begin_time") Long beginTime,
			@FormParam("end_time") Long endTime, @FormParam("span") Integer span) {
		Result result = null;
		try {
			Token token = TokenUtil.parseToken(strToken);
			if (TokenUtil.isValid(token)) {
				result = new Result(ResultStatus.RS_ERROR, QUERY_LIMIT_MSG);
			} else {
				result = myCarService.getDriveRoutes(token.getMcid(),
						beginTime, endTime, Span.MONTH);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "status request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);

		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/upload")
	public Response uploadDriveRoutes(@FormParam("token") String strToken,
			@FormParam("routes") String routes) {
		Result result = null;
		try {
			Token token = TokenUtil.parseToken(strToken);
			if (TokenUtil.isValid(token)) {
				result = new Result(ResultStatus.RS_ERROR, QUERY_LIMIT_MSG);
			} else {
				List<DriveRoutePo> drps = parseDriveRoutes(routes);
				result = myCarService.uploadDriveRoutes(drps);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag,
					"upload drive routes data request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	private List<DriveRoutePo> parseDriveRoutes(String routes) throws Exception {
		StringTokenizer st = new StringTokenizer(routes, "|");
		if (st.countTokens() <= 0) {
			return null;
		}
		List<DriveRoutePo> drps = new ArrayList<DriveRoutePo>();
		while (st.hasMoreTokens()) {
			try {
				StringTokenizer subSt = new StringTokenizer(st.nextToken());
				String route = subSt.nextToken();
				long beginTime = Long.parseLong(subSt.nextToken());
				long endTime = Long.parseLong(subSt.nextToken());
				DriveRoutePo drp = new DriveRoutePo();
				drp.setRoute(route);
				drp.setBeginTime(beginTime);
				drp.setEndTime(endTime);
				drps.add(drp);
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag,
						"parse upload drive routes data error"), e);
			}
		}
		return drps;
	}

	@POST
	@Path("/diagnose")
	public Response suggest(@FormParam("token") String token,
			@FormParam("codes") String codes) {
		Result result = null;
		try {

		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "suggest error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}
}
