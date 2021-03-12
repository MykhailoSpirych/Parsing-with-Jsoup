import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Goods implements Createable{
    public static void main(String[] args) {
        List<String> allGoods = new ArrayList<>();

        Elements dom = new Goods().createGoods("https://allegro.pl/kategoria/dom-i-ogrod?string=sale_deals&bmatch=e2101-d3681-c3682-hou-1-5-0304");
        Elements electronics = new Goods().createGoods("https://allegro.pl/kategoria/elektronika?string=sale_deals&bmatch=cl-e2101-d3681-c3682-ele-1-5-0304");
        Elements moda = new Goods().createGoods("https://allegro.pl/kategoria/moda?string=sale_deals&bmatch=e2101-d3681-c3682-fas-1-5-0304");
        List<String> domList = createGoodsList(dom);
        List<String> electroList = createGoodsList(electronics);
        List<String> modaList = createGoodsList(moda);

        allGoods.add("Dom i ogrod\n");
        allGoods.addAll(domList);
        allGoods.add("Electronika\n");
        allGoods.addAll(electroList);
        allGoods.add("Moda\n");
        allGoods.addAll(modaList);
        try(PrintWriter writer = new PrintWriter(new File("Goods.csv"))) {
            allGoods.forEach(writer::write);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done.You can open the file");
    }

    private static List<String> createGoodsList(Elements elements) {
        String s = null;
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("ID: ").append(i + 1).append(" \t").append("Name: ").append((elements.select("h2>a")).get(i).text()).append(" \t Old Price: ").
                    append((elements.select("div.mp0t_ji.mpof_vs._9c44d_1VS-Y._9c44d_3_DDQ._9c44d_2MDwk>span.mpof_uk.mqu1_ae._9c44d_18KEF.m9qz_yp._9c44d_2BSa0._9c44d_KrRuv")).get(i).text()).
                    append("\tNew Price: ").
                    append((elements.select("div.msa3_z4._9c44d_2K6FN>span")).get(i).text()).append("\t\n");
            s = sb.toString();
        }
        result.add(s);
        return result;

    }

}


