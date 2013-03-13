package com.chocodev.herokuserver.app.window;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.app.HerokuServerApplication;
import com.vaadin.Application;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Default test window
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Component
public class LoginWindow extends Window{

	@Autowired
    private Application application;
    @Resource
    private transient ApplicationContext context;
    
	ObjectProperty<String> userName=new ObjectProperty<String>("");
	ObjectProperty<String> password=new ObjectProperty<String>("");
	
	private Label status;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginWindow()
	{
		super("Login");
		setModal(true);
		
		TextField txtUser=new TextField(userName);
		TextField txtPassword=new TextField(password);
		status=new Label();
		addComponent(new Label("Login (test)"));
		addComponent(txtUser);
		
		addComponent(new Label("Password (test)"));
		addComponent(txtPassword);
		addComponent(status);
		
		Button doLogin=new Button("login");
		addComponent(doLogin);
		doLogin.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if("test".equals(userName.getValue()) && "test".equals(password.getValue()))
				{
					LoginWindow.this.close();
				}
				else
				{
					status.setCaption("Usuario/Clave incorrecto");
				}
				
			}
		});
		setWidth("300px");
		setHeight("300px");
		center();
		
	}
}
