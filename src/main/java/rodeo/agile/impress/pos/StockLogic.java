package rodeo.agile.impress.pos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockLogic {

	private StockDao dao;

	public StockLogic(StockDao dao) {
		this.dao = dao;
	}

	public void add(int itemId, int amount) throws ClassNotFoundException, SQLException {
		if (amount == 0) {
			throw new RuntimeException("Failed to add stock which amount is 0.");
		}
		dao.insert(itemId, amount);
	}

	public List selectItems() {
		// TODO Auto-generated method stub
		List items = new ArrayList();
		items.add(1);
		// dao.select();
		return items;
	}

}
