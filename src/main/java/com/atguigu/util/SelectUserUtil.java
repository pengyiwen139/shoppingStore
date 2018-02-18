package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;

public class SelectUserUtil<T> implements FactoryBean<T>{
	private String UserURL;
	private Class<T> t;

	public static <T> T useWSInterface(String UserURL,Class<T> t){
		JaxWsProxyFactoryBean ws = new JaxWsProxyFactoryBean();
		ws.setAddress(UserURL);
		ws.setServiceClass(t);
		T create = (T)ws.create();
		return create;
	}

	public T getObject() throws Exception {
		return useWSInterface(this.UserURL, this.t);
	}

	public Class<?> getObjectType() {
		return this.t;
	}

	public boolean isSingleton() {
		return false;
	}
	

	
	public String getUserURL() {
		return UserURL;
	}

	public void setUserURL(String userURL) {
		UserURL = userURL;
	}

	public Class<T> getT() {
		return t;
	}
	
	public void setT(Class<T> t) {
		this.t = t;
	}
}
