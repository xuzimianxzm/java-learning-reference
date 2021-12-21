package com.xuzimian.globaldemo.common.ipip;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import com.xuzimian.globaldemo.common.ipip.ipdb.BaseStation;
import com.xuzimian.globaldemo.common.ipip.ipdb.City;
import com.xuzimian.globaldemo.common.ipip.ipdb.CityInfo;
import com.xuzimian.globaldemo.common.ipip.ipdb.District;
import com.xuzimian.globaldemo.common.ipip.ipdb.DistrictInfo;
import com.xuzimian.globaldemo.common.ipip.ipdb.IDC;
import com.xuzimian.globaldemo.common.ipip.ipdb.IDCInfo;

public class IpDbDemo {
    static String tempFilePath = IpDbDemo.class.getClassLoader().getResource("ipiptest.ipdb").getPath();

    public static void main(String[] args) throws URISyntaxException {
        tempFilePath = System.getProperty( "os.name" ).contains( "indow" ) ? tempFilePath.substring(1) : tempFilePath;
   
        try {
            City db = new City(tempFilePath);

            System.out.println(Arrays.toString(db.find("1.1.1.1", "CN")));

            CityInfo info = db.findInfo("118.28.1.1", "CN");

            System.out.println(info);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // testBaseStation();
        // testFree();
        // testIDC();
        // testDistrict();
        // testCity();
    }

    public static void testBaseStation() {
        try {

            BaseStation db = new BaseStation(tempFilePath);
            System.out.println(Arrays.toString(db.fields()));

            System.out.println(Arrays.toString(db.find("112.224.4.99", "CN")));

        } catch (Exception e) {

        }
    }

    public static void testFree() {
        try {
            District db = new District(tempFilePath);

            System.out.println(Arrays.toString(db.find("1.12.13.1", "CN")));

            DistrictInfo info = db.findInfo("8.8.8.8", "CN");
            if (info != null) {
                System.out.println(info.getCountryName());
            }

            Map<String, String> m = db.findMap("114.114.114.114", "CN");

            System.out.println(m);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDistrict() {
        try {
            District db = new District(tempFilePath);

            System.out.println(Arrays.toString(db.find("1.12.13.1", "CN")));

            DistrictInfo info = db.findInfo("1.12.13.1", "CN");
            if (info != null) {
                System.out.println(info);
                System.out.println(info.getCountryName());
            }

            Map<String, String> m = db.findMap("1.12.13.1", "CN");

            System.out.println(m);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testIDC() {
        try {
            IDC db = new IDC(tempFilePath);

            System.out.println(Arrays.toString(db.find("1.1.1.1", "CN")));

            IDCInfo info = db.findInfo("8.8.8.8", "CN");

            System.out.println(info.getCountryName());

            Map<String, String> m = db.findMap("114.114.114.114", "CN");

            System.out.println(m);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCityV4() {
        try {
            City db = new City(tempFilePath);

            System.out.println(Arrays.toString(db.find("1.1.1.1", "CN")));

            CityInfo info = db.findInfo("118.28.1.1", "CN");

            System.out.println(info);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCity() {
        try {
            City db = new City(tempFilePath);

            System.out.println(Arrays.toString(db.find("2001:250:200::", "CN")));

            CityInfo info = db.findInfo("2001:250:201::", "CN");

            System.out.println(info);

            Map<String, String> m = db.findMap("2001:250:220::", "CN");

            System.out.println(m);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}