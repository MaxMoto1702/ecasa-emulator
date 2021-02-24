package ru.softwarecom.uspn.emulators.ecasa.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import static org.slf4j.LoggerFactory.getLogger;

@SuppressWarnings("CommentedOutCode")
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(WebServicesProperties.class)
@EnableWs
public class WebServicesConfiguration
        extends WsConfigurerAdapter {

    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final WebServicesProperties properties;

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, properties.getPath());
    }

    @Bean(name = "VerifyAuthenticationWSService")
    public Wsdl11Definition verifyAuthenticationWSServiceWsdl11Definition() {
        return new SimpleWsdl11Definition(new ClassPathResource("services/VerifyAuthenticationWSPort.wsdl"));
    }

//    @Bean
//    public BeanPostProcessor processor() {
//        return new MessageDispatcherServletBeanPostProcessor();
//    }
//
//    @SuppressWarnings({"rawtypes", "NullableProblems"})
//    public static class MessageDispatcherServletBeanPostProcessor implements BeanPostProcessor {
//        private static final Logger log = getLogger(MessageDispatcherServletBeanPostProcessor.class);
//
//        @Override
//        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//            if (bean instanceof ServletRegistrationBean) {
//                ServletRegistrationBean servletRegistrationBean = (ServletRegistrationBean) bean;
//                Object servlet = servletRegistrationBean.getServlet();
//                if (servlet instanceof MessageDispatcherServlet) {
//                    MessageDispatcherServlet messageDispatcherServlet = (MessageDispatcherServlet) servlet;
//                    if (!messageDispatcherServlet.isTransformSchemaLocations()) {
//                        log.info("Message dispatcher servlet reconfigure because it was initiate without transform WSDL locations");
//                        messageDispatcherServlet.setTransformWsdlLocations(true);
//                    }
//                }
//            }
//            return bean;
//        }
//    }
}
