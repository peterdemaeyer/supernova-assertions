package su.pernova.matchers.contains;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

import java.util.Iterator;

public class ArrayIterator implements Iterator {

	private final Object array;

	private final int length;

	private int index;

	public ArrayIterator(Object array) {
		this.array = array;
		this.length = getLength(array);
	}

	@Override
	public boolean hasNext() {
		return index < length;
	}

	@Override
	public Object next() {
		return get(array, index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("removing elements from an array is not supported");
	}
}
