package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
@Produces("application/json;charset=UTF-8")
public class OilBillAction extends AbstractAction {

	private static final String Tag = "OilBillAction";

	private static OilBillService oilBillService;

	static {
		try {
			oilBillService = (OilBillService) SpringApplicationContext
					.getBean("oilBillService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@POST
	@Path("/add")
	public Response add(@FormParam("uid") Integer uid,
			@FormParam("oil") Double oil,
			@FormParam("unitprice") Double unitPrice,
			@FormParam("addtime") Long addtime) {
		Result result = null;

		if (uid == null || oil == null || unitPrice == null || addtime == null) {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}

		try {
			OilBill bill = new OilBill();
			bill.setUid(uid);
			bill.setOil(oil);
			bill.setUnitprice(unitPrice);
			bill.setAddtime(addtime);
			result = oilBillService.addBill(bill);
			return Response.status(Status.OK).entity(result.toString()).build();
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "add bill error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/get")
	public Response get(@FormParam("uid") Integer uid,
			@FormParam("begin_time") Long beginTime,
			@FormParam("end_time") Long endTime) {
		Result result = null;

		if (uid == null || beginTime == null || endTime == null) {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}

		try {
			result = oilBillService.getBills(uid, beginTime, endTime);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "get bill error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("update")
	public Response update(@FormParam("bid") Integer bid,
			@FormParam("uid") Integer uid, @FormParam("oil") Double oil,
			@FormParam("unitprice") Double unitprice,
			@FormParam("addtime") Long addtime) {
		Result result = null;

		if (bid == null || uid == null || addtime == null
				|| (oil == null && unitprice == null)) {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}

		try {
			OilBill bill = new OilBill();
			bill.setId(bid);
			bill.setOil(oil);
			bill.setUnitprice(unitprice);
			bill.setAddtime(addtime);
			result = oilBillService.updateBill(bill);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "delete bill error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("delete")
	public Response delete(@FormParam("bid") Integer bid) {
		Result result = null;
		try {
			result = oilBillService.deleteBill(bid);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "delete bill error"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}
}
