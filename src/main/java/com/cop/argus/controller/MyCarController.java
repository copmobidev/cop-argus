package com.cop.argus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cop.argus.car.entity.Battery;
import com.cop.argus.car.entity.DriveDataServiceException;
import com.cop.argus.car.entity.TimeSpan;
import com.cop.argus.car.service.DiagnoseService;
import com.cop.argus.car.service.DriveDataService;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.NameValuePair;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.Token;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.common.util.UserAgentUtil;
import com.cop.argus.controller.core.BasicController;

/**
 * 行程数据API接口控制类
 * 
 * @author chris.liu
 * 
 */
@Controller
public class MyCarController extends BasicController {

	@Autowired
	private DriveDataService driveDataService;

	@Autowired
	private DiagnoseService diagnoseService;

	@RequestMapping(value = "/mycar/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> upload(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token,
			@RequestParam("data") String data) {
		Result result = null;
		try {
			String uaStr = headers.get("ua").get(0);
			UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
			Token tk = TokenUtil.parseToken(token);
			if (ua != null && TokenUtil.isValid(tk)) {
				String[] tmp = data.split(";");
				List<String> datas = new ArrayList<String>();
				for (String err : tmp) {
					datas.add(err);
				}
				Message msg = driveDataService.uploadDriveData(tk.getUid(),
						datas);
				result = new Result(ResultStatus.RS_OK,
						TokenUtil.generateToken(tk.getUid(), 1), msg);
			} else {
				result = new Result(ResultStatus.RS_FAIL,
						TokenUtil.generateToken(tk.getUid(), 1),
						Message.MSG_PARAM_INVALID);
			}
		} catch (DriveDataServiceException e) {
			result = DriveDataServiceException.handleException(e);
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}

	// TODO :
	@RequestMapping(value = "/mycar/get", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> getDriveData(
			@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token,
			@RequestParam("span") Integer span,
			@RequestParam("beginTime") Long beginTime,
			@RequestParam("endTime") Long endTime) {
		Result result = null;
		try {
			String uaStr = headers.get("ua").get(0);
			UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
			Token tk = TokenUtil.parseToken(token);
			TimeSpan ts = new TimeSpan(span, beginTime, endTime);
			if (ua != null && TokenUtil.isValid(tk)) {
				driveDataService.getDriveData(tk.getUid(), ts);
			} else {
				result = new Result(ResultStatus.RS_FAIL,
						TokenUtil.generateToken(tk.getUid(), 1),
						Message.MSG_PARAM_INVALID);
			}
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/mycar/diagnose", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> diagnose(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token,
			@RequestParam("errs") String errs) {
		Result result = null;
		try {
			String uaStr = headers.get("ua").get(0);
			UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
			Token tk = TokenUtil.parseToken(token);
			if (ua != null && TokenUtil.isValid(tk)) {
				String[] tmp = errs.split(";");
				List<String> errCodes = new ArrayList<String>();
				for (String err : tmp) {
					errCodes.add(err);
				}
				List<NameValuePair> diagnose = diagnoseService.diagnose(
						tk.getUid(), errCodes);
				result = new Result(ResultStatus.RS_OK,
						TokenUtil.generateToken(tk.getUid(), 1), diagnose);
			} else {
				result = new Result(ResultStatus.RS_FAIL,
						TokenUtil.generateToken(tk.getUid(), 1),
						Message.MSG_PARAM_INVALID);
			}
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/mycar/battery", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> battery(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token) {
		Result result = null;
		try {
			String uaStr = headers.get("ua").get(0);
			UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
			Token tk = TokenUtil.parseToken(token);
			if (ua != null && TokenUtil.isValid(tk)) {
				List<Battery> bats = diagnoseService.battery(tk.getUid());
				result = new Result(ResultStatus.RS_OK,
						TokenUtil.generateToken(tk.getUid(), 1), bats);
			} else {
				result = new Result(ResultStatus.RS_FAIL,
						TokenUtil.generateToken(tk.getUid(), 1),
						Message.MSG_PARAM_INVALID);
			}
		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}
}
