package crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) throws MalformedURLException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    try {
      Crawler c = new Crawler(new UrlFetcherImpl(), new DbCache(), executorService);
      List<URL> urls = new Vector<>();
      urls.add(new URL("http://www.google.com"));
      urls.add(new URL("http://www.cnn.com"));
      urls.add(new URL("http://techcrunch.com/2013/08/23/mark-zuckerberg-techcrunch-disrupt-sf-2013/"));
      Map<URL, String> result = c.fetchAll(urls);
      for (Entry<URL, String> entry : result.entrySet()) {
        System.out.println("URL: " + entry.getKey());
        System.out.println("Value: " + entry.getValue().substring(0,  1024));
        System.out.println();
      }
    } finally {
      // TODO(alok): What happens if you don't call this? Why is this in try/finally?
      executorService.shutdownNow();
    }

    // TODO(alok): If I pass URL like then I get NullPointerException. Why?
    // urls.add(new URL("http://http://cnn.com"));
  }
}
