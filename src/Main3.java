/**
 * Created by tanuj on 7/4/17.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main3
 */
@WebServlet("/saveblog")
public class Main3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main3() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");
        String blogID=request.getParameter("bid");
        String blogContent=request.getParameter("bcont");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_sql", "root", "tanuj");
            String qry="insert into blog values(?,?,?)";
            PreparedStatement ps=cn.prepareStatement(qry);
            ps.setString(1, blogID);
            ps.setString(2, blogContent);
            ps.setString(3, username);
            ps.executeUpdate();
            out.println("Your blog has been successfully added!");
            out.println("<h4>blogID<h4>"+blogID);
            out.println("<h4>blogContent<h4>"+blogContent);
            out.println("<h4>username<h4>"+username);
        }
        catch(Exception e)
        {

            System.out.println("Unsuccessful submission: "+e);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
