package au.edu.uq.itee.comp3506.assn1.adts;

/**
 * A simple collection that holds items and provides access to each item. There
 * is an implied ordering of elements to allow iteration through the items in
 * the bag, but there is no guarantee that the order in which items are added
 * will reflect the actual order of how they are stored, or accessed during
 * iteration through the collection.
 * 
 * <p>
 * The bag maintains a 'cursor' that is a reference to an element in the bag,
 * providing a mechanism to iterate over the collection via {@code firstItem}
 * and {@code nextItem}. <blockquote>
 * 
 * <pre>
 * <code>
 * {@code if (bag.size() > 0)} {
 *      item = bag.firstItem();
 *      while (!bag.isLast()) {
 *          // Make use of item.
 *          item = bag.nextItem();
 *      }
 *  }
 * </blockquote></code>
 * </pre>
 * </p>
 * 
 * Memory efficiency: O(n).
 * 
 * @author Wayne
 *
 * @param <T>
 *            The type of element held in the bag.
 */
public class FixedSizeBag<T> implements RemovableBag<T> {
	private Node<T> first;
	private Node<T> cursor;
	private Node<T> temp;
	private int size;
	private int maxSize;

	private static class Node<T> {
		private T item;
		private Node<T> next;

		/**
		 * Create a LinkedList with contents set to null.
		 * 
		 * Runtime efficiency: O(1)
		 */
		public Node() {
			item = null; // 1
			next = null; // 1
			// f(n) = 2
		}
	}

	/**
	 * Create a FixedSizeBag with contents set to null.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param size
	 *            Maximum number of items that can be contained in the bag.
	 */
	public FixedSizeBag(int size) {
		if (size > 0) { // 1
			first = null; // 1
			cursor = null; // 1
			this.size = 0; // 1
			maxSize = size; // 1
		} else {
			throw new IllegalArgumentException(); // 1
		}
		// f(n) = 5
	}

	/**
	 * Add an item to the bag.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param item
	 *            The item to be added.
	 * @return true if item is added to the bag; false if can't be added.
	 */
	@Override
	public boolean add(T item) {
		if (size < maxSize) { // 1
			Node<T> oldfirst = first; // 1
			first = new Node<T>(); // 2
			first.item = item; // 1
			first.next = oldfirst; // 1
			cursor = first; // 1
			size++; // 1
			return true; // 1
		} else {
			return false; // 1
		}
		// f(n) = 9
	}

	/**
	 * Remove the item from the bag. Searches from the head and removes the first
	 * occurrence of {@code item} found in the bag.
	 * 
	 * Runtime efficiency: O(n)
	 * 
	 * @param item
	 *            The item to be removed.
	 * @return true if item is removed from the bag; false if item was not in bag.
	 */
	@Override
	public boolean remove(T item) {
		if (size != 0) { // 1
			cursor = first; // 1
			for (int i = 0; i < maxSize; i++) { // 2n + 2
				temp = cursor; // n + 1
				if (temp.item.equals(item)) { // n + 1
					cursor.next = temp.next; // n + 1
					temp = null; // n + 1
					size--; // n + 1
					return true; // 1
				} else {
					cursor = cursor.next; // n + 1
				}
			}
		}
		return false; // 1
		// f(n) = 7n + 10
	}

	/**
	 * Set the internal cursor to refer to the first item in the bag.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The first item or null if bag is empty.
	 */
	@Override
	public T firstItem() {
		if (size == 0) { // 1
			return null; // 1
		} else {
			cursor = first; // 1
			return cursor.item; // 1
		}
		// f(n) = 3
	}

	/**
	 * Move the internal cursor to the next item in the bag. If the internal cursor
	 * refers to the last item, do not move the cursor and return {@code null}.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The next item or null if there is no next item.
	 */
	@Override
	public T nextItem() {
		if (size == 0) { // 1
			return null; // 1
		} else if (cursor.next == null) { // 1
			return null; // 1
		} else {
			cursor = cursor.next; // 1
			return cursor.item; // 1
		}
		// f(n) = 4
	}

	/**
	 * Indicates if the cursor is at the last element in the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return true if the internal cursor is at the last item in the bag; false
	 *         otherwise.
	 */
	@Override
	public boolean isLast() {
		if (cursor.next == null) { // 1
			return true; // 1
		} else {
			return false; // 1
		}
		// f(n) = 2
	}

	/**
	 * Get the number of items in the bag.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The number of items in the bag.
	 */
	@Override
	public int size() {
		return size; // 1
	}
	// f(n) = 1
}

/**
 * Justification: In this class I use a singly linked list to implement this
 * CDT. When considering the run-time efficiency, compare to an array, an new
 * item can be added in the front of the list rather than iterate all the array
 * to find a empty room. When considering the memory space efficiency, the size
 * of a list can grow and shrink as needed. A list don't need to allocate all
 * the space when created, and when deleting an item, the JVM can recycle the
 * memory. Although a list usually cost an O(n) memory efficiency, but in this
 * game, the max length of the list is limited, which means it may cost less
 * space than an array.
 * 
 * Bibliographic: Goodrich, M. and Tamassia, R. (2015). Data structures and
 * algorithms in Java. New York: John Wiley, pp.122-127.
 */
