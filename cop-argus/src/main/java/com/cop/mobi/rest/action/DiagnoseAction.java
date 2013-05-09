package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.service.DiagnoseService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/diagnose")
@Produces("application/json;charset=UTF-8")
public class DiagnoseAction extends AbstractAction {

	private static final String Tag = "DiagnoseAction";

	private static DiagnoseService diagnoseService;

	static {
		init();
	}

	private static void init() {
		try {
			diagnoseService = (DiagnoseService) SpringApplicationContext
					.getBean("diagnoseService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@POST
	@Path("/items")
	public void getItems(@FormParam("key") String key) {

	}

	@POST
	@Path("/suggestion")
	public Response suggest(@FormParam("mcid") int mcid,
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
