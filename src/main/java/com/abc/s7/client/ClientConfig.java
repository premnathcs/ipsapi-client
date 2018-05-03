package com.abc.s7.client;

import com.scene7.ipsapi.IpsApiPortType;
import com.abc.s7.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${client.ipsapi.address}")
    private String address;

    @Bean(name = "ipsApiServiceProxy")
    public IpsApiPortType ipsApiServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(IpsApiPortType.class);
        jaxWsProxyFactoryBean.setAddress(address);
        jaxWsProxyFactoryBean.getOutInterceptors().add(loggingOutInterceptor());
        jaxWsProxyFactoryBean.getInInterceptors().add(loggingInInterceptor());
        jaxWsProxyFactoryBean.getInFaultInterceptors().add(loggingInInterceptor());
        return (IpsApiPortType) jaxWsProxyFactoryBean.create();
    }

    @Bean
    public LoggingOutInterceptor loggingOutInterceptor() {
        return new LoggingOutInterceptor();
    }

    @Bean
    public SoapHeaderInterceptor soapHeaderInterceptor() {
        return new SoapHeaderInterceptor();
    }
    @Bean
    public LoggingInInterceptor loggingInInterceptor() {
        return new LoggingInInterceptor();
    }
}
