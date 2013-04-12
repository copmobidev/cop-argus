package com.cop.mobi.rest.action;

import java.net.URLEncoder;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Path("/account")
@Produces("application/json;charset=UTF-8")
public class AccountAction extends AbstractAction {
	private static final String Tag = "AccountAction";

	private static AccountService accountService;

	static {
		try {
			accountService = (AccountService) SpringApplicationContext
					.getBean("accountService");
		} catch (Exception e) {
			error(Tag, "account service init error", e);
		}
	}

	@GET
	@Path("/register")
	public Response register(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pwd") String pwd,
			@FormParam("sex") int sex, @FormParam("obd") String obd,
			@FormParam("price") double price, @FormParam("buyDate") long buyDate) {
		if (accountService == null) {
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPwd(pwd);
		user.setSex(sex);
		MyCar myCar = new MyCar();
		myCar.setObd(obd);
		myCar.setPrice(price);
		myCar.setBuyDate(buyDate);

		try {
			Result result = accountService.register(user, myCar);
			return Response.status(Status.OK)
					.entity(URLEncoder.encode(result.toString(), "UTF-8"))
					.build();
		} catch (Exception e) {
			error(Tag, "register exception", e);
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("/login")
	public Response login(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pwd") String pwd) {
		if (accountService == null) {
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}

		try {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPwd(pwd);
			Result result = accountService.login(user);
			return Response.status(Status.OK)
					.entity(URLEncoder.encode(result.toString(), "UTF-8"))
					.build();
		} catch (Exception e) {
			error(Tag, "login exception", e);
			Result result = new Result(ResultStatus.RS_ERROR, new Message(
					"系统错误", "服务器内部错误"));
			return Response.status(Status.OK).entity(result.toString()).build();
		}
	}

	@POST
	@Path("/freeze")
	public Response freeze() {
		return null;
	}
}
