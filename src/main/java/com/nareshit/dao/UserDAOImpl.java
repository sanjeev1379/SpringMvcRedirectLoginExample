package com.nareshit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nareshit.domain.Login;
@Repository
public class UserDAOImpl 
implements UserDAO{
	@Autowired
private SessionFactory sessionFactory;
	public Login login(Login login) {
Session session=sessionFactory.openSession();
String hql="select login.userId,login.userRole "
+ " from com.nareshit.domain.Login as login where login.userName=? and login.password=?";
  Query query=session.createQuery(hql);
   query.setParameter(0,login.getUserName());
   query.setParameter(1,login.getPassword());
   Object[] obj=(Object[])query.uniqueResult();
   if(obj!=null && obj.length>0){
	  login.setUserId((Integer)obj[0]);
	  login.setUserRole((String)obj[1]);
	  login.setPassword(null);
   }
		return login;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
