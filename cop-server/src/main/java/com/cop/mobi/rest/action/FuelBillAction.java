package com.cop.mobi.rest.action;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.FuelBill;
import com.cop.mobi.mycar.service.FuelBillService;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/oilbill")
@Produces("application/json;charset=UTF-8")
public class FuelBillAction extends AbstractAction {

	private static final String Tag = "FuelBillAction";

	private static FuelBillService fuelBillService;

	static {
		init();
	}

	/**
	 * 初始化函数，rest action被注入时调用一次
	 */
	protected static void init() {
		try {
			fuelBillService = (FuelBillService) SpringApplicationContext
					.getBean("oilBillService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@POST
	@Path("/add")
	public Response add(@FormParam("token") String token,
			@FormParam("fuel_type") Integer fuel_type,
			@FormParam("charge") Double charge,
			@FormParam("unitprice") Double unitprice,
			@FormParam("lat") Double lat, @FormParam("lng") Double lng,
			@FormParam("addtime") Long addtime) {
		Result result = null;
		Token tk = TokenUtil.parseToken(token);
		if (TokenUtil.isValid(tk)) {
			try {
				FuelBill bill = new FuelBill();
				bill.setUid(tk.getUid());
				bill.setMcid(tk.getMcid());
				bill.setCharge(charge);
				bill.setUnitprice(unitprice);
				bill.setAddtime(addtime);
				result = fuelBillService.addBill(tk, bill);
				return Response.status(Status.OK).entity(result.toString())
						.build();
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag, "add bill error"), e);
				result = new Result(ResultStatus.RS_ERROR,
						SERVER_INNER_ERROR_MSG);
			}
			return Response.status(Status.OK).entity(result.toString()).build();
		} else {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("/get")
	public Response get(@FormParam("token") String token,
			@FormParam("begin_time") Long beginTime,
			@FormParam("end_time") Long endTime) {
		Result result = null;
		Token tk = TokenUtil.parseToken(token);
		if (TokenUtil.isValid(tk)) {
			try {
				result = fuelBillService.getBills(tk, beginTime, endTime);
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag, "get bill error"), e);
				result = new Result(ResultStatus.RS_ERROR,
						SERVER_INNER_ERROR_MSG);
			}
			return Response.status(Status.OK).entity(result.toString()).build();
		} else {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("update")
	public Response update(@FormParam("token") String token,
			@FormParam("bid") Integer bid, @FormParam("oil") Double oil,
			@FormParam("unitprice") Double unitprice,
			@FormParam("addtime") Long addtime) {
		Result result = null;
		Token tk = TokenUtil.parseToken(token);
		if (TokenUtil.isValid(tk)) {
			try {
				FuelBill bill = new FuelBill();
				bill.setId(bid);
				bill.setCharge(oil);
				bill.setUnitprice(unitprice);
				bill.setAddtime(addtime);
				result = fuelBillService.updateBill(tk, bill);
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag, "delete bill error"), e);
				result = new Result(ResultStatus.RS_ERROR,
						SERVER_INNER_ERROR_MSG);
			}
			return Response.status(Status.OK).entity(result.toString()).build();
		} else {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("delete")
	public Response delete(@FormParam("token") String token,
			@FormParam("bid") Integer bid) {
		Result result = null;
		Token tk = TokenUtil.parseToken(token);
		if (TokenUtil.isValid(tk)) {
			try {
				result = fuelBillService.deleteBill(tk, bid);
			} catch (Exception e) {
				log.error(String.format("%s:%s", Tag, "delete bill error"), e);
				result = new Result(ResultStatus.RS_ERROR,
						SERVER_INNER_ERROR_MSG);
			}
			return Response.status(Status.OK).entity(result.toString()).build();
		} else {
			result = new Result(ResultStatus.RS_ERROR, PARAM_ERROR_MSG);
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}
}
