package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.bean.CourseBean;
import com.elearning.exception.ApplicationException;
import com.elearning.model.CourseModel;
import com.elearning.util.DataUtility;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class ViewMaterialVideoCtl
 */
@WebServlet(name = "ViewMaterialVideoCtl", urlPatterns = {"/home/login/student/dashboard/coursesearch/courseview/videomaterial"})
@MultipartConfig(maxFileSize = 16177215)
public class ViewMaterialVideoCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMaterialVideoCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		CourseModel model = new CourseModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			CourseBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
              
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
		ServletUtility.forward(getView(), request, response);
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
		return ELearnView.MATERIAL_VIDEO_VIEW;
	}

}
