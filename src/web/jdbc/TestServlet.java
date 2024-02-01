package web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step1: set up the Print Writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		//step2: get the connection to the database
		Connection myCon = null;
		Statement myState = null;
		ResultSet myRs = null;
		try {
			myCon = dataSource.getConnection();
			//step3: create sql statement
			String sql = "select * from student";
			myState = myCon.createStatement();
			//step4: execute sql query
			myRs = myState.executeQuery(sql);
			//step5: process the result set
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
