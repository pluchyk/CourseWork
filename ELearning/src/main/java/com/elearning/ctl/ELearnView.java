package com.elearning.ctl;

public interface ELearnView {
	public String APP_CONTEXT = "/ELearning";

	public String PAGE_FOLDER = "/jsp";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	public String HOME_VIEW = PAGE_FOLDER + "/home.jsp";
	public String SIGNUP_VIEW = PAGE_FOLDER + "/signuppage.jsp";
	public String SIGNIN_VIEW = PAGE_FOLDER + "/signinpage.jsp";
	public String FACILITATOR_REG_VIEW = PAGE_FOLDER + "/FacilitatorRegView.jsp";
	public String Student_REG_VIEW = PAGE_FOLDER + "/StudentRegView.jsp";
	public String Faculty_REG_VIEW = PAGE_FOLDER + "/FacultyRegView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String FORGOT_PASSWORD_VIEW = PAGE_FOLDER + "/ForgotPasswordView.jsp";
	public String FACILITATOR_LOGIN_VIEW = PAGE_FOLDER + "/FacilitatorLoginView.jsp";
	public String STUDENT_LOGIN_VIEW = PAGE_FOLDER + "/StudentLoginView.jsp";
	public String FACULTY_LOGIN_VIEW = PAGE_FOLDER + "/FacultyLoginView.jsp";
	public String FACILITAOR_DASHBOARD_VIEW =PAGE_FOLDER + "/FacilitatorDashboard.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COUSRE_SERACH_VIEW = PAGE_FOLDER + "/CourseSearchView.jsp";
	public String DISCUSSION_VIEW = PAGE_FOLDER + "/DiscussionView.jsp";
	public String STUDNET_COURSE_VIEW = PAGE_FOLDER + "/StudentCourseView.jsp";
	public String MATERIAL_VIDEO_VIEW = PAGE_FOLDER + "/MaterialVideoView.jsp";
	public String TOPIC_REG_VIEW = PAGE_FOLDER + "/TopicRegView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String RAISE_QUERY_VIEW = PAGE_FOLDER + "/RaiseQueryView.jsp";
	public String ANSWER_QUERY_VIEW = PAGE_FOLDER + "/AnswerQueryView.jsp";
	
	public String MY_PROFILE_CTL = "/home/profile";
	public String ERROR_CTL = "/ctl/ErrorCtl";
	public String HOME_CTl = APP_CONTEXT + "/home";
	public String SIGNUP_CTL = APP_CONTEXT + "/home/signup";
	public String SIGNIN_CTL = APP_CONTEXT + "/home/login";
	public String FACILITATOR_REG_CTL = APP_CONTEXT + "/home/signup/facilitator";
	public String STUDENT_REG_CTL = APP_CONTEXT + "/home/signup/student";
	public String FACULTY_REG_CTL = APP_CONTEXT + "/home/signup/faculty";
	public String FACILITATOR_LOGIN_CTL = APP_CONTEXT + "/home/login/facilitator";
	public String STUDENT_LOGIN_CTL = APP_CONTEXT + "/home/login/student";
	public String FACULTY_LOGIN_CTL = APP_CONTEXT + "/home/login/faculty";
	public String LOGIN_CTL = APP_CONTEXT + "/home/login/facilitator";
	public String FORGOT_PASSWORD_CTL = APP_CONTEXT + "/home/signup/forgotpassword";
	public String Dashboard_CTL = APP_CONTEXT + "/home/login/facilitator/dashboard";
	public String Student_Dashboard_CTL = APP_CONTEXT + "/home/login/student/dashboard";
	public String Faculty_Dashboard_CTL = APP_CONTEXT + "/home/login/faculty/dashboard";
	public String Cousre_Reg_CTL = APP_CONTEXT + "/home/login/facilitator/dashboard/addcourse";
	public String Course_VIEW_CTL = APP_CONTEXT + "/home/login/facilitator/viewcourse/upload";
	public String COURSE_SERACH_CTL = APP_CONTEXT + "/home/login/student/coursesearch";
	public String DISCUSSION_CTL = APP_CONTEXT + "/home/login/student/dashboard/discussion"; 
	public String ENROLL_CTL = APP_CONTEXT + "/home/login/student/coursesearch/enroll"; 
	public String STUDENT_COURSE_VIEW_CTL = APP_CONTEXT + "/home/login/student/dashboard/coursesearch/courseview";
	public String MATERIAL_VIDEO_VIEW_CTL = APP_CONTEXT + "/home/login/student/dashboard/coursesearch/courseview/videomaterial";
	public String TOPIC_REG_CTL = APP_CONTEXT + "/home/login/facilitator/viewcourse/topic";
	public String TOPIC_CTL = APP_CONTEXT + "/home/login/student/dashboard/coursesearch/courseview/video&material";
	public String RAISE_VIEW_CTL = APP_CONTEXT + "/home/login/student/dashboard/raisequery";
	public String QUERY_CTL = APP_CONTEXT + "/home/login/faculty/dashboard/queryresolved";
	
}
