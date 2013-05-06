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

import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.CarBrand;
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
			@FormParam("sid") String sid, @FormParam("addtime") Long addtime) {
		Result result = null;
		try {
			UserPo userPo = new UserPo();
			userPo.setObd(obd);
			userPo.setAddtime(addtime);
			MyCarPo myCarPo = new MyCarPo();
			myCarPo.setSid(sid);
			result = accountService.register(userPo, myCarPo);
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
			UserPo userPo = new UserPo();
			userPo.setEmail(email);
			userPo.setName(name);
			userPo.setPwd(pwd);
			result = accountService.login(userPo);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "login exception"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result.toString()).build();
	}

	@POST
	@Path("/update")
	public Response update(@FormParam("token") String token,
			@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("pwd") String pwd, @FormParam("brand") String brand,
			@FormParam("model") String model, @FormParam("engine") String engine) {
		Result result = null;
		try {
			Token tk = TokenUtil.parseToken(token);
			UserPo userPo = new UserPo();
			userPo.setId(tk.getUid());
			userPo.setEmail(email);
			userPo.setName(name);
			userPo.setPwd(pwd);
			MyCarPo myCarPo = new MyCarPo();
			myCarPo.setId(tk.getMcid());
			CarBrand cb = new CarBrand(brand, model, engine);
			result = accountService.update(userPo, myCarPo, cb);
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
