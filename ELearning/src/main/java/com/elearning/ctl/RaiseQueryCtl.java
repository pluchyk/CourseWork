package com.elearning.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.bean.BaseBean;
import com.elearning.bean.CourseBean;
import com.elearning.bean.RaiseQueryBean;
import com.elearning.bean.TopicBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.CourseModel;
import com.elearning.model.QueryModel;
import com.elearning.model.TopicModel;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class RaiseQueryCtl
 */
@WebServlet(name = "RaiseQueryCtl", urlPatterns = {"/home/login/student/dashboard/raisequery"})
public class RaiseQueryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RaiseQueryCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected boolean validate(HttpServletRequest request) {
    	boolean pass =true;
    	 
    	
		if (DataValidator.isNull(request.getParameter("tdesc"))) {
			request.setAttribute("tdesc",
					PropertyReader.getValue("error.require", "Topic Description"));
			System.out.println("Pass 4 "+pass);
			pass = false;
		} 
		System.out.println("pass "+pass);
		return pass;
    }
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	RaiseQueryBean bean = new RaiseQueryBean();
    	bean.setId(DataUtility.getLong(request.getParameter("id")));
    	bean.setCourseName(DataUtility.getString(request.getParameter("cname")));
    	bean.setCourseDescription(DataUtility.getString(request.getParameter("tdesc")));
    	bean.setStatus(2);
    	
    	populateDTO(bean, request);
    	return bean;
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
		 System.out.println("******in do post*****");
			String op = DataUtility.getString(request.getParameter("operation"));
			System.out.println("op is "+op);
		        // get model
		        QueryModel model = new QueryModel();
		        long id = DataUtility.getLong(request.getParameter("id"));
		        System.out.println("*****here-here****");
		        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
		        	System.out.println("*****here****");
		        	RaiseQueryBean bean = (RaiseQueryBean) populateBean(request);
		            HttpSession session=request.getSession();
		            UserBean uBean=(UserBean)session.getAttribute("user");
		            bean.setUserId(uBean.getId());
		            try {
		                System.out.println("//////hello*****");
		                    long pk = model.add(bean);
		                    ServletUtility.setBean(bean, request);
		                    ServletUtility.setSuccessMessage("Query is successfully Raised",request);
		                    ServletUtility.forward(getView(), request, response);
		    				return;
		                }
		            catch(DuplicateRecordException e){
		            	ServletUtility.setBean(bean, request);
						ServletUtility.setErrorMessage("Login id already exists",
								request);
						ServletUtility.forward(getView(), request, response);
		            } catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		              
		               
		        }
		        
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.RAISE_QUERY_VIEW;
	}

}
