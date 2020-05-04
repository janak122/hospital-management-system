package org.raghuvir.hms.daos;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.dtos.RoomListDTO;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.IDGenerator;
import org.raghuvir.hms.utils.NewHibernateUtil;
import org.springframework.stereotype.Repository;
@Repository
public class ManageRoomDAOImpl implements ManageRoomDAO {

	private final SessionFactory factory;
	private static ManageRoomDAO instance;

	private ManageRoomDAOImpl() {
		this.factory = NewHibernateUtil.getSessionFactory();
	}

	public static ManageRoomDAO getInstance() {
		if (ManageRoomDAOImpl.instance == null) {
			ManageRoomDAOImpl.instance = new ManageRoomDAOImpl();
		}
		return instance;
	}

	@Override
	public synchronized List<String> getAvailableRoomList(String type) {
		final String type1 = EntitiesConstants.getValidRoomType(type);
		final int total = EntitiesConstants.getTotalBEDS(type1);
		return (List) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return session.createCriteria(RoomBEAN.class).setProjection(Projections.property("roomno"))
					.add(Restrictions.eq("typeofroom", type1).ignoreCase())
					.add(Restrictions.sizeLt("roomentries", total)).list();
		});
	}

	@Override
	public synchronized void removePatient(String entryid) {
		HibernateTemplet.executeTemplate(factory, (Session session) -> {
			session.delete(session.get(RoomEntryBEAN.class, entryid));
			return null;
		});
	}

	@Override
	public synchronized void addPatient(String pid, String roomno, String date1) {
		Date date = Date.valueOf(date1);
		HibernateTemplet.executeTemplate(factory, (Session session) -> {
			session.save(new RoomEntryBEAN(IDGenerator.getInstance().getNextId(),
					(PatientBEAN) session.get(PatientBEAN.class, pid), (RoomBEAN) session.get(RoomBEAN.class, roomno),
					date));
			return null;
		});
	}

	@Override
	public synchronized RoomInfoDTO getRoomInfo(String roomno) {
		return (RoomInfoDTO) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			RoomInfoDTO room = new RoomInfoDTO();
			room.copy((RoomBEAN) session.get(RoomBEAN.class, roomno));
			return room;
		});
	}

	@Override
	public synchronized List<RoomListDTO> searchRooms(String query) {
		return (List<RoomListDTO>) HibernateTemplet.executeSearchTemplate(factory, (FullTextSession ftxtsession) -> {
			QueryBuilder qb = ftxtsession.getSearchFactory().buildQueryBuilder().forEntity(RoomBEAN.class).get();
			org.apache.lucene.search.Query lucenequery = qb.keyword().onFields("roomno", "typeofroom").matching(query)
					.createQuery();
			org.hibernate.Query hibQuery = ftxtsession.createFullTextQuery(lucenequery, RoomBEAN.class);
			List<RoomBEAN> list = hibQuery.list();
			List<RoomListDTO> list1=new LinkedList<>();
			for(RoomBEAN r:list) {
				list1.add((new RoomListDTO(r.getRoomno(), r.getTypeofroom(), r.getRoomentries().size())));
			}
			return list1;
		});
	}

	@Override
	public synchronized long getTotalRooms() {
		return (Long) HibernateTemplet.executeTemplate(factory, (Session session) -> {
			return (Long) session.createCriteria(RoomBEAN.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
		}); 
	}

}
