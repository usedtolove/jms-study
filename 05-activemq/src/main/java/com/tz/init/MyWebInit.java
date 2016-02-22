package com.tz.init;

import com.tz.jms.JmsConfig;
import com.tz.service.AppConfig;
import com.tz.web.controller.WebConfig;
import com.tz.web.socket.SocketConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import java.nio.charset.StandardCharsets;

/**
 * Created by hjl on 2016/2/18.
 */
public class MyWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class< ?>[] {
                AppConfig.class,
//                SocketConfig.class,
                JmsConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class< ?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
        return new Filter[] { characterEncodingFilter };
    }

    /**
     * This can be quite important
     * if you’re running the application on a Tomcat container.
     * It says that asynchronous communication is possible,
     * so that connections do not have to be closed directly.
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("dispatchOptionsRequest", "true");
        registration.setAsyncSupported(true);
    }
}
