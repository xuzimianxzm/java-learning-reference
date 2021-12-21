package com.xuzimian.globaldemo.common.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * @program: global-demo
 * @description: alibaba fastjson demo
 * @author: xzm
 * @create: 2019-07-09 11:09
 **/
public class FastJsonDemo {

    /**
     * parse 空字符串并不会抛出异常
     */
    @Test
    public void testParseForNull() {
        System.out.println(JSONObject.parse(null));
    }

    /**
     * parse 空字符串并不会抛出异常,返回null对象
     */
    @Test
    public void testParseArrayForNull() {
        List<CJRLJsonModel> lists = JSONObject.parseArray(null, CJRLJsonModel.class);
        System.out.println(lists);
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
