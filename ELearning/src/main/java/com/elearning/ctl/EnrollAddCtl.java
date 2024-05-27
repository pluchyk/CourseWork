package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.bean.BaseBean;
import com.elearning.bean.EnrollBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.EnrollModel;
import com.elearning.util.DataUtility;
import com.elearning.util.ServletUtility;



/**
 * Servlet implementation class EnrollAddCtl
 */
@WebServlet(name = "EnrollAddCtl",urlPatterns = {"/home/login/student/coursesearch/enrollcourse"})
public class EnrollAddCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollAddCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		EnrollBean enrolBean = new EnrollBean();
		System.out.println("------cid-----"+request.getParameter("cid"));
		enrolBean.setCourseId(DataUtility.getLong(request.getParameter("id")));
		enrolBean.setStatus(1); //Active It
		populateDTO(enrolBean, request);
		return enrolBean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************hi in do get************");
		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        EnrollModel model = new EnrollModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        System.out.println("********id "+id);
      //  if (OP_SAVE.equalsIgnoreCase(op)) {
            EnrollBean bean = (EnrollBean) populateBean(request);
            HttpSession session=request.getSession();
            UserBean uBean=(UserBean)session.getAttribute("user");
            bean.setUserId(uBean.getId());
            try {
                if (id > 0) {
                    model.add(bean);
                
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                    ServletUtility.redirect(getView(), request, response);
                } else {
                    long pk = model.add(bean);
                   // bean.setId(pk);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                    ServletUtility.redirect(getView(), request, response);
                }
              
               
            } catch (ApplicationException e) {
               
                ServletUtility.handleException(e, request, response);
                return; 
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login id already exists", request);
            }
           // ServletUtility.redirect(getView(), request, response);
       /* } else if (OP_DELETE.equalsIgnoreCase(op)) {

            

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		
	}*/
       // ServletUtility.forward(getView(), request, response);
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
		return ELearnView.Student_Dashboard_CTL;
	}

}
