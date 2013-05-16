package com.cop.mobi.rest.action;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.common.UserAgent;
import com.cop.mobi.other.entity.Feedback;
import com.cop.mobi.other.service.OtherService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
import com.cop.mobi.rest.core.UserAgentUtil;

/**
 * 包括config，feedback在内零散接口
 * 
 * @author chris.liu
 * 
 */
@Path("/other")
public class OtherAction extends AbstractAction {
	private static final String Tag = "AccountAction";

	private static OtherService otherService;

	static {
		init();
	}

	private static void init() {
		try {
			otherService = (OtherService) SpringApplicationContext
					.getBean("otherService");
		} catch (Exception e) {
			log.error(String.format("%s:init() error", Tag), e);
		}
	}

	@POST
	@Path("/config")
	public Response config(@HeaderParam("ua") String ua,
			@FormParam("token") String token) {
		Result result = null;
		try {
			UserAgent userAgent = UserAgentUtil.parseUserAgent(ua);
			Token tk = TokenUtil.parseToken(token);
			result = otherService.getConfig(userAgent, tk);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "get config error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/feedback")
	public Response feedback(@HeaderParam("ua") String ua,
			@FormParam("token") String token,
			@FormParam("content") String content) {
		Result result = null;
		try {
			Token tk = TokenUtil.parseToken(token);
			Feedback feedback = new Feedback(tk.getUid(), tk.getMcid(), ua,
					content, new Date().getTime());
			result = otherService.feedback(feedback);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "feedback error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}
}
