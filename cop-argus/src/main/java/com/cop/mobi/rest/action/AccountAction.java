package com.cop.mobi.rest.action;

import java.io.File;
import java.io.FileOutputStream;
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
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.MD5Util;
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

	private static final String PROFILE_UPLOADED_PATH = "d:/data/resources/profile/";

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
	public Response register(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pwd") String pwd,
			@FormParam("sex") int sex, @FormParam("obd") String obd,
			@FormParam("price") double price, @FormParam("buyDate") long buyDate) {
		Result result = null;
		try {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPwd(pwd);
			user.setSex(sex);
			MyCar myCar = new MyCar();
			myCar.setObd(obd);
			myCar.setPrice(price);
			myCar.setBuyDate(buyDate);

			result = accountService.register(user, myCar);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "register exception"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return Response.status(Status.OK).entity(result).build();
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
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("/uploadprofile")
	@Consumes("multipart/form-data")
	public Response uploadProfile(MultipartFormDataInput input) {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");
		for (InputPart inputPart : inputParts) {
			try {
				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class,
						null);
				byte[] data = IOUtils.toByteArray(inputStream);
				String fileName = PROFILE_UPLOADED_PATH + MD5Util.digest(data) + ".png";
				// constructs upload file path
				writeFile(data, fileName);
			} catch (IOException e) {
				log.error(
						String.format("%s:%s", Tag, "uploadprofile exception"),
						e);
			}
		}

		return Response.status(Status.OK)
				.entity("uploadFile is called, Uploaded file name").build();
	}

	@POST
	@Path("/freeze")
	public Response freeze() {
		return null;
	}

	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();

	}

}
