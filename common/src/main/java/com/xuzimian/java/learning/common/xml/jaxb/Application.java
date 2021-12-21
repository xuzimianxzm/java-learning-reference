/**
 * Welcome to https://waylau.com
 */
package com.xuzimian.globaldemo.common.xml.jaxb;

import com.xuzimian.globaldemo.common.xml.jaxb.service.CityDataService;
import com.xuzimian.globaldemo.common.xml.jaxb.vo.City;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Application Main.
 * 
 * @since 1.0.0 2018年3月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class Application {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-jaxb-xml.xml");
		CityDataService cityDataService = context.getBean(CityDataService.class);

		List<City> cityList = cityDataService.listCity();
		for (City city : cityList) {
			System.out.println(city);
		}

	}

}
