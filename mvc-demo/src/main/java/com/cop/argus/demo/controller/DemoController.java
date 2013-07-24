package com.cop.argus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cop.argus.demo.common.entity.Message;
import com.cop.argus.demo.common.entity.Result;
import com.cop.argus.demo.common.entity.Result.ResultStatus;
import com.cop.argus.demo.entity.User;
import com.cop.argus.demo.service.DemoService;

/**
 * 
 * @author chris.liu
 * 
 */
@Controller
public class DemoController {

	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody
	Result userInfo(@RequestParam("id") int id) {
		Result result = null;
		try {
			result = new Result(ResultStatus.RS_ERROR, "",
					Message.MSG_SERVER_INNER_ERROR);
		} catch (Exception e) {
			result = null;
		}

		return result;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public ResponseEntity<String> helloworld() {
		String msg = "hello, spring 3.0";
		System.out.println(msg);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@RequestMapping("/world")
	public ModelAndView world() {
		String msg = "world, spring 3.0";
		System.out.println(msg);
		return new ModelAndView("hello", "message", msg);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getTest(@RequestParam("id") int id) {
		String result = null;
		try {
			User user = demoService.retrivePerson(id);

			if (user != null) {
				result = user.toString();
			} else {
				result = "no user found";
			}
		} catch (Exception e) {
			result = "server inner error";
		}
		return result;
	}
}
