
package org.raghuvir.hms.daos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Repository;
@Repository
public class ManageStaffDAOImpl implements ManageStaffDAO {
    private final SessionFactory factory;
    private static ManageStaffDAO instance;

    private ManageStaffDAOImpl() {
        this.factory=NewHibernateUtil.getSessionFactory();
    }

    public static ManageStaffDAO getInstance() {
        if(ManageStaffDAOImpl.instance==null) {
        	ManageStaffDAOImpl.instance=new ManageStaffDAOImpl();
        }
        return ManageStaffDAOImpl.instance;
    }

    public synchronized StaffInfoDTO getStaffInfo(String sid) {
        return (StaffInfoDTO) HibernateTemplet.executeTemplate(factory, (Session session) -> {
            
            StaffBEAN obj = (StaffBEAN) session.get(StaffBEAN.class,sid);
            if(obj==null){
                obj=new StaffBEAN();
                obj.setUserId(IDGenerator.getInstance().getNextId("S"));
            }
            StaffInfoDTO staffinfo=new StaffInfoDTO(obj, obj.getJob(), obj.getSalary(), obj.getDateofjoin(), obj.getArrivaltime(),
                    obj.getDeparttime());
            System.out.println(staffinfo);
            return staffinfo;
        });
    }
    @Override
    public synchronized LoginBEAN getIdPassword(String sid) {
        return (LoginBEAN) HibernateTemplet.executeTemplate(factory, (Session session) -> {
            
            LoginBEAN obj = (LoginBEAN) session.get(LoginBEAN.class,sid);
            if(obj==null){
                obj=new LoginBEAN();
            }
            return obj;
        });
    }

	@Override
	public List<StaffBEAN> searchStaff(String query) {
		return (List<StaffBEAN>)HibernateTemplet.executeSearchTemplate(factory,(FullTextSession ftxtsession)-> {
			QueryBuilder qb = ftxtsession.getSearchFactory().buildQueryBuilder().forEntity(StaffBEAN.class).get();
			org.apache.lucene.search.Query lucenequery = qb.keyword()
					.onFields("name","emailaddress","userId","gender")
					.matching(query).createQuery();
			org.hibernate.Query hibQuery = ftxtsession.createFullTextQuery(lucenequery, StaffBEAN.class);
			List<StaffBEAN> list=hibQuery.list();
			return list;			
		});
	}

	@Override
	public synchronized long getTotalStaff() {
		return (Long) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return (Long) session.createCriteria(StaffBEAN.class)
					.setProjection(Projections.rowCount())
					.add(Restrictions.ne("job",EntitiesConstants.DOCTOR).ignoreCase())
					.uniqueResult();
		});
	}
}
