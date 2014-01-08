package crawler;

/**
 * A generic cache interface.
 */
public interface Cache<K, V> {

  /**
   * Returns value associated with given cache.
   */
  V get(K key) throws KeyNotFoundException;

  /**
   * Adds or updates existing value for given key.
   */
  void put(K key, V value);

  // TODO(alok): Explain why we don't provide exists() method and then caller can do
  // if (cache.exists(k)) {
  //   V value = cache.get(k);
  // }

  // TODO(alok): Add other useful methods. Describe how to evict entries from the cache.
}
