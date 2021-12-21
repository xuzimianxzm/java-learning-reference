package com.xuzimian.globaldemo.common.jsoup;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @program: global-demo
 * @description: 临时演示Jsoup API 的参考示例
 * @author: xzm
 * @create: 2019-07-09 09:58
 **/
public class JsoupDemo {

    @Test
    public void test() {
        String year = String.valueOf(LocalDate.now().getYear());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
        String timeStamp = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        String DATA_URL = "https://cdn-rili.jin10.com/data/{0}/{1}/economics.json?_={2}";
        String url = MessageFormat.format(DATA_URL, year, date, timeStamp);


        try {
            String jsonData = Optional.ofNullable(Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(3000).ignoreContentType(true)
                    .get())
                    .map(d -> d.body())
                    .map(d -> d.html())
                    .orElseGet(() -> StringUtils.EMPTY);

            System.out.println(jsonData);

            List<CJRLJsonModel> lists = Optional.ofNullable(JSONObject.parseArray(jsonData, CJRLJsonModel.class))
            .orElseGet(()-> Lists.newArrayList());

            lists.forEach(d -> {
                System.out.println(d.getCountry() + d.getTime_period() + d.getName() +getUnit(d.getUnit()));
            });


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private String getUnit(String unit) {
        if (null == unit || "%".equals(unit)) {
            return StringUtils.EMPTY;
        }

        return "(" + unit + ")";
    }


    /**
     * FastJSON 不能将json转化为一个非静态内部类类型的model，因为非静态的内部类是不能作为一个单独的类去实例化的
     */
    static class CJRLJsonModel {

        /**
         * id : 131550
         * affect : 1
         * country : 美国
         * actual : 170.86
         * consensus : 170
         * unit : 亿美元
         * revised : 174.6
         * star : 2
         * previous : 174.97
         * name : 消费信贷
         * pub_time : 2019-07-08T19:00:00.000Z
         * indicator_id : 773
         * time_period : 5月
         */

        private int id;
        private int affect;
        private String country;
        private String actual;
        private String consensus;
        private String unit;
        private String revised;
        private int star;
        private String previous;
        private String name;
        private String pub_time;
        private int indicator_id;
        private String time_period;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAffect() {
            return affect;
        }

        public void setAffect(int affect) {
            this.affect = affect;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getActual() {
            return actual;
        }

        public void setActual(String actual) {
            this.actual = actual;
        }

        public String getConsensus() {
            return consensus;
        }

        public void setConsensus(String consensus) {
            this.consensus = consensus;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRevised() {
            return revised;
        }

        public void setRevised(String revised) {
            this.revised = revised;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPub_time() {
            return pub_time;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
        }

        public int getIndicator_id() {
            return indicator_id;
        }

        public void setIndicator_id(int indicator_id) {
            this.indicator_id = indicator_id;
        }

        public String getTime_period() {
            return time_period;
        }

        public void setTime_period(String time_period) {
            this.time_period = time_period;
        }
    }

}
