package org.raghuvir.hms.daos;

import java.util.Date;
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
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Repository;
import org.raghuvir.hms.dtos.RoomEntryDTO;
@Repository
public class ManagePatientDAOImpl implements ManagePatientDAO {

	private final SessionFactory factory;
	private static ManagePatientDAO instance;

	private ManagePatientDAOImpl() {
		this.factory = NewHibernateUtil.getSessionFactory();
	}

	public static ManagePatientDAO getInstance() {
		if (ManagePatientDAOImpl.instance == null) {
			ManagePatientDAOImpl.instance = new ManagePatientDAOImpl();
		}
		return instance;
	}

	@Override
	public synchronized void addPrescription(long caseid, String prescription) {
		HibernateTemplet.executeTemplate(factory, (Session session) -> {
			ChiefComplaintBEAN complaint = (ChiefComplaintBEAN) session.get(ChiefComplaintBEAN.class, caseid);
			complaint.setPrescription(prescription);
			complaint.setStatus(EntitiesConstants.CHECKED);
			session.merge(complaint);
			return null;
		});
	}

	public synchronized Set<HmsUserBEAN> getPatientListForDoctor(String did) {

		Set<HmsUserBEAN> users = new HashSet<>();
		return (Set<HmsUserBEAN>) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			List<ChiefComplaintBEAN> drs_complaint = session.createCriteria(ChiefComplaintBEAN.class)
					.add(Restrictions.eq("status", EntitiesConstants.PENDING).ignoreCase())
					.add(Restrictions.eq("doctor.userId", did).ignoreCase()).list();
			drs_complaint.forEach((complaint) -> {
				users.add((new HmsUserBEAN()).copy(complaint.getPatient()));
			});
			return users;
		});

	}

	@Override
	public synchronized PatientInfoDTO getPatientInfo(String pid) {
		return (PatientInfoDTO) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			PatientBEAN obj = (PatientBEAN) session.get(PatientBEAN.class, pid);
			List<ChiefComplaintDTO> complaints = new LinkedList<>();
			List<RoomEntryDTO> entries=new LinkedList<>();
			ManageRoomDAO instance=ManageRoomDAOImpl.getInstance();
			obj.getChiefcomplaint().forEach((complaint) -> {
				complaints.add((new ChiefComplaintDTO()).copy(complaint));
			});
			obj.getRoomentries().forEach((entry)->{
				entries.add((new RoomEntryDTO()).copy(entry));
			});
			return new PatientInfoDTO((new HmsUserBEAN()).copy(obj)
					,complaints,entries
					,instance.getAvailableRoomList("vip")
					,instance.getAvailableRoomList("regular")
					,instance.getAvailableRoomList("general")
					);
		});
	}

	@Override
	public synchronized long getTotalPatients() {
		return (Long) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return (Long) session.createCriteria(PatientBEAN.class).setProjection(Projections.rowCount())
					.uniqueResult();
		});
	}

	@Override
	public Set<String> getDoctorIds() {
		return (Set<String>) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return new HashSet<>((List<String>) session.createCriteria(DoctorBEAN.class)
					.setProjection(Projections.property("userId")).list());
		});
	}

	@Override
	public void addChiefComplaint(ChiefComplaintDTO chiefcomplaint) {
		HibernateTemplet.executeTemplate(factory, (Session session) -> {
			ChiefComplaintBEAN complaint = new ChiefComplaintBEAN();
			complaint.copy(chiefcomplaint);
			complaint.setDoctor((DoctorBEAN) session.get(DoctorBEAN.class, chiefcomplaint.getDoctorid()));
			complaint.setPatient((PatientBEAN) session.get(PatientBEAN.class, chiefcomplaint.getPatientid()));
			session.save(complaint);
			return null;
		});
	}

	@Override
	public List<PatientBEAN> serchPatient(String query,int start,int size) {
		return (List<PatientBEAN>)HibernateTemplet.executeSearchTemplate(factory,(FullTextSession ftxtsession)-> {
			QueryBuilder qb = ftxtsession.getSearchFactory().buildQueryBuilder().forEntity(PatientBEAN.class).get();
			org.apache.lucene.search.Query lucenequery = qb.keyword()
					.onFields("name","emailaddress","userId","gender")
					.matching(query).createQuery();
			org.hibernate.Query hibQuery = ftxtsession.createFullTextQuery(lucenequery, PatientBEAN.class)
					.setFirstResult(start)
					.setMaxResults(size);
			List<PatientBEAN> list=hibQuery.list();
			return list;			
		});
	}

	@Override
	public void addRoomEntry(String pid, String roomno) {
		HibernateTemplet.executeTemplate(factory, (Session session)->{
			session.save(new RoomEntryBEAN(IDGenerator.getInstance().getNextId(),
					(PatientBEAN)session.get(PatientBEAN.class, pid)
					,(RoomBEAN)session.get(RoomBEAN.class, roomno),
					new Date()));
			return null;
		});
	}

}
