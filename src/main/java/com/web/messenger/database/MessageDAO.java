package com.web.messenger.database;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.web.messenger.model.MessengerBean;

public class MessageDAO {

	public Session getConnection() 
	{
		Configuration cfg=new Configuration();
		cfg.configure("/hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		return session;
	}

	public List<MessengerBean> getAllTitles() {
		MessageDAO dao = new MessageDAO();
    	Session session = dao.getConnection();
    	Transaction t = session.beginTransaction();
    	Query query = session.createQuery("from MessengerBean");
    	List titles= query.list();
    	Iterator iterator = titles.iterator();
    	while(iterator.hasNext()){
            MessengerBean msgBean = (MessengerBean)iterator.next(); 
    		System.out.println(msgBean.getImdbID());
    	}
    	
    	dao.closeConnection(session);
    	return titles;
		
	}

	private void closeConnection(Session s) {
		s.flush();
		s.close();
	}

	public boolean addNewTitle(MessengerBean msgBean) {
		System.out.println("in addNewTitle" + msgBean.getImdbID());
		System.out.println("in addNewTitle" + msgBean.getActors());
		System.out.println("in addNewTitle" + msgBean.getReleased());
		
		MessageDAO dao = new MessageDAO();
		/* Getting Session Object */
    	Session session = dao.getConnection();
    	Transaction t = session.beginTransaction();
    	session.save(msgBean);
    	t.commit();
    	dao.closeConnection(session);
    	return true;
	}

	public MessengerBean getTitle(String imdbID) {
		MessageDAO dao = new MessageDAO();
    	Session session = dao.getConnection();
		String hql = "from MessengerBean b where b.imdbID='"+imdbID; 
		Criteria c = session.createCriteria(MessengerBean.class);	
		Criterion  cn = Restrictions.eq("imdbID", imdbID);
		c.add(cn);
		Object o = c.uniqueResult();
		MessengerBean msgBean = (MessengerBean)o;
		return msgBean;
	}

	public boolean updateTitleDao(MessengerBean msgBean) {
		// TODO Auto-generated method stub
		MessageDAO dao = new MessageDAO();
		/* Getting Session Object */
    	Session session = dao.getConnection();
    	Transaction t = session.beginTransaction();
    	session.saveOrUpdate(msgBean);
    	t.commit();
    	dao.closeConnection(session);
    	return true;
	}

	public boolean deleteTitleDao(MessengerBean msgBean) {
		MessageDAO dao = new MessageDAO();
    	Session session = dao.getConnection();
    	Transaction t = session.beginTransaction();
    	session.delete(msgBean);
    	t.commit();
    	dao.closeConnection(session);
    	return true;	
	}
	
}
