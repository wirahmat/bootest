package com.lawencon.bootcamptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lawencon.bootcamptest.view.MainView;

public class App {
	public static void main(String[] args) throws Exception{
		final ApplicationContext context = new ClassPathXmlApplicationContext("main.xml");
		final MainView mainView = context.getBean("mainView", MainView.class);
		mainView.show();
		((ConfigurableApplicationContext)context).close();
	}
}
