package au.edu.uq.itee.comp3506.assn1.adts;

/**
 * An ordered sequential list of items. Maintains a 'cursor' that is a reference
 * to an element in the list, providing a mechanism to iterate over the list.
 * The cursor is the position at which non-fixed-positioning operations on the
 * list occur (e.g. insert, remove, getNext, getPrevious).
 * 
 * Memory efficiency: O(n).
 * 
 * @author Wayne
 *
 * @param <T>
 *            The type of element held in the list.
 */
public class LinkedList<T> implements GameList<T> {
	private Node<T> head;
	private Node<T> tail;
	private Node<T> cursor;
	private int size = 0;

	@SuppressWarnings("hiding")
	private class Node<T> {
		T item;
		Node<T> next;
		Node<T> previous;

		/**
		 * Create a Node with three initial values.
		 * 
		 * Runtime efficiency: O(1)
		 * 
		 * @param item
		 *            The item to be added to the list.
		 * @param next
		 *            The next node.
		 * @param previous
		 *            The previous node.
		 */
		public Node(T item, Node<T> next, Node<T> previous) {
			this.item = item; // 1
			this.next = next; // 1
			this.previous = previous; // 1
			// f(n) = 3
		}
	}

	/**
	 * Create a LinkedList with contents set to null.
	 * 
	 * Runtime efficiency: O(1)
	 */
	public LinkedList() {
		head = null; // 1
		tail = null; // 1
		cursor = null; // 1
		// f(n) = 1
	}

	/**
	 * Add an item to the end of the list. The cursor will refer to the newly added
	 * item. If the list is empty {@code item} becomes the first and last item in
	 * the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param item
	 *            The item to be added to the list.
	 */
	@Override
	public void addToEnd(T item) {
		Node<T> node = new Node<T>(item, null, null); // 3
		if (isEmpty()) { // 3
			head = node; // 1
			tail = node; // 1
			cursor = node; // 1
		} else {
			node.previous = tail; // 1
			tail.next = node; // 1
			tail = node; // 1
			cursor = node; // 1
		}
		size++; // 1
		// f(n) = 11
	}

	/**
	 * Insert an item in front of the current cursor position in the list. The
	 * cursor will refer to the newly inserted item. If the list is empty
	 * {@code item} becomes the first and last item in the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param item
	 *            The item to be inserted into the list.
	 */
	@Override
	public void insert(T item) {
		Node<T> node = new Node<T>(item, null, null); // 3
		if (isEmpty()) { // 3
			head = node; // 1
			tail = node; // 1
			cursor = node; // 1
		} else if (cursor.equals(head)) { // 1
			node.next = cursor; // 1
			cursor.previous = node; // 1
			head = node; // 1
			cursor = node; // 1
		} else {
			node.previous = cursor.previous; // 1
			node.next = cursor; // 1
			cursor.previous = node; // 1
			node.previous.next = node; // 1
			cursor = node; // 1
		}
		size++; // 1
		// f(n) = 13
	}

	/**
	 * Removes the item, at the current cursor position, from the list. Ensures that
	 * the previous item is correctly connected to the next item. After the removal
	 * the cursor will refer to the next item in the list. If the removed item was
	 * the last element in the list, then the cursor will refer to the previous
	 * element, which is now the last item in the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If an attempt is made to remove an element from an empty list.
	 */
	@Override
	public void remove() throws IndexOutOfBoundsException {
		if (size <= 0) { // 1
			throw new IndexOutOfBoundsException(); // 1
		} else if (size == 1) { // 1
			head = null; // 1
			tail = null; // 1
			cursor = null; // 1
		} else if (cursor.equals(head)) { // 1
			head = cursor.next; // 1
			head.previous = null; // 1
			cursor = head; // 1
		} else if (cursor.equals(tail)) { // 1
			tail = cursor.previous; // 1
			tail.next = null; // 1
			cursor = tail; // 1
		} else {
			cursor.previous.next = cursor.next; // 1
			cursor.next.previous = cursor.previous; // 1
			cursor = cursor.next; // 1
		}
		size--; // 1
		// f(n) = 8
	}

	/**
	 * Move the internal cursor to the first element in the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The item at the first position in the list; null if the list is
	 *         empty.
	 */
	@Override
	public T getFirst() {
		if (isEmpty()) { // 3
			return null; // 1
		} else {
			cursor = head; // 1
			return cursor.item; // 1
		}
		// f(n) = 5
	}

	/**
	 * Move the internal cursor to the next element in sequential order in the list.
	 * If the cursor is at the end of the list it remains at that position, and
	 * returns the item at that position.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The item at the new cursor position; null if the list is empty.
	 */
	@Override
	public T getNext() {
		if (isLast()) { // 5
			return cursor.item; // 1
		} else if (isEmpty()) { // 3
			return null; // 1
		} else {
			cursor = cursor.next; // 1
			return cursor.item; // 1
		}
		// f(n) = 10
	}

	/**
	 * Move the internal cursor to the last element in the list.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The item at the last position in the list; null if the list is empty.
	 */
	@Override
	public T getLast() {
		if (isEmpty()) { // 3
			return null; // 1
		} else {
			cursor = tail; // 1
			return cursor.item; // 1
		}
		// f(n) = 5
	}

	/**
	 * Move the internal cursor to the previous element in sequential order in the
	 * list. If the cursor is at the beginning of the list it remains at that
	 * position, and returns the item at that position.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return The item at the new cursor position; null if the list is empty.
	 */
	@Override
	public T getPrevious() {
		if (cursor.equals(head)) { // 1
			return cursor.item; // 1
		} else if (isEmpty()) { // 3
			return null; // 1
		} else {
			cursor = cursor.previous; // 1
			return cursor.item; // 1
		}
		// f(n) = 6
	}

	/**
	 * Finds an item in the list, moving the cursor to the item's position in the
	 * list. Starts searching from the beginning of the list, and stops when it
	 * finds the first instance of the item in the list. If the item is not found
	 * the cursor remains at the end of the list.
	 * 
	 * Runtime efficiency: O(n)
	 * 
	 * @param item
	 *            The item to be found.
	 * @return true if the item has been found in the list; false otherwise.
	 */
	@Override
	public boolean find(T item) {
		if (isEmpty()) { // 3
			return false; // 1
		} else {
			cursor = head; // 1
			for (int i = 0; i < size; i++) { // 2n + 2
				if (cursor.item.equals(item)) { // n + 1
					return true; // n + 1
				}
				if (cursor.equals(tail)) { // n + 1
					return false; // n + 1
				} else {
					cursor = cursor.next; // n + 1
				}
			}
			return false; // 1
		}
		// f(n) = 6n + 11
	}

	/**
	 * Indicates if the list is empty or not.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return true if the list is empty (has no elements); false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) { // 1
			return true; // 1
		} else {
			return false; // 1
		}
		// f(n) = 3
	}

	/**
	 * Indicates if the cursor is at the last element in the list. Will return false
	 * if the list is empty.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @return true if the cursor position is the last element in the list; false
	 *         otherwise.
	 */
	@Override
	public boolean isLast() {
		if (isEmpty()) { // 3
			return false; // 1
		} else if (cursor.equals(tail)) { // 1
			return true; // 1
		} else {
			return false; // 1
		}
		// f(n) = 5
	}
}

/**
 * Justification: In this class I use a doubly linked list to implement this
 * CDT. When considering the run-time efficiency, this list allows many
 * O(1)-time update operations, and it can easily access both the previous and
 * next element of one node. It also allows to insert an element as a head or a
 * tail. When considering the memory space efficiency, the size of a list can
 * grow and shrink as needed. A list don't need to allocate all the space when
 * created, and when deleting an item, the JVM can reclaim the memory.
 * 
 * Bibliographic: Goodrich, M. and Tamassia, R. (2015). Data structures and
 * algorithms in Java. New York: John Wiley, pp.132-137.
 */
