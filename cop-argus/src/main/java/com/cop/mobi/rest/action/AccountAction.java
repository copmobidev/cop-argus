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
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.CarBrand;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.service.MyCarService;
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
	private static MyCarService myCarService;

	static {
		init();
	}

	private static void init() {
		try {
			accountService = (AccountService) SpringApplicationContext
					.getBean("accountService");
			myCarService = (MyCarService) SpringApplicationContext
					.getBean("myCarService");
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag, "account service init error"),
					e);
		}
	}

	@POST
	@Path("/register")
	public Response register(@FormParam("obd") String obd,
			@FormParam("sid") String sid,
			@FormParam("manufacturer") String manufacturer,
			@FormParam("brand") String brand, @FormParam("model") String model,
			@FormParam("engine") String engine,
			@FormParam("timestamp") Long timestamp) {
		Result result = null;
		try {
			CarBrand carBrand = new CarBrand(manufacturer, brand, model, engine);
			result = accountService.register(obd, sid, carBrand, timestamp);
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
			@FormParam("pwd") String pwd,
			@FormParam("manufacturer") String manufacturer,
			@FormParam("brand") String brand, @FormParam("model") String model,
			@FormParam("engine") String engine) {
		Result result = null;
		try {
			Token tk = TokenUtil.parseToken(token);
			UserPo userPo = null;
			MyCarPo myCarPo = null;

			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(email)
					|| StringUtils.isNotBlank(pwd)) {
				userPo = new UserPo();
				userPo.setId(tk.getUid());
				userPo.setEmail(email);
				userPo.setName(name);
				userPo.setPwd(pwd);
			}

			if (StringUtils.isNotEmpty(brand) && StringUtils.isNotBlank(model)
					&& StringUtils.isNotBlank(engine)) {
				CarBrand cb = myCarService.getCarBrandMap().get(
						String.format("%s%s%s", brand, model, engine));
				if (cb != null) {
					myCarPo = new MyCarPo();
					myCarPo.setId(tk.getMcid());
					myCarPo.setBid(cb.getId());
				}
			}

			if (userPo != null || myCarPo != null) {
				result = accountService.update(userPo, myCarPo);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("更新失败",
						"传入数据错误"));
			}
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
