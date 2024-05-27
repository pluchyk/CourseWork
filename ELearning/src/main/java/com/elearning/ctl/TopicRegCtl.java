package com.elearning.ctl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.elearning.bean.BaseBean;
import com.elearning.bean.CourseBean;
import com.elearning.bean.TopicBean;
import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.CourseModel;
import com.elearning.model.TopicModel;
import com.elearning.util.DataUtility;
import com.elearning.util.DataValidator;
import com.elearning.util.PropertyReader;
import com.elearning.util.ServletUtility;





/**
 * Servlet implementation class TopicRegCtl
 */
@WebServlet(name = "TopicRegCtl", urlPatterns = {"/home/login/facilitator/viewcourse/topic"})
@MultipartConfig(maxFileSize = 16177215)
public class TopicRegCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicRegCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    @Override
    protected void preload(HttpServletRequest request) {
    	CourseModel model = new CourseModel();
    	try {
			List l = model.list();
			System.out.println("is list returning?" + l.get(0));
			request.setAttribute("courseList", l);
			Iterator<CourseBean> itr=l.iterator();
			while(itr.hasNext()){
				CourseBean b=new CourseBean();
				b=itr.next();
				System.out.println("b"+b.getCourseName());
			}
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    protected boolean validate(HttpServletRequest request) {
    	boolean pass =true;
    	if (DataValidator.isNull(request.getParameter("cname"))) {
			request.setAttribute("cname",
					PropertyReader.getValue("error.require", "Cousre Name"));
			pass = false;
			System.out.println("Pass 1 "+pass);
		} 
    	if (DataValidator.isNull(request.getParameter("tname"))) {
			request.setAttribute("tname",
					PropertyReader.getValue("error.require", "Topic Name"));
			System.out.println("Pass 3 "+pass);
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("tdesc"))) {
			request.setAttribute("tdesc",
					PropertyReader.getValue("error.require", "Topic Description"));
			System.out.println("Pass 4 "+pass);
			pass = false;
		} 
		/*if (DataValidator.isNull(request.getParameter("uploadmaterial"))) {
			request.setAttribute("uploadmaterial",
					PropertyReader.getValue("error.require", "Upload Material"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("uploadvideo"))) {
			request.setAttribute("uploadvideo",
					PropertyReader.getValue("error.require", "Upload Video"));
			pass = false;
		} */
		System.out.println("pass is "+pass);
    	return pass;
    }
    public Blob MaterialUpload(Part part) throws IOException {
		InputStream inputStream = null;
		Blob blob = null;
		inputStream = part.getInputStream();
		byte[] b = new byte[inputStream.available()];
		inputStream.read(b);

		try {
			blob = new SerialBlob(b);

		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	
    	
    	TopicBean bean = new TopicBean();
    	bean.setId(DataUtility.getLong(request.getParameter("id")));
    	bean.setCourseId(DataUtility.getLong(request.getParameter("cname")));
    	bean.setTopicName(DataUtility.getString(request.getParameter("tname")));
    	bean.setTopicDescription(DataUtility.getString(request.getParameter("tdesc")));
    	Blob blob=null;
		Blob blob2=null;   // For Video Upload
		
			Part filepart;
			try {
				filepart = request.getPart("uploadmaterial");
				Part filepart2 = request.getPart("uploadvideo");
				blob = MaterialUpload(filepart);
				blob2 = MaterialUpload(filepart2);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //Upload material method called
		
		bean.setUploadTopicMaterial(blob);
		bean.setUploadTopicVideo(blob2);
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
	        // get model
	        TopicModel model = new TopicModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
	        	System.out.println("*****here****");
	        	TopicBean bean = (TopicBean) populateBean(request);
	            HttpSession session=request.getSession();
	            UserBean uBean=(UserBean)session.getAttribute("user");
	            bean.setUserId(uBean.getId());
	            try {
	                System.out.println("//////hello*****");
	                    long pk = model.add(bean);
	                    ServletUtility.setBean(bean, request);
	                    ServletUtility.setSuccessMessage("Topic is successfully Added",request);
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
		return ELearnView.TOPIC_REG_VIEW;
	}

}
