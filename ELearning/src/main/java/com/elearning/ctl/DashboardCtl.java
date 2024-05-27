package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class DashboardCtl
 */
@WebServlet(name = "DashboardCtl", urlPatterns= {"/home/login/facilitator/dashboard","/home/login/student/dashboard","/home/login/faculty/dashboard"})
public class DashboardCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("ction "+action);
		switch (action) {
		case "/home/login/facilitator/dashboard":
			ServletUtility.forward("/jsp/FacilitatorDashboard.jsp", request, response);
			break;
		case "/home/login/student/dashboard":
			ServletUtility.forward("/jsp/studentDashboard.jsp", request, response);
			break;	
		case "/home/login/faculty/dashboard":
			ServletUtility.forward("/jsp/FacultyDashboard.jsp", request, response);
			break;
		default:
			break;
		}
	//	ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.FACILITATOR_LOGIN_VIEW;
	}

}
