package crawler;

import java.io.IOException;
import java.net.URL;

/**
 * Interface for fetching URLs.
 * All implementations of this should be thread-safe.
 *
 * TODO(alok): Make this more efficient. e.g., returning String by storing all content in memory
 * is not very efficient.
 */
public interface UrlFetcher {

  String fetch(URL url) throws IOException;

}
