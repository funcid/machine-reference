package ru.func.machinereference;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.func.machinereference.config.ApplicationConfig;
import ru.func.machinereference.config.DataSourceConfig;
import ru.func.machinereference.config.MvcConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.nio.charset.StandardCharsets;

public class WebApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfig.class);
        applicationContext.register(ApplicationConfig.class);
        applicationContext.register(DataSourceConfig.class);

        setupDispatcherServlet(servletContext, applicationContext);
        setupCharacterFilter(servletContext);
    }

    private void setupDispatcherServlet(ServletContext servletContext, WebApplicationContext context) {
        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("contextClass", context.getClass().getName());
        servletContext.addListener(new ContextLoaderListener(context));
    }

    private void setupCharacterFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic filterRegistration
                = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

        filterRegistration.setInitParameter("encoding", StandardCharsets.UTF_8.name());
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");
    }
}
