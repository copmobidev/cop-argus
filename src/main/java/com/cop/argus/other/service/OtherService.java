package com.cop.argus.other.service;

import java.util.Map;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.service.common.BasicServiceException;

/**
 * @author chris.liu
 */
public interface OtherService {

    /**
     * 用户反馈
     *
     * @param ua
     * @param uid
     * @param content
     * @return
     */
    Message feedback(String ua, int uid, String content);

    Map<String, Object> getConfig(UserAgent ua, int uid) throws BasicServiceException;
}
