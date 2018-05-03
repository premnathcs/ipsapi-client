package com.abc.s7.interceptor;

import com.scene7.ipsapi.xsd.AuthHeader;
import com.scene7.ipsapi.xsd._2013_02_15.ObjectFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.util.List;

@Component
public class SoapHeaderInterceptor extends AbstractSoapInterceptor {

    @Value("${scene7.abc.userid}")
    private String userid;

    @Value("${scene7.abc.password}")
    private String password;

    public SoapHeaderInterceptor() {
        super(Phase.POST_LOGICAL);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();
        AuthHeader authHeader = createAuthHeader();
        try {
            Header header = new Header(new QName("http://www.scene7.com/IpsApi/xsd", "authHeader"),
                    authHeader,
                    new JAXBDataBinding(AuthHeader.class));
            headers.add(header);
            message.put(Header.HEADER_LIST, headers);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private AuthHeader createAuthHeader() {
        AuthHeader header = new AuthHeader();
        header.setUser(this.userid);
        header.setPassword(this.password);
        return header;
    }

}
