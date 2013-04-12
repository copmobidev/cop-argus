package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/maycar")
public class MyCarAction extends AbstractAction {

	private static final String Tag = "MyCarAction";

	private static MyCarService myCarService;

	static {
		try {
			myCarService = (MyCarService) SpringApplicationContext
					.getBean("myCarService");
		} catch (Exception e) {
			error(Tag, "init error", e);
		}
	}

	@POST
	@Path("/info")
	public Response carInfo(@FormParam("mcid") int mcid) {
		if (myCarService == null) {
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}
		try {
			Result result = myCarService.getMyCarById(mcid);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("/status")
	public Response carStatus(@FormParam("mcid") int mcid,
			@FormParam("beginTime") long beginTime,
			@FormParam("endTime") long endTime) {
		if (myCarService == null) {
			return Response.status(Status.OK)
					.entity(new Message("系统错误", "服务器内部错误")).build();
		}

		try {
			Result result = myCarService.getDriveRoutes(mcid, beginTime,
					endTime);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result).build();
		}
	}
	
	@POST
	@Path("/diagnose")
	public Response diagnose(@FormParam("codes") String codes) {
		
		return null;
	}
}
