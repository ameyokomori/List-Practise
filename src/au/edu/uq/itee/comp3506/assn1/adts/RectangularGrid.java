package au.edu.uq.itee.comp3506.assn1.adts;

/**
 * A two-dimensional grid to hold items in a positional relationship to each
 * other.
 * 
 * Memory efficiency: O(1).
 * 
 * @author Weiye Zhao 44612975
 *
 * @param <T>
 *            The type of element held in the grid.
 */
public class RectangularGrid<T> implements Grid<T> {

	private T[][] grid;

	/**
	 * Create a RectangularGrid with length and width.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param length
	 *            Max length of the grid.
	 * @param width
	 *            Max width of the grid.
	 */
	@SuppressWarnings("unchecked")
	public RectangularGrid(int length, int width) {
		if (length <= 0 || width <= 0) { // 1
			throw new IllegalArgumentException(); // 1
		} else {
			grid = (T[][]) new Object[length][width]; // 3
		}
		// f(n) = 4
	}

	/**
	 * Place an item at a fixed position in the grid. Overwrites whatever was at the
	 * position.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param item
	 *            Item to be placed in the grid.
	 * @param x
	 *            X Coordinate of the position of the item.
	 * @param y
	 *            Y Coordinate of the position of the item.
	 * @throws IndexOutOfBoundsException
	 *             If x or y coordinates are out of bounds.
	 */
	@Override
	public void place(T item, int x, int y) throws IndexOutOfBoundsException {
		grid[x][y] = item; // 1
		// f(n) = 1
	}

	/**
	 * Return the item at the indicated position.
	 * 
	 * Runtime efficiency: O(1)
	 * 
	 * @param x
	 *            X Coordinate of the position of the item.
	 * @param y
	 *            Y Coordinate of the position of the item.
	 * @return Item at this position or null.
	 * @throws IndexOutOfBoundsException
	 *             If x or y coordinates are out of bounds.
	 */
	@Override
	public T get(int x, int y) throws IndexOutOfBoundsException {
		return grid[x][y]; // 1
		// f(n) = 1
	}
}

/**
 * Justification: In this class I use a two-dimensional array to implement this
 * CDT. When considering the run-time efficiency, it takes a O(n^2)-time to
 * iterate the whole array, but it only takes O(1) to add or get a value with a
 * specific index. When considering the memory space efficiency, the size of a
 * two-dimensional array is fixed, and in this game, all the spaces are taken by
 * a value, so a two-dimensional array is suitable.
 * 
 * As to the vary large grid limitation, a vary large grid means create a
 * two-dimensional array with a large width and length, which will take too much
 * memory. A large grid with few items can release the burden of memory because
 * few place methods are called. But this situation may affect the gaming
 * experience, so I think the best solution is limit the length and width to a
 * small size, 20*20 for example, and increase the number of items in the grid.
 * 
 * Bibliographic: Goodrich, M. and Tamassia, R. (2015). Data structures and
 * algorithms in Java. New York: John Wiley, pp.118-121.
 */
