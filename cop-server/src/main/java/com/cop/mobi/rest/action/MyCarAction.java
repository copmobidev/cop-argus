package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.DateSpan;
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
				DateSpan dateSpan = new DateSpan(span, beginTime, endTime);
				result = myCarService.getDriveRoutes(token.getMcid(), dateSpan);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "status request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);

		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/upload")
	public Response uploadDriveRoutes(@FormParam("token") String token,
			@FormParam("routes") String routes) {
		Result result = null;
		try {
			Token tk = TokenUtil.parseToken(token);
			if (TokenUtil.isValid(tk)) {
				result = new Result(ResultStatus.RS_EXPIRED, QUERY_LIMIT_MSG);
			} else if (StringUtils.isNotBlank(routes)) {
				String[] tmp = routes.split("\\;");
				if (tmp == null || tmp.length <= 0) {
					result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
				}
				result = myCarService.uploadDriveRoutes(tk.getMcid(), tmp);
			} else {
				result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag,
					"upload drive routes data request error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/diagnose")
	public Response suggest(@FormParam("token") String token,
			@FormParam("codes") String codes) {
		Result result = null;
		try {
			Token tk = TokenUtil.parseToken(token);
			if (TokenUtil.isValid(tk)) {
				result = new Result(ResultStatus.RS_EXPIRED, QUERY_LIMIT_MSG);
			} else if (StringUtils.isNotBlank(codes)) {
				String[] tmp = codes.split("\\|");
				if (tmp == null || tmp.length <= 0) {
					result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
				} else {
					result = diagnoseService.diagnose(tk, tmp);
				}
			} else {
				result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
			}

		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "suggest error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}
}
