package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
@Path("/oilbill")
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
			@FormParam("oil") double oil,
			@FormParam("unitPrice") double unitPrice,
			@FormParam("addtime") long addtime) {
		Result result = null;
		try {
			OilBill bill = new OilBill();
			bill.setUid(uid);
			bill.setOil(oil);
			bill.setUnitPrice(unitPrice);
			bill.setAddtime(addtime);
			result = oilBillService.addBill(bill);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			error(Tag, "add bill error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("/get")
	public Response get(@FormParam("uid") int uid,
			@FormParam("beginTime") long beginTime,
			@FormParam("endTime") long endTime) {
		Result result = null;
		try {
			result = oilBillService.getBills(uid, beginTime, endTime);
		} catch (Exception e) {
			error(Tag, "get bill error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("delete")
	public Response delete(@FormParam("bid") int bid) {
		Result result = null;
		try {
			result = oilBillService.deleteBill(bid);
		} catch (Exception e) {
			error(Tag, "get bill error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result).build();
	}
}
