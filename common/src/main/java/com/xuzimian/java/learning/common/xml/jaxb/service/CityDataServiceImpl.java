package com.xuzimian.globaldemo.common.xml.jaxb.service;


import com.xuzimian.globaldemo.common.xml.jaxb.util.XmlBuilder;
import com.xuzimian.globaldemo.common.xml.jaxb.vo.City;
import com.xuzimian.globaldemo.common.xml.jaxb.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/**
 * City Data Service.
 * 
 * @since 1.0.0 2018年3月9日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class CityDataServiceImpl implements CityDataService {

	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		
		while ((line = br.readLine()) !=null) {
			buffer.append(line);
		}
		
		br.close();
		
		// XML转为Java对象
		CityList cityList = (CityList)XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
		return cityList.getCityList();
	}

}
