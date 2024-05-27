package com.elearning.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.bean.CourseBean;
import com.elearning.bean.RaiseQueryBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.model.CourseModel;
import com.elearning.model.QueryModel;
import com.elearning.util.DataUtility;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class DiscussionCtl
 */
@WebServlet(name = "DiscussionCtl",urlPatterns = {"/home/login/student/dashboard/discussion"})
public class DiscussionCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscussionCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		RaiseQueryBean bean = (RaiseQueryBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		

		String[] ids = request.getParameterValues("ids");

		QueryModel model = new QueryModel();
		try {

			HttpSession session = request.getSession();
			UserBean uBean = (UserBean) session.getAttribute("user");

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
		return ELearnView.DISCUSSION_VIEW;
	}

}
