package com.rs.game.timer;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Jonathan on 3/24/2017.
 */
public final class Indexer<E> implements Iterable<E> {
	
	private final Object[] arr;
	
	public Indexer(int minIndex, int capacity) {
		arr = new Object[capacity];
	}
	
	public Indexer(int capacity) {
		this(0, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) arr[index];
	}
	
	@SuppressWarnings("unchecked")
	public E set(int index, E element) {
		Object previous = arr[index];
		arr[index] = element;
		return (E) previous;
	}
	
	public boolean contains(E element) {
		if (element == null)
			return false;
		
		for (E e : this) {
			if (element.equals(e))
				return true;
		}
		
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < arr.length; i++)
			arr[i] = null;
	}
	
	public int size() {
		return arr.length;
	}
	
	public int nextIndex() {
		for (int i = 0; i < arr.length; i++) {
			if (null == arr[i])
				return i;
		}
		throw new IllegalStateException("Out of indices!");
	}
	
	@Override
	public Iterator<E> iterator() {
		iterator.pointer = 0;
		return iterator;
	}
	
	private final IndexerIterator iterator = new IndexerIterator();
	
	public boolean isEmpty() {
		return arr.length == 0;
	}
	
	private final class IndexerIterator implements Iterator<E> {
		
		private int pointer;
		
		@Override
		public boolean hasNext() {
			return arr.length > 0 && pointer < arr.length;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public E next() {
			Object o = arr[pointer++];
			if (o == null && hasNext())
				return next();
			return (E) o;
		}
		
		@Override
		public void remove() {
			set(pointer - 1, null);
		}
		
	}
	
	public Stream<E> stream() {
		return StreamSupport.stream(spliterator(), false);
	}
	
}