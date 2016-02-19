package com.tz.service;

import com.tz.bean.Message;
import com.tz.bean.OutputMessage;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hjl on 2016/2/18.
 */
@Service
public class HelloService {

    public OutputMessage sayHi(Message message) {
        return new OutputMessage(message, new Date());
    }
}
