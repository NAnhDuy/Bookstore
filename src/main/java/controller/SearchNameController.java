package controller;

import context.DBContext;
import dao.ListProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchNameController", value = "/SearchNameController.html")
public class SearchNameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String search = request.getParameter("search");
            int index = Integer.parseInt(request.getParameter("index"));
            ListProductDAO listProductDAO = new ListProductDAO();
            List<Product> ls = listProductDAO.searchName(search, index);
            int count = listProductDAO.countName(search);
            int size = 9;
            int endPage = count/size;
            if (count % size != 0) {
                endPage++;
            }
            request.setAttribute("listName", ls);
            request.setAttribute("endPage", endPage);
            request.setAttribute("search", search);
            request.getRequestDispatcher("searchByName.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
