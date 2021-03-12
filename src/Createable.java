import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

 public  interface Createable {
    default Elements createGoods(String page1) {
        Elements goods=new Elements();
        try {
            Document doc = Jsoup.connect(page1).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").referrer("https://www.google.com/").get();
            Elements goods_p1 = doc.select("section>article");
            Document doc1 = Jsoup.connect(page1 + "&p=2").userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").referrer("https://www.google.com/").get();
            Elements goods_p2 = doc1.select("section>article");
            goods.addAll(goods_p1);
            goods.addAll(goods_p2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goods;
    }
}

