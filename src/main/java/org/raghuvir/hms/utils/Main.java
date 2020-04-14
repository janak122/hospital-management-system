package org.raghuvir.hms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.daos.HibernateTemplet;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.dtos.RoomInfoDTO;


public class Main {

    public static void main(String args[]) {
    	System.out.println(System.getProperty("user.dir"));
        try {
//            addRecords();
//            insertRooms();
//            printrooms();
//            modifyDates();
//        	searchSomething("suresh");
//        	searchSomething("D2046M5G");
//        	searchSomething("arjun@gmail.com");
     
        } finally {
//            NewHibernateUtil.closeSessionFactory();
        }
    }

    static void searchSomething(String query) {
//    	List<PatientBEAN> list=ManagePatientDAO.getInstance().serchPatient(query);
//    	List<RoomBEAN>list=ManageRoomDAO.getInstance().searchRooms(query);
//    	List<StaffBEAN> list=ManageStaffDAO.getInstance().searchStaff(query);
    	List<DoctorBEAN> list=ManageDoctorDAOImpl.getInstance().searchDoctor(query);
    	list.forEach(entity->{
    		System.out.println(entity);
    	});
    	System.out.println("===============================================");
    }
    
    public static void modifyDates() {
        HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
            StaffBEAN obj = (StaffBEAN) session.get(StaffBEAN.class, "S2046M5J");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            try {
                obj.setArrivaltime(format.parse("02-12-2020 14:30:45"));
            } catch (ParseException ex) {
            }
            session.saveOrUpdate(obj);
            System.out.println(obj);
            return null;
        });
        HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
            StaffBEAN obj = (StaffBEAN) session.get(StaffBEAN.class, "S2046M5J");
            System.out.println(obj.getArrivaltime().getHours());
            System.out.println(obj.getArrivaltime().getMinutes());
            System.out.println(obj.getArrivaltime().getSeconds());

            System.out.println(obj);
            return null;
        });
    }

    public static void printrooms() {
        List list = (List) HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
            return session.createCriteria(RoomBEAN.class)
                    .setProjection(Projections.property("roomno"))
                    .add(Restrictions.eq("typeofroom", "vip").ignoreCase())
                    .add(Restrictions.sizeLt("roomentries", 1))
                    .list();
        });
        if (list != null) {
            list.forEach((item) -> {
                System.out.println(item);
            });
        }
    }

    public static void addRecords() {
        IDGenerator idg = IDGenerator.getInstance();
        String[] patients = {"rahul", "keshav", "arjun", "smit", "amit", "nand", "suresh"};
        String[] doctors = {"tiwari", "chavada", "mahesh", "ramesh"};
        String[] types = {"dental", "mental", "homiophethic", "allinone"};
        String[] degrees = {"mbbs;md", "phd", "ms", "mbbs;phd;ms;md"};
        String[] staffs = {"menka", "sakuntala", "parita", "laila", "juliat"};

        HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
            int i = 0;
            for (String name : patients) {
                session.saveOrUpdate(new PatientBEAN(new HashSet(), new LinkedList(),
                        idg.getNextId("P"), name, "/default.png", "942915195" + i,
                        EntitiesConstants.PATIENT, "home", "male", name + "@gmail.com",
                        new Date(2000, 12, 2))
                );
                i++;
                System.out.println(i);
            }
            i = 0;
            for (String name : doctors) {
                session.saveOrUpdate(new DoctorBEAN(degrees[i], types[i], new HashSet<>(), 1000000,
                        new Date(), new Date(0, 0, 0, 8, 0, 0), new Date(0, 0, 0, 18, 0, 0),
                        "doctor", idg.getNextId("D"), name, "/default.png", "842915175" + i,
                        EntitiesConstants.DOCTOR, "home", "male", name + "@gmail.com",
                        new Date(2000, 12, 2))
                );
                i++;
                System.out.println(i);

            }
            i = 0;
            for (String name : staffs) {
                session.saveOrUpdate(new StaffBEAN(1000000,
                        new Date(), new Date(0, 0, 0, 8, 0, 0), new Date(0, 0, 0, 18, 0, 0),
                        "receptionist", idg.getNextId("S"), name, "/default.png", "742915195" + i,
                        EntitiesConstants.STAFF, "home", "female", name + "@gmail.com",
                        new Date(2000, 12, 2))
                );
                i++;
                System.out.println(i);

            }
            return null;
        });
    }

    public static void insertRooms() {
        HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
            for (int i = 5; i < 10; i++) {
                session.saveOrUpdate(new RoomBEAN("RR10" + i, "regular", new LinkedList()));
                session.saveOrUpdate(new RoomBEAN("RG10" + i, "general", new LinkedList()));
                session.saveOrUpdate(new RoomBEAN("RV10" + i, "vip", new LinkedList()));
            }
            return null;
        });
    }

}
