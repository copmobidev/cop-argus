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
import org.springframework.web.multipart.MultipartFile;

import com.cop.argus.account.entity.AccountServiceException;
import com.cop.argus.account.entity.User;
import com.cop.argus.account.service.AccountService;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.Token;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.common.util.UserAgentUtil;
import com.cop.argus.controller.core.BasicController;

/**
 * 账户相关API接口控制类
 *
 * @author chris.liu
 */
@Controller
public class AccountController extends BasicController {

    @Autowired
    private AccountService accountService;

    /**
     * 用户初始绑定
     *
     * @param obd
     * @return
     */
    @RequestMapping(value = "/account/bound", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> bound(@RequestHeader HttpHeaders headers,
                                        @RequestParam("obd") String obd) {
        Result result = null;
        try {
            String uaStr = headers.get("ua").get(0);
            UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
            if (ua != null) {
                User user = accountService.bound(obd);
                if (user != null) {
                    String token = TokenUtil.generateToken(user.getId(), 0);
                    result = new Result(ResultStatus.RS_OK, token, user);
                } else {
                    throw new AccountServiceException(
                            AccountServiceException.USER_NOT_FOUND,
                            "no user found!");
                }
            } else {
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_PARAM_INVALID);
            }
        } catch (AccountServiceException e) {
            result = AccountServiceException.handleException(e);
        } catch (Exception e) {
            result = new Result(ResultStatus.RS_ERROR, null,
                    Message.MSG_SERVER_INNER_ERROR);
        }
        return new ResponseEntity<String>(DataFormater.format(result),
                HttpStatus.OK);
    }

    /**
     * 重新绑定用户
     *
     * @param obd
     * @param vin
     * @param cid
     * @return
     */
    @RequestMapping(value = "/account/rebound", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> rebound(@RequestHeader HttpHeaders headers,
                                          @RequestParam("obd") String obd,
                                          @RequestParam(value = "vin", required = false) String vin,
                                          @RequestParam(value = "calid", required = false) String calid,
                                          @RequestParam("cid") String cid) {
        Result result = null;
        try {
            String uaStr = headers.get("ua").get(0);
            UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
            if (ua != null) {
                User user = accountService.rebound(obd, vin, calid, cid);
                if (user != null) {
                    String token = TokenUtil.generateToken(user.getId(), 0);
                    result = new Result(ResultStatus.RS_OK, token, user);
                } else {
                    throw new AccountServiceException(
                            AccountServiceException.USER_NOT_FOUND,
                            "no user found!");
                }
            } else {
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_PARAM_INVALID);
            }
        } catch (AccountServiceException e) {
            result = AccountServiceException.handleException(e);
        } catch (Exception e) {
            result = new Result(ResultStatus.RS_ERROR, null,
                    Message.MSG_SERVER_INNER_ERROR);
        }

        return new ResponseEntity<String>(DataFormater.format(result),
                HttpStatus.OK);
    }

    /**
     * 用户修改个人信息
     *
     * @param email
     * @param name
     * @param pwd
     * @param sex
     * @param birth
     * @return
     */
    @RequestMapping(value = "/account/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> update(@RequestHeader HttpHeaders headers,
                                         @RequestParam("token") String token,
                                         @RequestParam(value = "vin", required = false) String vin,
                                         @RequestParam(value = "calid", required = false) String calid,
                                         @RequestParam(value = "cid", required = false) String cid,
                                         @RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "pwd", required = false) String pwd,
                                         @RequestParam(value = "sex", required = false) Integer sex,
                                         @RequestParam(value = "birth", required = false) Long birth) {
        Result result = null;
        try {
            String uaStr = headers.get("ua").get(0);
            UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
            Token tk = TokenUtil.parseToken(token);
            if (TokenUtil.isValid(tk) && ua != null) {
                User user = accountService.update(tk.getUid(), vin, calid, cid,
                        email, name, pwd, sex, birth);
                result = new Result(ResultStatus.RS_OK,
                        TokenUtil.generateToken(user.getId(), 1), user);
            } else {
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_PARAM_INVALID);
            }
        } catch (AccountServiceException e) {
            result = AccountServiceException.handleException(e);
        } catch (Exception e) {
            result = new Result(ResultStatus.RS_ERROR, null,
                    Message.MSG_SERVER_INNER_ERROR);
        }
        return new ResponseEntity<String>(DataFormater.format(result),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/account/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadProfile(
            @RequestHeader HttpHeaders headers,
            @RequestParam("token") String token,
            @RequestParam("imageFile") MultipartFile image) {
        Result result = null;
        try {
            String uaStr = headers.get("ua").get(0);
            UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
            Token tk = TokenUtil.parseToken(token);
            if (TokenUtil.isValid(tk) && ua != null) {
                User user = accountService.uploadProfile(tk.getUid(), image);
                result = new Result(ResultStatus.RS_OK,
                        TokenUtil.generateToken(user.getId(), 1), user);
            } else {
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_PARAM_INVALID);
            }
        } catch (AccountServiceException e) {
            result = AccountServiceException.handleException(e);
        } catch (Exception e) {
            result = new Result(ResultStatus.RS_ERROR, null,
                    Message.MSG_SERVER_INNER_ERROR);
        }
        return new ResponseEntity<String>(DataFormater.format(result),
                HttpStatus.OK);
    }
}
