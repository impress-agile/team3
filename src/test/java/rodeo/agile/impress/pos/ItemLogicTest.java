package rodeo.agile.impress.pos;

import java.sql.SQLException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ItemLogicTest {

	private ItemDao dao = null;
	private ItemLogic itemLogic = null;
	
	@Before
	public void setup() {
//		dao = mock(ItemDao.class);
		dao = new ItemDao("/home/impress/workspace/team3/src/test/pos.db");
		itemLogic = new ItemLogic(dao);
	}
	
	
	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {		
//		itemLogic.add("ValidName", 5);
//		
//		verify(dao, times(1)).insert("ValidName", 5);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfPriceIsZero() throws ClassNotFoundException, SQLException {
		itemLogic.add("ValidName", 0);
	}

	@Test
	public void testSelectItemsMethod() throws ClassNotFoundException, SQLException {		
		List items = itemLogic.selectItems();
		int cnt = items.size();
		assertTrue(cnt>0);
		
		//List<Item> items = new ArrayList();
		if (items.size() > 0) {
			Item first = (Item) items.get(0);
			assertEquals(first.getName(),"item1");
			assertEquals(first.getPrice(),100);
			//assertEquals(first.getPrice(),200);
		}	//verify(dao, times(1)).select());
	}

}
