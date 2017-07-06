/**
 * Created by tanuj on 7/4/17.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Main1 extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public Main1()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        boolean flag=false;
        String userName=request.getParameter("user");
        String passWord=request.getParameter("passw");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_sql", "root", "tanuj");
            String qry="select * from ids where username=? and pass=?";
            PreparedStatement ps=cn.prepareStatement(qry);
            ps.setString(1, userName);
            ps.setString(2, passWord);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                flag=true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(flag)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", userName);
            response.sendRedirect("blogpg");

        }
        else
        {
            out.println("<h4 style='color:Red'>*wrong username/password</h4>");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
    }

}
