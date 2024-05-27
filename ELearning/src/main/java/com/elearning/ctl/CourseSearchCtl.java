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
import com.elearning.model.UserModel;
import com.elearning.util.DataUtility;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;



/**
 * Servlet implementation class CourseSearchCtl
 */
@WebServlet(name = "CourseSearchCtl",urlPatterns = {"/home/login/student/coursesearch"})
public class CourseSearchCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSearchCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	CourseBean bean = new CourseBean();
    	bean.setCourseName(DataUtility.getString(request.getParameter("cname")));
    	populateDTO(bean, request);
    	return bean;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In do get");
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
		//ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		CourseBean bean = (CourseBean) populateBean(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		
		String[] ids = request.getParameterValues("ids");
		
		CourseModel model = new CourseModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ELearnView.Student_Dashboard_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					UserBean deletebean = new UserBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						//model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
				long id = DataUtility.getLong(request.getParameter("id"));
				 HttpSession session=request.getSession();
		            UserBean uBean=(UserBean)session.getAttribute("user");
				EnrollModel enrollModel = new EnrollModel();
				EnrollBean enrolBean = new EnrollBean();
				enrolBean.setCourseId(DataUtility.getLong(request.getParameter("cid")));
				enrolBean.setStatus(1); //Active It
				enrolBean.setUserId(uBean.getId());
				populateDTO(enrolBean, request);
	           System.out.println("cid bean "+request.getParameter("cid"));
	           System.out.println("id bean "+request.getParameter("id"));
	            
	            try {
	                if (id > 0) {
	                   // model.update(bean);
	                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                } else {
	                    long pk = enrollModel.add(enrolBean);
	                   // enrolBean.setId(enrolBean.getId());
	                    ServletUtility.setBean(enrolBean, request);
	                    
	                    ServletUtility.setSuccessMessage("You have successfuly enrolled for the course",request);
	                    
	                }
	              
	               
	            } catch (ApplicationException e) {
	               
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage(e.getMessage(), request);
	            } 
			}
			
			else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ELearnView.COURSE_SERACH_CTL, request, response);
				return;

			}

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
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.COUSRE_SERACH_VIEW;
	}

}
