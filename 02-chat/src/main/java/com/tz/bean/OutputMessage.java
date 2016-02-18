package com.tz.bean;

import java.util.Date;

/**
 * Created by hjl on 2016/2/18.
 */
public class OutputMessage extends Message {

    private Date time;

    public OutputMessage(Message original, Date time) {
        super(original.getId(), original.getMessage());
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OutputMessage{" +
                "time=" + time +
                "} " + super.toString();
    }
}
