package crawler;

/**
 * Exception raised by Cache when key is not found.
 */
class KeyNotFoundException extends Exception {
  private static final long serialVersionUID = 4306623899423323711L;

  public KeyNotFoundException(String message) {
    super(message);
  }
}

