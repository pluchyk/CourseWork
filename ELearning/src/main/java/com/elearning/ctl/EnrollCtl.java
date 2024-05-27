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
import com.elearning.bean.EnrollBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.CourseModel;
import com.elearning.model.EnrollModel;
import com.elearning.util.DataUtility;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class EnrollCtl
 */
@WebServlet(name = "EnrollCtl", urlPatterns = {"/home/login/student/coursesearch/enroll"})
public class EnrollCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		EnrollBean bean = new EnrollBean();
		//bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("cid")));
		bean.setStatus(1); //Active It
		populateDTO(bean, request);
		return bean;
	}
    public EnrollCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader
				.getValue("page.size"));

		CourseBean bean = (CourseBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list

		String[] ids = request.getParameterValues("ids");

		CourseModel model = new CourseModel();
		try {

			list = model.search(bean, pageNo, pageSize);

			
		
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			
			ServletUtility.handleException(e, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in dopost");
		String op = DataUtility.getString(request.getParameter("operation"));
		 EnrollModel model = new EnrollModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
	        	EnrollBean bean = (EnrollBean) populateBean(request);
	            HttpSession session=request.getSession();
	            UserBean uBean=(UserBean)session.getAttribute("user");
	            bean.setUserId(uBean.getId());
	            try {
	                if (id > 0) {
	                   // model.update(bean);
	                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                } else {
	                    long pk = model.add(bean);
	                   // ServletUtility.setBean(bean, request);
	                    ServletUtility.setSuccessMessage("You have successfuly enrolled for the course",request);
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

	        	EnrollBean bean = (EnrollBean) populateBean(request);

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
	        	
	        	
	        }else if (OP_RESET.equalsIgnoreCase(op)) {
	    		
	    }
	    					
	      //  ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.COUSRE_SERACH_VIEW;
	}

}
