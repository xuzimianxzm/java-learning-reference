package com.xuzimian.globaldemo.common.jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

public class IpAddressDemo {
    private static String[] provinces = {"北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省",
            "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省",
            "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区", "西藏自治区",
            "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区"};

    @Test
    public void getIpAddressFromWebSiteIP138() {
        try {
            Document doc = Jsoup.connect("http://www.ip138.com/ips1388.asp?ip=1.2.2.255&action=2").get();
            Element element = doc.select(".ul1")
                    .select("li").first();

            String value = Optional.ofNullable(element)
                    .map(d -> StringUtils.replaceOnce(d.html(),
                            "本站数据：", StringUtils.EMPTY).split(" "))
                    .map(d -> d.length > 0 ? d[0] : StringUtils.EMPTY)
                    .orElseGet(() -> StringUtils.EMPTY);

            for (String province : provinces) {
                value = StringUtils.replaceOnce(value, province, StringUtils.EMPTY);
            }
            System.out.println(value);

        } catch (IOException e) {

        }
    }

    @Test
    public void getIpAddressFromWebSiteIPTaoBao(String ip_address) {
        try {
            Document doc = Jsoup.connect("http://ip.taobao.com/service/getIpInfo2.php").data("ip", ip_address).post();

            JSONObject jsonObject = Optional.ofNullable(JSON.parseObject(doc.body().html()))
                    .map(d -> d.getJSONObject("data"))
                    .orElseGet(() -> new JSONObject());

            System.out.println(doc.body().html());

            Optional.ofNullable(jsonObject.get("region")).map(d -> (String) d).ifPresent(d -> System.out.println((d)));
            Optional.ofNullable(jsonObject.get("city")).map(d -> (String) d).ifPresent(d -> System.out.println((d)));
        } catch (IOException e) {

        }

    }

    @Test
    public void getIpAddressFromWebSiteIPBaidu(String ip_address) {
        try {
            Document doc = Jsoup.connect("http://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip_address + "&co=&resource_id=6006")
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(3000).ignoreContentType(true)
                    .get();

            System.out.println(doc.body().html());

            JSONObject jsonObject = Optional.ofNullable(JSON.parseObject(doc.body().html()))
                    .map(d -> d.getJSONArray("data")).map(d -> d.getJSONObject(0))
                    .orElseGet(() -> new JSONObject());

            String value = Optional.ofNullable(jsonObject.get("location")).map(d -> ((String) d).split(" "))
                    .map(d -> d.length > 0 ? d[0] : StringUtils.EMPTY).orElseGet(() -> StringUtils.EMPTY);


            for (String province : provinces) {
                value = StringUtils.replaceOnce(value, province, StringUtils.EMPTY);
            }

            System.out.println(value);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBlankHtml() {
        String html = "<h1><h1/><p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        System.out.println(doc.select("h1").first().html());
    }

    /***
     * 字符串格式ip转换为整数
     * @param ip
     * @return
     */
    public int ipToNum(String ip) {
        String[] ipArray = ip.split("\\.");

        int ZhongIPNumTotal = Ints.tryParse(ipArray[0]) * 256 * 256 * 256
                + Ints.tryParse(ipArray[1]) * 256 * 256 + Ints.tryParse(ipArray[2]) * 256
                + Ints.tryParse(ipArray[3]);

        return ZhongIPNumTotal;
    }

    /***
     * 整数ip转换为字符串格式
     * @param ipaddr
     * @return
     */
    public String getIP(int ipaddr) {
        int y = ipaddr % 256;
        int m = (ipaddr - y) / (256 * 256 * 256);
        int n = (ipaddr - 256 * 256 * 256 * m - y) / (256 * 256);
        int x = (ipaddr - 256 * 256 * 256 * m - 256 * 256 * n - y) / 256;
        return m + "." + n + "." + x + "." + y;
    }

    public long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }
}
