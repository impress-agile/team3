package rodeo.agile.impress.pos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemLogic {
	
	private ItemDao dao;
	
	public ItemLogic(ItemDao dao) {
		this.dao = dao;
	}
		
	public void add(String name, int price) throws ClassNotFoundException, SQLException {
		if (price == 0) {
			throw new RuntimeException("Failed to add item which price is 0.");
		}
		dao.insert(name, price);
	}

	public List selectItems() throws SQLException {
		// TODO Auto-generated method stub
		List items = dao.select();
		//items.add(1);
		
		return items;
	}

}
