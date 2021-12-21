package com.xuzimian.globaldemo.common.ipip;

import java.io.IOException;
import java.util.Arrays;

import com.xuzimian.globaldemo.common.ipip.datx.BaseStation;
import com.xuzimian.globaldemo.common.ipip.datx.City;
import com.xuzimian.globaldemo.common.ipip.datx.District;
import com.xuzimian.globaldemo.common.ipip.datx.IPv4FormatException;

public class IpDbDatxDemo {
    public static void main(String[] args) {
         String tempFilePath = IpDbDemo.class.getClassLoader().getResource("ipiptest.ipdb").getPath();
        tempFilePath = System.getProperty( "os.name" ).contains( "indow" ) ? tempFilePath.substring(1) : tempFilePath;
        try {
            City city = new City(tempFilePath); // 城市库

            System.out.println(Arrays.toString(city.find("1.12.0.0")));
            System.out.println(Arrays.toString(city.find("255.255.255.255")));

            District district = new District(tempFilePath);//区县库

            System.out.println(Arrays.toString(district.find("1.12.0.0")));
            System.out.println(Arrays.toString(district.find("223.255.127.250")));

            BaseStation baseStation = new BaseStation(tempFilePath); // 基站库

            System.out.println(Arrays.toString(baseStation.find("8.8.8.8")));
            System.out.println(Arrays.toString(baseStation.find("223.221.121.0")));

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (IPv4FormatException ipex) {
            ipex.printStackTrace();
        }
    }
}