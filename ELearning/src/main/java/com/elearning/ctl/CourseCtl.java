package com.elearning.ctl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.elearning.bean.BaseBean;
import com.elearning.bean.CourseBean;

import com.elearning.exception.ApplicationException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.model.CourseModel;

import com.elearning.util.DataUtility;
import com.elearning.util.ServletUtility;

/**
 * Servlet implementation class CourseCtl
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/home/login/facilitator/viewcourse/upload" })
@MultipartConfig(maxFileSize = 16177215)
public class CourseCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
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
		CourseBean bean = new CourseBean();
		/*
		 * Lets' call a method
		 */

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		/*InputStream inputStream=null; */
		Blob blob=null;
		Blob blob2=null;   // For Video Upload
		
			Part filepart;
			try {
				filepart = request.getPart("material");
				Part filepart2 = request.getPart("video");
				blob = MaterialUpload(filepart);
				blob2 = MaterialUpload(filepart2);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //Upload material method called
		
		bean.setUploadMaterial(blob);
		bean.setUploadVideo(blob2);
		populateDTO(bean, request);
		return bean;

	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In do post");
		String op = DataUtility.getString(request.getParameter("operation"));

		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			CourseBean bean = (CourseBean) populateBean(request);

			System.out.println("bean info: Name " + bean.getCourseName() + "bean photo" + bean.getUploadMaterial()
					+ " video" + bean.getUploadVideo());
			try {
				if (id > 0) {
					model.updateMaterialVideo(bean);

					ServletUtility.setSuccessMessage("Data is successfully Uploaded", request);
				} else {
					long pk = model.add(bean);

					ServletUtility.setSuccessMessage("Data is successfully saved", request);
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

			CourseBean bean = (CourseBean) populateBean(request);

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ELearnView.Course_VIEW_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ELearnView.COURSE_VIEW;
	}

}
