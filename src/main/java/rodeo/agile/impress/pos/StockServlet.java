package rodeo.agile.impress.pos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockServlet extends HttpServlet {

    private static final long serialVersionUID = 6961400581681209885L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	ItemDao dao = new ItemDao(dbPath);

        	ItemLogic logic = new ItemLogic(dao);
        	List items = logic.selectItems();
        	request.setAttribute("items", items);
        	
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("items", new ArrayList());
        }
        request.getRequestDispatcher("jsp/stocks/input.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdString = request.getParameter("itemId");
        String amountString = request.getParameter("amount");

        int itemId = 0;
        int amount = 0;
        try {
        	itemId = Integer.parseInt(itemIdString);
        	amount = Integer.parseInt(amountString);

        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	StockDao dao = new StockDao(dbPath);

        	StockLogic logic = new StockLogic(dao);
        	logic.add(itemId, amount);
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("itemId", itemIdString);
            request.setAttribute("amount", amountString);
            request.getRequestDispatcher("jsp/stocks/input.jsp").forward(request, response);
            //request.getRequestDispatcher("jsp/stocks/login.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("jsp/stocks/success.jsp").forward(request, response);
    }

}
