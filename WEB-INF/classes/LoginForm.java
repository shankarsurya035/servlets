import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class LoginForm extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out=res.getWriter();
		String u=req.getParameter("n1");
		String v=req.getParameter("n2");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","suryamona2000");
			Statement st=con.createStatement();
			String sql="select *from logindemo where uname='"+u+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				if(v.equals(rs.getString(2)))
				{
					out.println("VALID USER"+u);
				}
				else
				{
					out.println("INVALID PASSWORD");
				}
			}
			else
		    {
		         	out.println("INVALID USER");
		    }
		}
		catch (Exception e)
		{
			out.println(e);
		}
	}
}
