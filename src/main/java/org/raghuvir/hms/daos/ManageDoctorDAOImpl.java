package org.raghuvir.hms.daos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Repository;
@Repository
public class ManageDoctorDAOImpl implements ManageDoctorDAO{
    private final SessionFactory factory;
    private static ManageDoctorDAO instance;

    private ManageDoctorDAOImpl() {
    	this.factory=NewHibernateUtil.getSessionFactory();
    }

    public static ManageDoctorDAO getInstance() {
    	if(ManageDoctorDAOImpl.instance==null) {
    		ManageDoctorDAOImpl.instance=new ManageDoctorDAOImpl();
    	}
        return ManageDoctorDAOImpl.instance;
    }

    @Override
    public synchronized DoctorInfoDTO getDoctorInfo(String did) {
        return (DoctorInfoDTO) HibernateTemplet.executeTemplate(factory, (Session session) -> {
            
            DoctorBEAN obj = (DoctorBEAN) session.get(DoctorBEAN.class,did);
            if(obj==null){
                obj=new DoctorBEAN();
                obj.setUserId(IDGenerator.getInstance().getNextId("D"));
            }
            DoctorInfoDTO doctorinfo=(new DoctorInfoDTO()).copy(obj);
            return doctorinfo;
        });
    }
    @Override
    public synchronized LoginBEAN getIdPassword(String did) {
        return (LoginBEAN) HibernateTemplet.executeTemplate(factory, (Session session) -> {
            
            LoginBEAN obj = (LoginBEAN) session.get(LoginBEAN.class,did);
            if(obj==null){
                obj=new LoginBEAN();
            }
            return obj;
        });
    }

	@Override
	public synchronized List<DoctorBEAN> searchDoctor(String query) {
		return (List<DoctorBEAN>)HibernateTemplet.executeSearchTemplate(factory,(FullTextSession ftxtsession)-> {
			QueryBuilder qb = ftxtsession.getSearchFactory().buildQueryBuilder().forEntity(DoctorBEAN.class).get();
			org.apache.lucene.search.Query lucenequery = qb.keyword()
					.onFields("name","emailaddress","userId","gender")
					.matching(query).createQuery();
			org.hibernate.Query hibQuery = ftxtsession.createFullTextQuery(lucenequery, DoctorBEAN.class);
			List<DoctorBEAN> list=hibQuery.list();
			return list;			
		});
	}

	@Override
	public synchronized long getTotalDoctors() {
		return (Long) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return (Long) session.createCriteria(DoctorBEAN.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
		});
	}
    
}