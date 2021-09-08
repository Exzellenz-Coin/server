package server.message;

import java.util.LinkedHashSet;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class MessageCache extends LinkedHashSet<UUID> {
	private final int capacity;

	public MessageCache(final int capacity) {
		super();
		this.capacity = capacity;
	}

	@Override
	public synchronized boolean add(UUID uuid) {
		if (contains(uuid)) return false;
		boolean success = super.add(uuid);
		while (size() >= capacity) {
			this.remove(StreamSupport.stream(this.spliterator(), false).findFirst().orElse(null));
		}
		return success;
	}
}
