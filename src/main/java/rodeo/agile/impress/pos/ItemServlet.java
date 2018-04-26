package rodeo.agile.impress.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet {

    private static final long serialVersionUID = 6961400581681209885L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/stocks/item.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceString = request.getParameter("price");

        int price = 0;
        try {
        	price = Integer.parseInt(priceString);

        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	ItemDao dao = new ItemDao(dbPath);

        	ItemLogic logic = new ItemLogic(dao);
        	logic.add(name, price);
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("name", name);
            request.setAttribute("price", priceString);
            request.getRequestDispatcher("jsp/stocks/item.jsp").forward(request, response);
            //request.getRequestDispatcher("jsp/stocks/login.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("jsp/stocks/success.jsp").forward(request, response);
    }

}
