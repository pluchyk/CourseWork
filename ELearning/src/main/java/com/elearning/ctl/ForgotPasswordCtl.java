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
import com.elearning.exception.RecordNotFoundException;
import com.elearning.model.UserModel;
import com.elearning.model.UserModelImpl;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;





/**
 * Servlet implementation class ForgotPasswordCtl
 */
@WebServlet(name = "ForgotPasswordCtl",urlPatterns = {"/home/signup/forgotpassword"})
public class ForgotPasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	protected boolean validate(HttpServletRequest request) {
		

        boolean pass = true;

        String newpassword = request.getParameter("newpassword");
        String retypepassword = request.getParameter("retypepassword");
        String login = request.getParameter("login");

        if (DataValidator.isNull(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Email Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.email", "Login "));
            pass = false;
        }

        if (DataValidator.isNull(newpassword)) {
            request.setAttribute("newpassword",
                    PropertyReader.getValue("error.require", "New Password"));
            pass = false;
        } else if (!DataValidator.isPassword(newpassword)) {
            request.setAttribute("newpassword",
                    PropertyReader.getValue("error.password", "New Password "));
            pass = false;
        }
        if (DataValidator.isNull(retypepassword)) {
            request.setAttribute("retypepassword",
                    PropertyReader.getValue("error.require", "New Password"));
            pass = false;
        } else if (!DataValidator.isPassword(newpassword)) {
            request.setAttribute("retypepassword",
                    PropertyReader.getValue("error.password", "New Password "));
            pass = false;
        }
        if (!request.getParameter("newpassword").equals(
				request.getParameter("retypepassword"))
				&& !"".equals(request.getParameter("retypepassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("retypepassword", PropertyReader.getValue("error.confirmPassword","Retype Password"));
			pass = false;
		}
       
        return pass;
	}

	
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		

	        UserBean bean = new UserBean();
	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        bean.setPassword(DataUtility.getString(request.getParameter("newpassword")));
	        bean.setConfirmPassword(DataUtility.getString(request.getParameter("retypepassword")));
	       

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

        UserBean bean = (UserBean) populateBean(request);

        // get model
        UserModel model = new UserModelImpl();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
				int i = model.forgetPassword(bean.getPassword(), bean.getConfirmPassword(), bean.getLogin());
				if(i>0){
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("New passoword is set", request);
					ServletUtility.forward(getView(), request, response);
				}
			}catch (RecordNotFoundException e) {
				
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id doesn't exists",
						request);
				ServletUtility.forward(getView(), request, response); }
            catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ServletUtility.forward(getView(), request, response);
        }
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.FORGOT_PASSWORD_VIEW;
	}

}
