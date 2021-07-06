package reference.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import reference.util.FileDownloadView;

/**
 * Created by Jeongwon.
 * FileName : WebServletConfig
 * Date: 2019-11-13
 * Time: 오전 10:27
 */
@Configuration
public class WebServletConfig {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(1);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public BeanNameViewResolver downloadViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(0);
        return resolver;
    }

    @Bean("fileDownloadView")
    public AbstractView fileDownloadView() {
        return new FileDownloadView();
    }
}
