package com.cop.argus.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.junit.Test;

import com.cop.argus.account.entity.User;
import com.cop.argus.car.entity.CarBrand;
import com.cop.argus.car.entity.OBDConfig;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.entity.Token;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.DriveDataUtil;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.common.util.UserAgentUtil;

/**
 * @author chris.liu
 */
public class CommonUtilTest {

    @Test
    public void parseTokenTest() {
        try {
            String tk = "6c25e31d8fda33258dcfcc2046ba5121e0c78784e32032bd06a8bd2b3d96cb72";
            Token token = TokenUtil.parseToken(tk);
            if (token != null) {
                System.out.println(token.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateTokenTest() throws Exception {
        String tk = TokenUtil.generateToken(1, 1);
        System.out.println(tk);
    }

    @Test
    public void parseUATest() {
        String uaAndroid = "mapi 1.0 peseus 1.0.0 motorola MB526 Android 2.3.5";
        UserAgent userAgent = UserAgentUtil.parseUserAgent(uaAndroid);
        if (userAgent != null) {
            System.out.println(userAgent.toString());
        }

        String uaIOS = "MApi 1.0 achilles 1.0.0 IPhone 4S IOS 6.0";
        userAgent = UserAgentUtil.parseUserAgent(uaIOS);
        if (userAgent != null) {
            System.out.println(userAgent.toString());
        }
    }

    @Test
    public void stringTest() {
        String email = null;
        String name = "chris";
        String pwd = "";
        int sex = -1;
        long birth = 13452524653l;
        String values = "";
        if (StringUtils.isNotBlank(email)) {
            values = String.format("%s,email='%s'", values, email);
        }
        if (StringUtils.isNotBlank(name)) {
            values = String.format("%s,name='%s'", values, name);
        }
        if (StringUtils.isNotBlank(pwd)) {
            values = String.format("%s,pwd='%s'", values, pwd);
        }
        if (sex != -1) {
            values = String.format("%s,sex=%s", values, sex);
        }
        if (birth > 0) {
            values = String.format("%s,birth=%s", values, birth);
        }
        if (values.startsWith(",")) {
            values = values.substring(1);
        }
        System.out.println("values:" + values);
    }

    @Test
    public void odbConfigParserTest() {
        String data = "A54B57502D666173742D696E697420204C4757464633413532414230303535343220313031393232363020123014AC0FA00FA012ED10450FC00FB22020202020BE3EB811800000000000000001F80000200B20202120202020202020202020202020202020202020202020202020202042363030303230372020202020202099";
        try {
            OBDConfig obdConfig = DriveDataUtil.parserOBDConfig(data);
            System.out.println(obdConfig.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tripDataParserTest() {
        String encoding = "utf-8";
        String fileName = "data/000000000000043";
        try {
            File file = new File(fileName);
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                DriveDataUtil.parserTripData(line, 1, 1);
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hashCodeTest() {
        String name = "chris";
        String name1 = "范尼阿文热土范尼阿文热土范尼阿文热土范尼阿文热土范尼阿文热土";
        System.out.println(name.hashCode());
        System.out.println(name1.hashCode());

        String te = null;
        try {
            JSONObject jo = new JSONObject();
            jo.put("te", te);
            System.out.println(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void annotationObjectTest() {
        Result result = new Result(ResultStatus.RS_OK, null,
                Message.MSG_SERVER_INNER_ERROR);

        System.out.println(DataFormater.format(result));

        CarBrand brand = new CarBrand(1, "大众", "奥迪", "A4", 2.4, "香槟金", 1.37);
        User user = new User(1, "10192260", brand, "chrisliu@gmail.com",
                "chris.liu", 1, 41562423563l, "dfa423tsadfafds.png", 41, 1,
                132645645124453l);
        try {
            result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
                    user.getId(), 1), user);
            System.out.println(DataFormater.format(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> pairs = new HashMap<String, String>();
        pairs.put("name", "chris");
        pairs.put("age", "10");
        System.out.println(DataFormater.format(pairs));
    }

    @Test
    public void intArrayTest() {
        List<Integer> battery = new ArrayList<Integer>();
        battery.add(1);
        battery.add(2);
        battery.add(3);
        battery.add(5);
        System.out.println(DataFormater.format(battery));
    }

    @Test
    public void test() {
        Date date = new Date(1374440758000l);
        SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
        try {
            System.out.println(sdf.format(date));
        } catch (Exception e) {

        }

    }
}
