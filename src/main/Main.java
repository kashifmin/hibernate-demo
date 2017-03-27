package main;

import org.hibernate.Session;
import org.hibernate.SessionEventListener;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by kashif on 22/3/17.
 */
public class Main {
    static SessionFactory sf;
    public static void main(String args[]) throws ClassNotFoundException {

        sf = HibernateUtils.getSessionFactory();


        //creating session object
//        Session session=sf.openSession();
//
//        //creating transaction object
//        Transaction t=session.beginTransaction();

//        Employee e1=new Employee();
//        e1.setSsn(69);
//        e1.setName("Dark");
//        e1.setAge(77);
//        e1.setSex("male");
//
//        session.persist(e1);//persisting the object
//
//        t.commit();//transaction is commited
//        session.close();

 //       sf = HibernateUtils.getSessionFactory();

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Department d = new Department();

        Map<String, Object> m = sf.getProperties();

        for (String key: m.keySet()
             ) {
            System.out.println(key);
        }


        s.persist(d);
        d.setId(7);
        d.setName("Kmank");


 //       s.persist(d);

        t.commit();
//
//        listDepartments();

        System.out.println("successfully saved");
    }

    private static void listDepartments() {
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<Department> depts = session.createQuery("FROM " + "main.Department").list();

        System.out.print("No. of dep" + depts.size());
        for(Iterator i = depts.iterator(); i.hasNext(); ) {
            Department d = (Department) i.next();
            System.out.println("Name: " + d.getName());
        }


        t.commit();


    }
}
