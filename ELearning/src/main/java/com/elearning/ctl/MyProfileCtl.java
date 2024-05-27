package com.elearning.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

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








@WebServlet(name="MyProfileCtl",urlPatterns={"/home/profile"})
public class MyProfileCtl extends BaseCtl 
{
	private static final long serialVersionUID = 1L;
	
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
    public static final String OP_CHANGE_MY_PASSWORD="ChangePassword";   
	
    private static Logger log=Logger.getLogger(MyProfileCtl.class);
        
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MyProfileCtl Method validate Started");
	
		boolean pass=true;
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)||op==null){
			return pass;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("login"))){			
		request.setAttribute("login", PropertyReader.getValue("error.require","Login ID"));
		pass=false;
		}
		if(DataValidator.isNull(request.getParameter("firstName"))){
			System.out.println("firstName"+request.getParameter("firstName"));
		request.setAttribute("firstName", PropertyReader.getValue("error.require","First Name"));
		pass=false;
		}else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require","Last Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		} 
		
		if(DataValidator.isNull(request.getParameter("gender"))){
			System.out.println("gender"+request.getParameter("gender"));
			request.setAttribute("error.require", PropertyReader.getValue("Gender"));
			pass=false;
		} else if (DataValidator.isNotNull(request.getParameter("gender"))) {
			if ("Select".equals(request.getParameter("gender"))) {
				request.setAttribute("gender",PropertyReader.getValue("error.require", "Gender"));
				pass = false;
			}
		}
		 if (DataValidator.isNull(request.getParameter("mobileNo"))) {
	            request.setAttribute("mobileNo",PropertyReader.getValue("error.require", "Mobile No"));
	            pass = false;
	        }else if(!DataValidator.isPhoneNo(request.getParameter("mobileNo"))){
				request.setAttribute("mobileNo", PropertyReader.getValue("error.invalid","Mobile No"));
				pass=false;		
			}		
		if(DataValidator.isNull(request.getParameter("dob"))){
			request.setAttribute("error.require", PropertyReader.getValue("Date Of Birth"));
			pass=false;
		}/*else if (!DataValidator.isDate(request.getParameter("dob"))) {
            request.setAttribute("dob",
            		"Min Age Must be 17 years");
            pass = false;
        }*/		
		log.debug("MyProfileCtl Method validate Ended");
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
	log.debug("MyProfileCtl Method PopulateBean Started ");
	return null;
		
		
		
	
		
	}



	/**
     * Display Concept for viewing profile page view
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("MyProfileCTl Method doGet Started");
		System.out.println("in do get of profile");
	
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		long id=userBean.getId();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModelImpl();
		
		
		if(id>0||op !=null){
			System.out.println("in id>0 condition");
			UserBean bean;
			try{
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		 log.debug("MyProfileCtl Method doGet Ended");
		}
		
	@Override
	protected String getView() {

		
		return ELearnView.MY_PROFILE_VIEW;
	}

}
