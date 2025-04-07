package internal.su.pernova.nio.channels;

import java.nio.channels.Channel;

public interface ChannelFactory<C extends Channel> {

	default C openQuietly() {
		try {
			return open();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	C open() throws Exception;
}
