package com.xuzimian.globaldemo.common.xml.jaxb.service;


import com.xuzimian.globaldemo.common.xml.jaxb.vo.City;

import java.util.List;


/**
 * City Data Service.
 * 
 * @since 1.0.0 2018年3月9日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity() throws Exception;
}
