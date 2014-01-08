package crawler;

import java.net.URL;

// TODO(alok): Implement this class.
// Store content in a file on local machine for now.
// Also think about how to invalidate cached data.
/**
 * A cache to store fetched URL and content. This is used to avoid fetching same URL again.
 *
 * This is a thread safe class.
 */
public class DbCache implements Cache<URL, String> {

  /* (non-Javadoc)
   * @see crawler.Cache#get(java.lang.Object)
   */
  @Override
  public String get(URL key) throws KeyNotFoundException {
    throw new KeyNotFoundException("not implemented yet");
  }

  /* (non-Javadoc)
   * @see crawler.Cache#put(java.lang.Object, java.lang.Object)
   */
  @Override
  public void put(URL key, String value) {
	  // TODO(alok): Add this.
  }
}
