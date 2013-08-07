package com.cop.argus.test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.argus.car.service.DriveDataService;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.common.util.TokenUtil;
import com.cop.argus.test.BasicTest;

/**
 * @author chris.liu
 */
public class DriveDataServiceTest extends BasicTest {

    @Autowired
    private DriveDataService driveDataService;

    @Test
    public void uploadTest() throws Exception {
        List<String> datas = new ArrayList<String>();
        String encoding = "utf-8";
        String fileName = "data/000000000000022.dat";
        try {
            File file = new File(fileName);
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                datas.add(line);
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = driveDataService.uploadDriveData(1, datas);
        Result result = new Result(ResultStatus.RS_OK, TokenUtil.generateToken(
                1, 1), msg);
        System.out.println(DataFormater.format(result));
    }
}
