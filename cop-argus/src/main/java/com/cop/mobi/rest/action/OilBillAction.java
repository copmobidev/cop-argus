package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.OilBill;
import com.cop.mobi.mycar.service.OilBillService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/bill")
public class OilBillAction extends AbstractAction {

	private static final String Tag = "OilBillAction";

	private static OilBillService oilBillService;

	static {
		try {
			oilBillService = (OilBillService) SpringApplicationContext
					.getBean("oilBillService");
		} catch (Exception e) {
			error(Tag, "init error", e);
		}
	}

	@POST
	@Path("/add")
	public Response add(@FormParam("uid") int uid,
			@FormParam("oil") double oil, @FormParam("cost") double cost,
			@FormParam("addtime") long addtime) {
		if (oilBillService == null) {
			return Response.status(Status.OK)
					.entity(new Message("系统错误", "服务器内部错误")).build();
		}
		try {
			OilBill bill = new OilBill();
			bill.setUid(uid);
			bill.setOil(oil);
			bill.setCost(cost);
			bill.setAddtime(addtime);
			Result result = oilBillService.addBill(bill);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			error(Tag, "add bill error", e);
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result).build();
		}
	}

	@POST
	@Path("/get")
	public Response get(@FormParam("uid") int uid,
			@FormParam("beginTime") long beginTime,
			@FormParam("endTime") long endTime) {
		if (oilBillService == null) {
			return Response.status(Status.OK)
					.entity(new Message("系统错误", "服务器内部错误")).build();
		}

		try {
			Result result = oilBillService.getBills(uid, beginTime, endTime);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			error(Tag, "get bill error", e);
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result).build();
		}
	}

	@POST
	@Path("delete")
	public Response delete(@FormParam("bid") int bid) {
		return null;
	}
}
