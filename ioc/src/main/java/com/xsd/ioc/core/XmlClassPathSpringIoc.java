package com.xsd.ioc.core;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlClassPathSpringIoc implements ApplocaltionIoc
{
	private HashMap<String, Object> map = new HashMap<String, Object>();

	public XmlClassPathSpringIoc(String xmlUrl) throws Exception
	{
		URL resources = this.getClass().getClassLoader().getResource(xmlUrl);
		URI uri = resources.toURI();
		this.xmlAnalysis(uri);
	}

	// 解析xml文件
	public void xmlAnalysis(URI uri) throws Exception
	{
		SAXReader saxReader = new SAXReader();
		Document read = saxReader.read(new File(uri));
		Element rootElement = read.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = rootElement.elements("bean");
		for (Element element : elements)
		{
			// 获取父类参数
			String id = element.attribute("id").getValue();
			String type = element.attribute("class").getValue();
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(type);
			Object newInstance = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			// 获取子类参数
			@SuppressWarnings("unchecked")
			List<Element> elementsSon = element.elements();
			for (Element elementSon : elementsSon)
			{
				String name = elementSon.attributeValue("name");
				String value = elementSon.attributeValue("value");
				for (int i = 0; i < methods.length; i++)
				{
					String methodname = methods[i].getName();
					if (methodname.startsWith("set"))
					{
						String substring = methodname.substring(3, methodname.length()).toLowerCase();
						if (name != null)
						{
							if (substring.equals(name))
							{
								System.out.println(value);
								methods[i].invoke(newInstance, value);

							}
						} else
						{
							System.out.println(elementSon.attribute("ref").toString());
							methods[i].invoke(newInstance, map.get(elementSon.attributeValue("ref")));
						}
					}
				}

			}
			map.put(id, newInstance);
		}

	}

	public Object getBean(String name)
	{
		System.out.println(map.toString());
		return map.get(name);
	}
}
