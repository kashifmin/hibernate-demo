package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by kashif on 22/3/17.
 */
public class Main {
    public static void main(String args[]) throws ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();

        Employee e1=new Employee();
        e1.setId(69);
        e1.setFirstName("Dark");
        e1.setLastName("Knight");

        session.persist(e1);//persisting the object

        t.commit();//transaction is commited
        session.close();

        System.out.println("successfully saved");
    }
}
