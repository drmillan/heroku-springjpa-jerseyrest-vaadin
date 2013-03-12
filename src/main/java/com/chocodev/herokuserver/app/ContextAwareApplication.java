package com.chocodev.herokuserver.app;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.wiring.BeanConfigurerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 *
 * @author peholmst
 */
public abstract class ContextAwareApplication extends Application {

    private static final long serialVersionUID = 1L;
    private transient ConfigurableWebApplicationContext parentContext;
    private transient AnnotationConfigApplicationContext vaadinApplicationContext;
    protected static final Logger logger = Logger.getLogger(ContextAwareApplication.class.getName());
    protected static final Level LOGLEVEL = Level.INFO;

    @Override
    public final void init() {
        logger.log(LOGLEVEL, "Initializing application {0}", this);
        configureApplication();
        initApplication();
    }

    /**
     * Initializes the application. The default implementation looks up a bean
     * of type {@link Window} from the Vaadin application specific context and
     * sets the application's main window to that. Subclasses may override.
     */
    protected void initApplication() {
        setMainWindow(new MainWindow());
    }

    @Override
    public void setMainWindow(Window mainWindow) {
        logger.log(LOGLEVEL, "Setting main window of application {0} to {1}", new Object[]{this, mainWindow});
        super.setMainWindow(mainWindow);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        logger.log(LOGLEVEL, "Deserializing application {0}", this);
        in.defaultReadObject();
        configureApplication();
    }

    protected final ApplicationContext getParentContext() {
        return parentContext;
    }

    protected final ApplicationContext getVaadinApplicationSpecificContext() {
        return vaadinApplicationContext;
    }

    protected void configureApplication() {
        parentContext = (ConfigurableWebApplicationContext) ContextLoader.getCurrentWebApplicationContext();

        logger.log(LOGLEVEL, "Configuring application {0}, using parent application context {1}", new Object[]{this, parentContext});
        final BeanConfigurerSupport configurerSupport = new BeanConfigurerSupport();
        configurerSupport.setBeanFactory(parentContext.getBeanFactory());
        configurerSupport.afterPropertiesSet();
        configurerSupport.configureBean(this);
        configurerSupport.destroy();

        logger.log(LOGLEVEL, "Creating Vaadin application specific context for application {0}", this);
        vaadinApplicationContext = new AnnotationConfigApplicationContext();
        vaadinApplicationContext.setParent(parentContext);

        // TODO The package name should be made configurable in a real-world application
        final String packageName = getClass().getPackage().getName();

        vaadinApplicationContext.addBeanFactoryPostProcessor(new BeanFactoryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                beanFactory.registerResolvableDependency(Application.class, ContextAwareApplication.this);
                for (String beanName : beanFactory.getBeanDefinitionNames()) {
                    final BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
                    if (definition.isSingleton() && definition.getBeanClassName().startsWith(packageName) && isSerializable(definition)) {
                        logger.log(LOGLEVEL, "Bean \"{0}\" is serializable, replacing with bean factory", beanName);
                        final String factoryName = beanName + "Factory";
                        beanFactory.registerSingleton(factoryName, new SerializableSingletonFactory(beanName, definition));
                        definition.setFactoryBeanName(factoryName);
                        definition.setFactoryMethodName("get");
                    }
                }
            }
        });
        vaadinApplicationContext.scan(packageName);
        vaadinApplicationContext.refresh();
    }
    
    private Map<String, Serializable> singletons = new HashMap<String, Serializable>();

    public class SerializableSingletonFactory {

        private final BeanDefinition definition;
        private final String beanName;

        public SerializableSingletonFactory(String beanName, BeanDefinition definition) {
            this.definition = definition;
            this.beanName = beanName;
        }

        public Object get() {
            Object bean = singletons.get(beanName);
            if (bean == null) {
                try {
                    logger.log(LOGLEVEL, "Creating new instance of class {0}", definition.getBeanClassName());
                    Class<?> beanClass = parentContext.getClassLoader().loadClass(definition.getBeanClassName());
                    bean = beanClass.newInstance();
                    logger.log(LOGLEVEL, "Created instance is {0}", bean);
                } catch (Exception e) {
                    throw new IllegalStateException("Could not create instance of class " + definition.getBeanClassName(), e);
                }
                singletons.put(beanName, (Serializable) bean);
            }
            logger.log(LOGLEVEL, "Found bean {0} in map under name {1}", new Object[] { bean, beanName });
            
            return bean;
        }

    }

    @Override
    public void close() {
        logger.log(LOGLEVEL, "Closing application {0}", this);
        super.close();
        if (vaadinApplicationContext != null) {
            vaadinApplicationContext.close();
        }
    }

    private boolean isSerializable(BeanDefinition definition) {
        try {
            Class<?> beanClass = parentContext.getClassLoader().loadClass(definition.getBeanClassName());
            return Serializable.class.isAssignableFrom(beanClass);
        } catch (Exception e) {
            throw new IllegalStateException("Could not load class " + definition.getBeanClassName(), e);
        }
    }
}
