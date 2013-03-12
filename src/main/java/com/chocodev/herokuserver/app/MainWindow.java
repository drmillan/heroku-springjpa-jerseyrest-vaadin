package com.chocodev.herokuserver.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.app.window.LoginWindow;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
*
* @author peholmst
*/
@Component
public class MainWindow extends Window {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Application application;
    @Resource
    private transient ApplicationContext context;
    @Resource
    private transient ApplicationEventPublisher eventPublisher;
    
    protected static final Logger logger = Logger.getLogger(MainWindow.class.getName());
    protected static final Level LOGLEVEL = Level.INFO;
    
    public MainWindow() {
        super("Main window");
        getWindow().addWindow(new LoginWindow());
       }
}