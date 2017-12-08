package au.edu.uq.itee.comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;


/* Implement your additional JUnit tests for RectangularGrid in this test class. */ 
public class MyRectangularGridTest {
	private RectangularGrid<GameObject> grid;
	
	@Before
	public void setupRectangularGrid() {
		grid = new RectangularGrid<GameObject>(3, 4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void invalidConstruction() {
		new RectangularGrid<GameObject>(-1, 1);
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void invalidGridAccess() {
		grid.get(-1, -1);
	}
	
	@Test
	public void itemReplace() {
		GameObject itemToInsert1 = new GameObject("Item1 to Insert");
		GameObject itemToInsert2 = new GameObject("Item2 to Insert");
		
		grid.place(itemToInsert1, 1, 2);
		grid.place(itemToInsert2, 1, 2);
		
		GameObject itemRetrieved = grid.get(1, 2);
		assertThat("Item retrieved does not match item inserted at the same position", 
			    itemToInsert2, is(equalTo(itemRetrieved)));
	}
}
