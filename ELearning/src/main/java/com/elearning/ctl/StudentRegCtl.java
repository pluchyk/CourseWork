package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.bean.BaseBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.UserModel;
import com.elearning.model.UserModelImpl;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class StudentRegCtl
 */
@WebServlet(name = "StudentRegCtl",urlPatterns = {"/home/signup/student"})
public class StudentRegCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    @Override
    protected boolean validate(HttpServletRequest request) {
    	boolean pass= true;
    	if (DataValidator.isNull(request.getParameter("fname"))) {
			request.setAttribute("fname",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("fname"))) {
			request.setAttribute("fname",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lname"))) {
			request.setAttribute("lname",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lname"))) {
			request.setAttribute("lname",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}
		if (DataValidator.isNull(request.getParameter("cpassword"))) {
			request.setAttribute("cpassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("password").equals(
				request.getParameter("cpassword"))
				&& !"".equals(request.getParameter("cpassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("cpassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		return pass;
    	
    }
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleid(2);
		bean.setFirstName(DataUtility.getString(request
				.getParameter("fname")));

		bean.setLastName(DataUtility.getString(request.getParameter("lname")));

			bean.setLogin(DataUtility.getString(request.getParameter("login")));
			
	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
			bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("cpassword")));
	
			
			
			populateDTO(bean, request);
	
			
	
			return bean;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModelImpl();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			
			UserBean bean = (UserBean) populateBean(request);
		
			try {
		
				long pk = model.registerUser(bean);
				
				bean.setId(pk);
			
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Student Successfully Registered", request);
				ServletUtility.forward(ELearnView.Student_REG_VIEW, request, response);
				return;
			} catch (DuplicateRecordException e) {
				
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ELearnView.FACILITATOR_REG_VIEW, request, response);
			return;
		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.Student_REG_VIEW;
	}

}
