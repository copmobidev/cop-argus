package com.cop.argus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.Token;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.common.util.UserAgentUtil;
import com.cop.argus.controller.core.BasicController;
import com.cop.argus.other.entity.OtherServiceException;
import com.cop.argus.other.service.OtherService;

/**
 * APP所需其他API接口控制类
 * 
 * @author chris.liu
 * 
 */
@Controller
public class OtherController extends BasicController {

	@Autowired
	private OtherService otherService;

	@RequestMapping(value = "/other/config", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> bound(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token) {
		Result result = null;
		try {
			String uaStr = headers.get("ua").get(0);
			UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
			Token tk = TokenUtil.parseToken(token);
			Map<String, Object> config = otherService
					.getConfig(ua, tk.getUid());
			result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
					tk.getUid(), 1), config);
		} catch (OtherServiceException e) {
			result = OtherServiceException.handleException(e);
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/other/feedback", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> feedback(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token,
			@RequestParam("content") String content) {
		Result result = null;
		try {
			String ua = headers.get("ua").get(0);
			Token tk = TokenUtil.parseToken(token);
			Message msg = otherService.feedback(ua, tk.getUid(), content);
			result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
					tk.getUid(), 1), msg);
		} catch (OtherServiceException e) {
			result = OtherServiceException.handleException(e);
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}
}
