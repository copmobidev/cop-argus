package com.cop.argus.car.service;

import java.util.List;

import com.cop.argus.car.entity.FuelBill;
import com.cop.argus.car.entity.FuelBillServiceException;
import com.cop.argus.car.entity.TimeSpan;
import com.cop.argus.common.entity.Message;

/**
 * @author chris.liu
 */
public interface FuelBillService {

    /**
     * 查询用户某段时间内的加油记录
     *
     * @param uid
     * @param timeSpan
     * @return
     * @throws FuelBillServiceException
     */
    List<FuelBill> get(Integer uid, TimeSpan timeSpan)
            throws FuelBillServiceException;

    /**
     * 添加加油记录
     *
     * @param uid       用户id
     * @param pid       poi id
     * @param fuel      加油量
     * @param unitprice 单价
     * @param fuelType  油品
     * @param addtime   加油时间
     * @return
     * @throws FuelBillServiceException
     */
    FuelBill add(Integer uid, Integer pid, Double fuel, Double unitprice,
                 Integer fuelType, Long addtime) throws FuelBillServiceException;

    FuelBill update(Integer id, Double fuel, Double unitprice,
                    Integer fuelType, Integer pid, Long addtime)
            throws FuelBillServiceException;

    /**
     * 删除账单数据
     *
     * @param id
     * @return
     * @throws FuelBillServiceException
     */
    Message delete(Integer id) throws FuelBillServiceException;

}
