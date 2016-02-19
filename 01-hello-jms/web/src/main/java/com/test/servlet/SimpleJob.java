/* 
 * Copyright 2005 - 2009 Terracotta, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

package com.test.servlet;

import com.test.message.UserMessage;
import com.test.message.UserMessageImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * User: 胡荆陵
 * Date: 12-12-14
 * 计划任务类
 */
public class SimpleJob implements Job {

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        System.out.println("*************************");
        System.out.println("运行计划任务:" + new Date());
        UserMessage userMessage = new UserMessageImpl();
        userMessage.processUserQueue();
        System.out.println("*************************");
    }

}
