package ru.func.machinereference;

import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class GlobalObject {
    private ApplicationContext context;

    static {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("ru.func.machinereference");
        annotationConfigApplicationContext.refresh();

        context = annotationConfigApplicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
