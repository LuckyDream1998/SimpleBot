import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Util {

  public static String getCurrency(String string){
    String currency = "";
    try {
      Document html = Jsoup.connect("http://kurstenge.kz").get();
      Elements rows = html.select("tr");

      for (int i = 1; i < rows.size(); i++) {
        Element row = rows.get(i);
        Elements cols = row.select("td");

        if (cols.get(1).text().equalsIgnoreCase(CurrencyEnum.getCurrency(string))) {
          currency = cols.get(3).text();
        }
      }
    }catch (IOException e) {
      e.printStackTrace();
    }
    return currency;
  }
}
