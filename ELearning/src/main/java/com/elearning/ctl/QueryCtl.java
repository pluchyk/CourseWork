package com.elearning.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.bean.BaseBean;
import com.elearning.bean.RaiseQueryBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.QueryModel;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;





/**
 * Servlet implementation class QueryCtl
 * This is for the faculty: faculty
 */
@WebServlet(name = "QueryCtl", urlPatterns = {"/home/login/faculty/dashboard/queryresolved"})
public class QueryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		QueryModel model = new QueryModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			RaiseQueryBean bean;
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
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass =true;
    	 
    	
		if (DataValidator.isNull(request.getParameter("answer"))) {
			request.setAttribute("answer",
					PropertyReader.getValue("error.require", "Answer"));
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
		bean.setCourseDescription(DataUtility.getString(request.getParameter("question")));
		bean.setStatus(1);
		bean.setAnswer(request.getParameter("answer"));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        QueryModel model = new QueryModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (OP_SAVE.equalsIgnoreCase(op)) {
	            RaiseQueryBean bean = (RaiseQueryBean) populateBean(request);
	          
	            try {
	                if (id > 0) {
	                    model.update(bean);
	                   
	                    ServletUtility.setBean(bean, request);
	                    ServletUtility.setSuccessMessage("Query is Resolved", request);
	                } else {
	                    long pk = model.add(bean);
	                   // bean.setId(pk);
	                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
	                }
	              
	               
	            } catch (ApplicationException e) {
	               
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists", request);
	            }
	            ServletUtility.forward(getView(), request, response);
	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	           

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
	        	
	        	
	        }else if (OP_RESET.equalsIgnoreCase(op)) {
	    		
	    }
	    				
	    		
	        ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.ANSWER_QUERY_VIEW;
	}

}
