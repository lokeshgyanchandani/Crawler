package crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlFetcherImpl implements UrlFetcher {

  /* (non-Javadoc)
   * @see crawler.UrlFetcher#fetch(java.net.URL)
   */
  @Override
  public String fetch(URL url) throws IOException {
    // TODO(alok): What is this new try syntax?!!
    // http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
    try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
      StringBuilder builder = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        builder.append(inputLine);
      }
      return builder.toString();
    }
  }
}
