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

public class StockLogicTest {

	private StockDao dao = null;
	private StockLogic stockLogic = null;
	
	@Before
	public void setup() {
		dao = mock(StockDao.class);
		stockLogic = new StockLogic(dao);
	}
	
	
	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {		
		stockLogic.add(1, 5);
		
		verify(dao, times(1)).insert(1, 5);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfPriceIsZero() throws ClassNotFoundException, SQLException {
		stockLogic.add(1, 0);
	}

	@Test
	public void testSelectItemsMethod() throws ClassNotFoundException, SQLException {		
		List items = stockLogic.selectItems();
		int cnt = items.size();
		assertTrue(cnt>0);
		
		List<Stock> stocks = new ArrayList();
		if (stocks.size() > 0) {
//			Stock first = stocks.get(0);
//			assertEquals(first.getName(),"きゅうり");
//			assertEquals(first.getPrice(),100);
//			assertEquals(first.getPrice(),200);
		}	//verify(dao, times(1)).select());
	}

}
