package au.edu.uq.itee.comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;


/* Implement your additional JUnit tests for FixedSizeBag in this test class. */ 
public class MyFixedSizeBagTest {
	private FixedSizeBag<GameObject> bag;
	
	@Before
	public void setupFixedSizeBag() {
		bag = new FixedSizeBag<GameObject>(3);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void invalidConstruction(){
		new FixedSizeBag<GameObject>(0);
	}

	@Test
	public void validRemoveTest() {
		GameObject item1ToAdd = new GameObject("Item 1 to be Added");
		GameObject item2ToAdd = new GameObject("Item 2 to be Added");
		bag.add(item1ToAdd);
		bag.add(item2ToAdd);
		assertThat("Bag size is not 2 after adding 2 items.", bag.size(), is(equalTo(2)));
		assertThat("Item2 should be removed.", bag.remove(item2ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 1 after removing 1 items.", bag.size(), is(equalTo(1)));
		assertThat("Item1 should be removed.", bag.remove(item1ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 0 after removing 2 items", bag.size(), is(equalTo(0)));
		assertThat("Item2 has been removed.", bag.remove(item2ToAdd), is(equalTo(false)));
		assertThat("Bag size is not 0 after removing noting.", bag.size(), is(equalTo(0)));
	}
	
	@Test
	public void addAndRemoveTest() {
		GameObject item1ToAdd = new GameObject("Item 1 to be Added");
		GameObject item2ToAdd = new GameObject("Item 2 to be Added");
		bag.add(item1ToAdd);
		bag.add(item2ToAdd);
		assertThat("Bag size is not 2 after adding 2 items.", bag.size(), is(equalTo(2)));
		assertThat("Item1 should be removed.", bag.remove(item1ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 1 after removing 1 items.", bag.size(), is(equalTo(1)));
		assertThat("Item2 should be removed.", bag.remove(item2ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 0 after removing 1 items.", bag.size(), is(equalTo(0)));
		bag.add(item1ToAdd);
		bag.add(item2ToAdd);
		assertThat("Bag size is not 2 after adding 2 items.", bag.size(), is(equalTo(2)));
		assertThat("Item1 should be removed.", bag.remove(item1ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 1 after removing 1 items.", bag.size(), is(equalTo(1)));
		assertThat("Item2 should be removed.", bag.remove(item2ToAdd), is(equalTo(true)));
		assertThat("Bag size is not 0 after removing 1 items.", bag.size(), is(equalTo(0)));
		assertThat("Cursor points to nothing in a empty bag", bag.isLast(), is(equalTo(false)));
	}
	
	@Test
	public void invalidRemoveTest() {
		GameObject itemToAdd = new GameObject("Item to be Added");
		assertThat("Remove nothing from an empty bag.", bag.remove(itemToAdd), is(equalTo(false)));		
		bag.add(itemToAdd);
		assertThat("Bag size is not 1 after adding 1 items.", bag.size(), is(equalTo(1)));
		assertThat("Item should be removed.", bag.remove(itemToAdd), is(equalTo(true)));
		assertThat("Bag size is not 0 after removing 1 items.", bag.size(), is(equalTo(0)));
	}
	
	@Test
	public void newBagIsEmpty() {
		assertThat("A newly created bag does not have a size of 0.", bag.size(), is(equalTo(0)));
	}
}
