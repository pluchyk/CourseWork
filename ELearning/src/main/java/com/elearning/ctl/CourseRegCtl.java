package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.bean.BaseBean;
import com.elearning.bean.CourseBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.CourseModel;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;



/**
 * Servlet implementation class CourseRegCtl
 */
@WebServlet(name = "CourseRegCtl", urlPatterns = {"/home/login/facilitator/dashboard/addcourse"})
public class CourseRegCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRegCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	CourseBean bean = new CourseBean();
    	bean.setId(DataUtility.getLong(request.getParameter("id")));
    	bean.setCourseName(DataUtility.getString(request.getParameter("cname")));
    	bean.setCourseDescription(DataUtility.getString(request.getParameter("cdesc")));
    	populateDTO(bean, request);
    	return bean;
    }
    @Override
    protected boolean validate(HttpServletRequest request) {
    	boolean pass= true;
    	if (DataValidator.isNull(request.getParameter("cname"))) {
			request.setAttribute("cname",
					PropertyReader.getValue("error.require", "Cousre Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("cname"))) {
			request.setAttribute("cname",
					PropertyReader.getValue("error.name", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("cdesc"))) {
			request.setAttribute("cdesc",
					PropertyReader.getValue("error.require", "Course Description"));
			pass = false;
		} 
		return pass;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        CourseModel model = new CourseModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (OP_SAVE.equalsIgnoreCase(op)) {
	        	CourseBean bean = (CourseBean) populateBean(request);
	            HttpSession session=request.getSession();
	            UserBean uBean=(UserBean)session.getAttribute("user");
	            bean.setUserId(uBean.getId());
	            try {
	                if (id > 0) {
	                    model.update(bean);
	                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                } else {
	                    long pk = model.add(bean);
	                    ServletUtility.setBean(bean, request);
	                    ServletUtility.setSuccessMessage("Course is successfully Added",request);
	                }
	              
	               
	            } catch (ApplicationException e) {
	               
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage(e.getMessage(), request);
	            }
	            ServletUtility.forward(getView(), request, response);
	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	        	CourseBean bean = (CourseBean) populateBean(request);

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
	        	
	        	
	        }else if (OP_RESET.equalsIgnoreCase(op)) {
	    		
	    }
	    					
	        ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.FACILITAOR_DASHBOARD_VIEW;
	}

}
