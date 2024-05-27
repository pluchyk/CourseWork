package com.elearning.model;

import java.util.List;

import com.elearning.bean.UserBean;
import com.elearning.exception.ApplicationException;
import com.elearning.exception.DatabaseException;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.exception.RecordNotFoundException;

public interface UserModel {
	
	/*TODO: This method generates the next primary key. */
	public Integer nextPk() throws DatabaseException;
	/*TODO: This method perform creation of user */
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException;
	/*TODO: This method perform deletion of user  */
	public void delete(UserBean bean) throws ApplicationException;
	/*TODO: This method perform finds user by login/username/emailid	 */
	public UserBean findByLogin(String login) throws ApplicationException;
	/*TODO: This method perform finds user by id	 */
	public UserBean findByPK(long pk) throws ApplicationException;
	/*TODO: This method perform update of user	 */
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException;
	/*TODO: This method search user	 */
	public List search(UserBean bean) throws ApplicationException;
	/*TODO: This method display list of user	 */
	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException;
	/*TODO: This method display list of user	 */
	public List list() throws ApplicationException;
	/*TODO: This method display list of user with pagination	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;
	/*TODO: This method authnticate	 */
	public UserBean authenticate(String login, String password) throws ApplicationException;
	/*TODO: This method register user	 */
	public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException;
	/*TODO: This method used by user to forgot password	 */
	int forgetPassword(String newpassword, String retypepassword, String login)
			throws RecordNotFoundException, ApplicationException, Exception;
	
	
}
