package rodeo.agile.impress.pos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockLogic {

	private StockDao dao;

	public StockLogic(StockDao dao) {
		this.dao = dao;
	}

	public void add(String name, int price) throws ClassNotFoundException, SQLException {
		if (price == 0) {
			throw new RuntimeException("Failed to add item which price is 0.");
		}
		dao.insert(name, price);
	}

	public List selectItems() {
		// TODO Auto-generated method stub
		List items = new ArrayList();
		items.add(1);
		// dao.select();
		return items;
	}

}
