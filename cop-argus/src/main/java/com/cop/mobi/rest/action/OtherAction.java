package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.cop.mobi.rest.core.AbstractAction;

/**
 * 包括config，feedback在内零散接口
 * 
 * @author chris.liu
 * 
 */
@Path("/other")
public class OtherAction extends AbstractAction {
	private static final String Tag = "AccountAction";

	@POST
	@Path("/config")
	public Response config(@HeaderParam("ua") String ua,
			@FormParam("token") String token) {

		return null;
	}

	@POST
	@Path("/feedback")
	public Response feedback(@HeaderParam("ua") String ua,
			@FormParam("token") String token,
			@FormParam("content") String content) {

		return null;
	}
}
