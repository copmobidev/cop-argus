package com.cop.argus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cop.argus.car.service.FuelBillService;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.Token;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.controller.core.BasicController;

/**
 * 加油记录API接口控制类
 * 
 * @author chris.liu
 * 
 */
@Controller
public class FuelBillController extends BasicController {

	@Autowired
	private FuelBillService fuelBillService;

	@RequestMapping(value = "/fuelbill/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> feedback(@RequestHeader HttpHeaders headers,
			@RequestParam("token") String token,
			@RequestParam("fuel") Double fuel,
			@RequestParam("unitprice") Double unitprice,
			@RequestParam("fuelType") Integer fuelType) {
		Result result = null;
		try {
			String ua = headers.get("ua").get(0);
			Token tk = TokenUtil.parseToken(token);

		} catch (Exception e) {
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
		}
		return new ResponseEntity<String>(DataFormater.format(result),
				HttpStatus.OK);
	}

}
