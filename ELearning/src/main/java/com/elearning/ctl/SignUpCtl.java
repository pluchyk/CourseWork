package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class SignUpCtl
 */
@WebServlet(name = "SignUpCtl", urlPatterns = {"/home/signup","/home/login"})
public class SignUpCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello! inside do get");
		String action = request.getServletPath();
		System.out.println("ction "+action);
		switch (action) {
		case "/home/signup":
			ServletUtility.forward(getView(), request, response);
			break;
		case "/home/login":
			ServletUtility.forward("/jsp/signinpage.jsp", request, response);
			break;	
		
		default:
			break;
		}
		//ServletUtility.forward(getView(), request, response);
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
		return ELearnView.SIGNUP_VIEW;
	}

}
