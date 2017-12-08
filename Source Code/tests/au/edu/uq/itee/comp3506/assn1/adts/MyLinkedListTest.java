package au.edu.uq.itee.comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;

/* Implement your additional JUnit tests for LinkedList in this test class. */
public class MyLinkedListTest {
	private LinkedList<GameObject> list;

	@Before
	public void createEmptyLinkedList() {
		list = new LinkedList<GameObject>();
	}

	@Test
	public void getsTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);
		assertThat("Cursor should at the last(third) item.", list.isLast(), is(equalTo(true)));//
		assertThat("Cursor should move to the second item.", list.getPrevious(), is(equalTo(item2ToAdd)));
		assertThat("Cursor should move to the last(third) item.", list.getLast(), is(equalTo(item3ToAdd)));
		assertThat("Cursor should move to the first item.", list.getFirst(), is(equalTo(item1ToAdd)));
		assertThat("Cursor should move to the second item.", list.getNext(), is(equalTo(item2ToAdd)));
	}

	@Test
	public void multipleItemsTest() {
		GameObject Item1 = new GameObject("Item1");
		GameObject Item2 = new GameObject("Item2");
		GameObject Item3 = new GameObject("Item3");
		GameObject Item4 = new GameObject("Item4");
		GameObject Item5 = new GameObject("Item5");

		list.addToEnd(Item1);
		list.insert(Item2);
		list.insert(Item3);

		assertThat("The next position of current item is not matching to the next item in the list.", list.getNext(),
				is(equalTo(Item2)));
		assertThat("The previous position of current item is not matching to the previous item in the list.",
				list.getPrevious(), is(equalTo(Item3)));
		assertThat("The last item inserted into the list is not the last item in the list.", list.getLast(),
				is(equalTo(Item1)));
		assertThat("The first item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(Item3)));

		list.addToEnd(Item4);
		list.getPrevious();
		list.insert(Item5);
		list.remove();

		assertThat("Removing current item did not result in the cursor move to last in the list.", list.isLast(),
				is(equalTo(false)));
		assertThat("Finding an item did not begining at the first position.", list.find(Item1), is(equalTo(true)));
		assertThat("Finding an item that have been removed should not been found.", list.find(Item5),
				is(equalTo(false)));
	}

	@Test
	public void insertIntoEmptyListTest() {
		GameObject itemToAdd = new GameObject("Item");
		list.insert(itemToAdd);
		assertThat("List should not be empty.", list.isEmpty(), is(equalTo(false)));
		assertThat("Cursor should point to the only item added to the list.", list.isLast(), is(equalTo(true)));
		assertThat("The only item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(itemToAdd)));
		assertThat("The only item added to the list is not the last item in the list.", list.getLast(),
				is(equalTo(itemToAdd)));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeEmptyListTest() {
		list.remove();
	}

	@Test
	public void validFindTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);

		assertThat("Item 1 should in the list.", list.find(item1ToAdd), is(equalTo(true)));
		assertThat("Cursor should move to the first item.", list.isLast(), is(equalTo(false)));
		assertThat("Item 3 should in the list.", list.find(item3ToAdd), is(equalTo(true)));
		assertThat("Cursor should move to the last item.", list.isLast(), is(equalTo(true)));
		assertThat("Item 2 should in the list.", list.find(item2ToAdd), is(equalTo(true)));
	}

	@Test
	public void invalidFindTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		GameObject item4ToAdd = new GameObject("Item 4");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);

		assertThat("Item 4 should not in the list.", list.find(item4ToAdd), is(equalTo(false)));
		assertThat("After iterating, the cursor should move to the last item.", list.isLast(), is(equalTo(true)));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void invalidRemoveTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		list.remove();
		list.addToEnd(item1ToAdd);
		list.remove();
		list.remove();
		list.remove();
		list.remove();
	}

	@Test
	public void removeLastItemTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);
		assertThat("Adding one item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
		list.remove();
		assertThat("Remove the last item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
	}

	@Test
	public void removeFirstItemTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);
		list.getFirst();
		assertThat("Call getFirst resulted in the cursor refering to the head of the list.", list.isLast(),
				is(equalTo(false)));
		list.remove();
		assertThat("The first item is removed, the second item becomes the first.", list.getFirst(),
				is(equalTo(item2ToAdd)));
	}

	@Test
	public void removeBodyItemTest() {
		GameObject item1ToAdd = new GameObject("Item 1");
		GameObject item2ToAdd = new GameObject("Item 2");
		GameObject item3ToAdd = new GameObject("Item 3");
		GameObject item4ToAdd = new GameObject("Item 4");
		list.addToEnd(item1ToAdd);
		list.addToEnd(item2ToAdd);
		list.addToEnd(item3ToAdd);
		list.addToEnd(item4ToAdd);
		list.find(item2ToAdd);
		list.remove();
		assertThat("Cursor should at the second last item.", list.isLast(), is(equalTo(false)));
		list.remove();
		assertThat("Cursor should at the last item..", list.isLast(), is(equalTo(true)));
	}

	@Test
	public void newListIsEmpty() {
		assertThat("A newly created list is not empty.", list.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void addOneItem() {
		GameObject addOneItemToEnd = new GameObject("Item to be added to end");
		list.addToEnd(addOneItemToEnd);
		assertThat("Adding one item resulted in an empty list.", list.isEmpty(), is(equalTo(false)));
		assertThat("Adding one item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
		assertThat("The only item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(addOneItemToEnd)));
		assertThat("The only item added to the list is not the last item in the list.", list.getLast(),
				is(equalTo(addOneItemToEnd)));
		assertThat("The next of the only item added to the list did not result in it being the last item.",
				list.getNext(), is(equalTo(addOneItemToEnd)));
		assertThat("The previous of the only item added to the list did not result in it being the first item.",
				list.getPrevious(), is(equalTo(addOneItemToEnd)));
		list.remove();
		assertThat("Removing the only one item in the list did not result in an empty list.", list.getFirst(),
				is(equalTo(null)));
	}

	@Test
	public void insertOneItem() {
		GameObject itemToInsert = new GameObject("Item to be inserted in the head of list");
		list.insert(itemToInsert);
		assertThat("Inserting one item resulted in an empty list.", list.isEmpty(), is(equalTo(false)));
		assertThat("Inserting one item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
		assertThat("The only item inserted into the list is not the first item in the list.", list.getFirst(),
				is(equalTo(itemToInsert)));
		assertThat("The only item inserted into the list is not the last item in the list.", list.getLast(),
				is(equalTo(itemToInsert)));
		assertThat("The next of the only item inserted into the list did not result in it being the last item.",
				list.getNext(), is(equalTo(itemToInsert)));
		assertThat("The previous of the only item inserted into the list did not result in it being the first item.",
				list.getPrevious(), is(equalTo(itemToInsert)));
		list.remove();
		assertThat("Removing the only one item in the list did not result in an empty list.", list.getLast(),
				is(equalTo(null)));

	}

	@Test
	public void addTwoItems() {
		GameObject addItem1 = new GameObject("Item1");
		GameObject addItem2 = new GameObject("Item2");
		list.addToEnd(addItem1);
		list.addToEnd(addItem2);
		assertThat("Adding multiple items to end of list did not result in the last one being the last item.",
				list.isLast(), is(equalTo(true)));
		assertThat("The first item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(addItem1)));
		assertThat("The last item added to the list is not the last item in the list.", list.getLast(),
				is(equalTo(addItem2)));
		list.remove();
		assertThat("Removing the current item did not resulting in the item being removed.", list.isLast(),
				is(equalTo(true)));
	}

	@Test
	public void insertTwoItems() {
		GameObject insertItem1 = new GameObject("Item1");
		GameObject insertItem2 = new GameObject("Item2");
		list.insert(insertItem1);
		list.insert(insertItem2);
		assertThat(
				"Inserting multiple items into an empty list resulted in the cursor refering to the end of the list.",
				list.isLast(), is(equalTo(false)));
		assertThat("The first item inserted into an empty list is not the last item in the list.", list.getLast(),
				is(equalTo(insertItem1)));
		assertThat("The last item inserted into the list is not the first item in the list.", list.getFirst(),
				is(equalTo(insertItem2)));
		list.remove();
		assertThat("Removing the current item did not resulting in the item being removed.", list.isLast(),
				is(equalTo(true)));

	}
}
