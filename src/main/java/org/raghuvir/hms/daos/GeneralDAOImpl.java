package org.raghuvir.hms.daos;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Repository;
@Repository
public class GeneralDAOImpl implements GeneralDAO {

    private final SessionFactory factory;
    private static GeneralDAO instance;

    private GeneralDAOImpl() {
        this.factory = NewHibernateUtil.getSessionFactory();
    }

    public static GeneralDAO getInstance() {
        if (GeneralDAOImpl.instance == null) {
        	GeneralDAOImpl.instance = new GeneralDAOImpl();
        }
        return instance;
    }

    @Override
    public Object getEntity(Class clazz, Serializable key) {
        return HibernateTemplet.executeTemplate(factory, (Session session) -> {
            return session.get(clazz, key);
        });
    }

    @Override
    public void insertUpdate(Object entity) {
        HibernateTemplet.executeTemplate(factory, (Session session) -> {
            session.saveOrUpdate(entity);
            return null;
        });
    }

    @Override
    public void delete(Object entity) {
        HibernateTemplet.executeTemplate(factory, (Session session) -> {
            session.delete(entity);
            return null;
        });
    }

    @Override
    public List getList(Class clazz, int start, int size, Criterion... criterions) {
        return (List) HibernateTemplet.executeTemplate(factory, (Session session) -> {
            Criteria criteria = session.createCriteria(clazz)
                    .setFirstResult(start)
                    .setMaxResults(size);
            for (Criterion c : criterions) {
                criteria = criteria.add(c);
            }
            return criteria.list();
        });
    }
}
