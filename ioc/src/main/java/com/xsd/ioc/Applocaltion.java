package com.xsd.ioc;

import com.xsd.ioc.core.XmlClassPathSpringIoc;
import com.xsd.ioc.model.IocInfo;
import com.xsd.ioc.service.IocService;

public class Applocaltion
{
	public static void main(String[] args) throws Exception
	{
		String path = "Applocaltion.xml";
		XmlClassPathSpringIoc xmlClassPathSpringIoc = new XmlClassPathSpringIoc(path);

		IocService bean = (IocService) xmlClassPathSpringIoc.getBean("IocService");
		IocInfo iocInfo = bean.getIocInfo();
		iocInfo.getIocInfo();
	}
}
