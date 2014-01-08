package crawler;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * TODO(alok): Comments.
 */
public class Crawler {

  private final UrlFetcher fetcher;
  private final Cache<URL, String> cache;
  private final ExecutorService executor;

  public Crawler(UrlFetcher fetcher, Cache<URL, String> cache, ExecutorService executor) {
    this.fetcher = fetcher;
    this.cache = cache;
    this.executor = executor;
  }

  // TODO(alok): If we fail to fetch url then we return 'null' as the value. Use a better way.
  public Map<URL, String> fetchAll(List<URL> urls) {
    Map<URL, String> result = new HashMap<>();
    if (urls == null || urls.isEmpty()) {
      return result;
    }

    List<Future<?>> futures = new Vector<>();
    for (URL url : urls) {
      futures.add(executor.submit(new Fetcher(url, result, this)));
    }

    // Jut wait for all tasks to finish.
    for (Future<?> f : futures) {
      try {
        f.get();
      } catch (InterruptedException | ExecutionException e) {
        // TODO(alok): Handle this and test this.
      }
    }

    return result;
  }

  private class Fetcher implements Runnable {

    private final URL url;
    private final Map<URL, String> result;
    private final Object lock;

    public Fetcher(URL url, Map<URL, String> result, Object lock) {
      this.url = url;
      this.result = result;
      this.lock = lock;
    }

    private String getContent() {
      try {
        return cache.get(url);
      } catch (KeyNotFoundException e) {
        // Entry not found in the cache. Use fetcher to fetch content.
      }
      String content;
      try {
        content = fetcher.fetch(url);
      } catch (IOException e) {
        // TODO(alok): We should let caller know what the error was.
        return null;
      }
      // Only store in cache if we got result without any errors.
      cache.put(url, content);
      return content;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      String content = getContent();
      synchronized (this.lock) {
        result.put(url,  content);
      }
    }
  }

}
