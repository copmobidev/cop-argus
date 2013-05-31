package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.common.UserAgent;
import com.cop.mobi.poi.service.POIService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
import com.cop.mobi.rest.core.UserAgentUtil;

/**
 * 
 * @author zhufeng.liu
 * 
 */
@Path("/poi")
@Produces("application/json;charset=UTF-8")
public class POIAction extends AbstractAction {
	private static final String Tag = "AccountAction";

	private static POIService poiService;

	static {
		init();
	}

	private static void init() {
		try {
			poiService = (POIService) SpringApplicationContext
					.getBean("poiService");
		} catch (Exception e) {
			log.error(String.format("%s:init() error", Tag), e);
		}
	}

	@POST
	@Path("/geoArea")
	public Response config(@HeaderParam("ua") String ua,
			@FormParam("token") String token,
			@FormParam("parentId") int parentId) {
		Result result = null;
		try {
			UserAgent userAgent = UserAgentUtil.parseUserAgent(ua);
			Token tk = TokenUtil.parseToken(token);
			if (userAgent == null || tk == null) {
				result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
			} else {
				result = poiService.geoAreaList(parentId);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "get config error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/localFuelStation")
	public Response localGasStation(@HeaderParam("ua") String ua,
			@FormParam("token") String token, @FormParam("lat") double lat,
			@FormParam("lng") double lng, @FormParam("range") int range) {
		Result result = null;
		try {
			UserAgent userAgent = UserAgentUtil.parseUserAgent(ua);
			Token tk = TokenUtil.parseToken(token);
			if (userAgent == null || tk == null) {
				result = new Result(ResultStatus.RS_FAIL, PARAM_ERROR_MSG);
			} else {
				result = poiService.localFuelStation(lat, lng, range);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "get config error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}
}
