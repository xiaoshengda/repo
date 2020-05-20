package com.xsd.ioc.model;

import java.io.Serializable;

public class IocInfo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3617655042978713282L;
	private String name;

	private String type;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void getIocInfo()
	{
		System.out.println("ioc名字：" + name + "ioc类型:" + type);
	}

}
