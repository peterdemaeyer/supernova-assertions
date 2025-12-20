package su.pernova.assertions;

/**
 * A closeable that does not declare any checked exception.
 *
 * @since 2.0.0
 */
public interface Closeable extends AutoCloseable {

	/**
	 * Closes without declaring any checked exception.
	 */
	@Override
	void close();
}
