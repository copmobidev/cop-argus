package com.cop.mobi.rest.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.MD5Util;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;

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
			log.error(
					String.format("%s:%s", Tag, "account service init error"),
					e);
		}
	}

	@POST
	@Path("/register")
	public Response register(@FormParam("obd") String obd,
			@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("pwd") String pwd, @FormParam("sex") Integer sex,
			@FormParam("vin") String vin, @FormParam("price") Double price,
			@FormParam("buy_date") Long buyDate) {
		Result result = null;
		try {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPwd(pwd);
			user.setSex(sex);
			MyCarPo myCarPo = new MyCarPo();
			myCarPo.setVin(vin);
			myCarPo.setPrice(price);
			myCarPo.setBuyDate(buyDate);

			result = accountService.register(user, myCarPo);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "register exception"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/login")
	public Response login(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pwd") String pwd) {
		Result result = null;
		try {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPwd(pwd);
			result = accountService.login(user);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "login exception"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/uploadprofile")
	@Consumes("multipart/form-data")
	public Response uploadProfile(MultipartFormDataInput input) {
		Result result = null;
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		InputPart ipToken = uploadForm.get("token").get(0);
		InputPart ipProfile = uploadForm.get("profile").get(0);
		try {
			Token token = TokenUtil.parseToken(ipToken.getBodyAsString());
			// convert the uploaded file to inputstream
			InputStream inputStream = ipProfile
					.getBody(InputStream.class, null);
			byte[] data = IOUtils.toByteArray(inputStream);
			String filename = String.format("%s.png", MD5Util.digest(data));
			// constructs upload file path
			result = accountService.uploadProfile(token.getUid(), filename,
					data);
		} catch (IOException e) {
			log.error(String.format("%s:%s", Tag, "uploadprofile exception"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/freeze")
	public Response freeze() {
		return null;
	}

}
