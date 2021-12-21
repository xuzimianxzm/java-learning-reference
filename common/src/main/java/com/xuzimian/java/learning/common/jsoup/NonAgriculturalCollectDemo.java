package com.xuzimian.globaldemo.common.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class NonAgriculturalCollectDemo {



    /***
     * 获取非农数据，从金荣中国 ： https://m.jrjr.com/fn/
     * @throws IOException
     */
    @Test
    public void getNonAgriculturalDataByJrjr() throws IOException {
        Document doc = getDoc("https://m.jrjr.com/fn/");
        Optional<Elements> elementsOptional = Optional.ofNullable(doc.select("div.dataList.feinong ul:nth-child(2) li"));

        if (elementsOptional.isPresent()) {
            int size = elementsOptional.get().size();
            Elements lis = elementsOptional.get();

            SimpleDateFormat sf = new SimpleDateFormat("yy年MM月dd");
            for (int n = 0; n < 20 && n < size; n++) {
                Elements spans = lis.get(n).select("span");


                System.out.println(spans.html());
                String day = spans.get(1).text();

                try {
                    System.out.println(sf.parse(day));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /***
     * 获取非农数据，从非农数据 ：https://www.feinongdata.com/
     * @throws IOException
     */
    @Test
    public void getNonAgriculturalDataByFnsj() throws IOException, ParseException {
        Document doc = getDoc("https://www.feinongdata.com/");
        Optional<Elements> elementsOptional = Optional.ofNullable(doc.select("#datadetail li"));
        SimpleDateFormat sf = new SimpleDateFormat("yy年MM月dd");
        if (elementsOptional.isPresent()) {
            int size = elementsOptional.get().size();
            Elements lis = elementsOptional.get();
            for (int n = 0; n < 20 && n < size; n++) {
                Elements spans = lis.get(n).select("span");

                System.out.println(spans.html());
                System.out.println(sf.parse(spans.get(1).text()));
            }
        }
    }

    /***
     * 获取非农数据，从fx168:https://news.fx168.com/feinong/
     * @throws IOException
     */
    @Test
    public void getNonAgriculturalDataByFx168() throws IOException, ParseException {
        Document doc = getDoc("https://news.fx168.com/feinong/");
        Optional<Elements> elementsOptional = Optional.ofNullable(doc.select("#table_HangQingHuiGu tbody tr:not(.yjl_feinong_huigu_th)"));
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd");
        if (elementsOptional.isPresent()) {
            int size = elementsOptional.get().size();
            Elements lis = elementsOptional.get();
            for (int n = 0; n < 20 && n < size; n++) {
                Elements tds = lis.get(n).select("td");

                System.out.println(tds.html());
                System.out.print(sf.parse(tds.get(0).text()));
            }
        }
    }

    /***
     * 获取非农数据，从财金日历：https://www.cjrl.cn/jiedu/meiguo-773.html
     * @throws IOException
     */
    @Test
    public void getNonAgriculturalDataByCjrl() throws IOException, ParseException {
        Document doc = getDoc("https://www.cjrl.cn/jiedu/meiguo-773.html");
        Optional<Elements> elementsOptional = Optional.ofNullable(doc.select("#datadetail tbody tr:nth-child(n+2)"));
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd,hh:mm");
        if (elementsOptional.isPresent()) {
            int size = elementsOptional.get().size();
            Elements lis = elementsOptional.get();
            for (int n = 0; n < 20 && n < size; n++) {
                Elements tds = lis.get(n).select("td");


                System.out.print(sf.parse(tds.get(0).text()));
                System.out.print(tds.get(5).text());
                System.out.println();
            }
        }
    }


    public Document getDoc(String url) throws IOException {
        return Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(3000).ignoreContentType(true)
                .get();
    }
}
