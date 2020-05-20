package com.xsd.ioc.service;

import com.xsd.ioc.model.IocInfo;

public class IocService
{
	private IocInfo iocInfo;

	public IocInfo getIocInfo()
	{
		return iocInfo;
	}

	public void setIocInfo(IocInfo iocInfo)
	{
		this.iocInfo = iocInfo;
	}

	public void getIoc()
	{
		iocInfo.getIocInfo();
	}

}
