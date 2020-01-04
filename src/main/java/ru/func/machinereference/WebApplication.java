package ru.func.machinereference;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.func.machinereference.config.DatabaseConfig;
import ru.func.machinereference.config.MvcConfig;

import java.net.InetSocketAddress;

@Slf4j
public class WebApplication {

    private void startJetty(String host, int port) {
        Log.setLog(new Slf4jLog("Jetty.Logger"));

        Server server = new Server(new InetSocketAddress(host, port));
        server.setHandler(createServletContextHandler(createWebApplicationContext()));
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("Error start server", e);
        }
    }

    private ServletContextHandler createServletContextHandler(WebApplicationContext context) {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        contextHandler.addEventListener(new ContextLoaderListener(context));
        return contextHandler;
    }

    private WebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfig.class);
        applicationContext.register(DatabaseConfig.class);

        return applicationContext;
    }

    public static void main(String[] args) {
        final String host = System.getProperty("host", "127.0.0.1");
        final int port = Integer.parseInt(System.getProperty("port", "8080"));

        log.info("Web app listen: {}:{}", host, port);
        new WebApplication().startJetty(host, port);
    }
}
