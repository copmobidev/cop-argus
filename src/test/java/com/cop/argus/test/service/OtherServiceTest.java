package com.cop.argus.test.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.common.util.UserAgentUtil;
import com.cop.argus.other.service.OtherService;
import com.cop.argus.test.BasicTest;

/**
 * @author chris.liu
 */
public class OtherServiceTest extends BasicTest {

    @Autowired
    private OtherService otherService;

    @Test
    public void iosConfigTest() throws Exception {
        String uaStr = "MApi 1.0 achilles 1.0.0 IPhone 4S IOS 6.0";
        UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
        Map<String, Object> config = otherService.getConfig(ua, 1);
        Result result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
                1, 1), config);
        System.out.println(DataFormater.format(result));
    }

    @Test
    public void androidConfigTest() throws Exception {
        String uaStr = "mapi 1.0 peseus 1.0.0 motorola MB526 Android 2.3.5";
        UserAgent ua = UserAgentUtil.parseUserAgent(uaStr);
        Map<String, Object> config = otherService.getConfig(ua, 1);
        Result result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
                1, 1), config);
        System.out.println(DataFormater.format(result));
    }

    @Test
    public void feedbackTest() throws Exception {
        String ua = "mapi 1.0 peseus 1.0.0 motorola MB526 Android 2.3.5";
        String content = "不错,希望越来越好";
        Message msg = otherService.feedback(ua, 1, content);
        Result result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
                1, 1), msg);
        System.out.println(DataFormater.format(result));
    }
}
