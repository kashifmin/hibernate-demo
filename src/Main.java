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
import java.util.Scanner;

/**
 * Created by kashif on 22/3/17.
 */
public class Main {
    static SessionFactory sf;
    static Scanner scanner;
    static List<Department> departments;
    public static void main(String args[]) throws ClassNotFoundException {

        sf = HibernateUtils.getSessionFactory();
        scanner = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("\nMENU 1. ADD DEPT\n2. LIST DEPT\n3. ADD EMP\n4. LIST EMP\n5. LIST EMPS IN A DEPT\n6. EXIT");
            System.out.println("** ENTER CHOICE **");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addDepartment(); break;
                case 2: listDepartments(); break;
                case 3: addEmployee(); break;
                case 4: listEmployees(); break;
                case 5: listDepartmentEmps();
                case 6: break;
            }
        } while (choice != 5);
//
//        listDepartments();
//        addDepartment();
//        listDepartments();
//
//        System.out.println("successfully saved");
    }

    private static void listDepartments() {
        Session session = sf.openSession();
        session.beginTransaction();
        departments = (List<Department>) session.createQuery( "from Department" ).list();

        System.out.println("No. of departments: " + departments.size());
        for ( Department d : (List<Department>) departments ) {
            System.out.println(d.getId() + " : " + d.getName() );
        }
        session.getTransaction().commit();
        session.close();
    }

    private static void listDepartmentEmps() {
        listDepartments();
        System.out.println("Choose one DEPARTMENT(Enter index): ");
        int deptIndex = scanner.nextInt();
        Department d =  departments.get(deptIndex);
        Session session = sf.openSession();
        session.beginTransaction();
        List emps = session.createQuery("from Employee E where E.department.id = " + d.getId() ).list();

        System.out.println("No. of employees: " + emps.size());
        for ( Employee e : (List<Employee>) emps ) {
            System.out.println(e.getSsn() + "\t" + e.getName() + "\t" + e.getAge() + "\t" + e.getSex() + "\t" + e.getDepartment().getName() );
        }

    }

    private static List fetchDepartments() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Department" ).list();
        return result;
    }

    private static void addDepartment() {
        Session session = sf.openSession();
        session.beginTransaction();

        int id = scanner.nextInt();
        String name = scanner.next();

        Department d = new Department();
        d.setId(id);
        d.setName(name);
        session.save(d);

        session.getTransaction().commit();
        session.close();
    }

    private static void addEmployee() {
        Session session = sf.openSession();
        session.beginTransaction();

        System.out.println("Enter SSN: " );
        int ssn = scanner.nextInt();
        System.out.println("Enter Name: " );
        String name = scanner.next();
        System.out.println("Enter Age: " );
        int age = scanner.nextInt();
        System.out.println("Enter Gender: " );
        String gen = scanner.next();
        System.out.println("Enter Department No. (index): " );
        listDepartments();
        int deptId = scanner.nextInt();

        Employee e = new Employee();
        e.setSsn(ssn);
        e.setName(name);
        e.setSex(gen);
        e.setAge(age);
        e.setDepartment(departments.get(deptId));
        session.save(e);

        session.getTransaction().commit();
        session.close();
    }

    private static void listEmployees() {
        Session session = sf.openSession();
        session.beginTransaction();
        List emps = session.createQuery( "from Employee" ).list();

        System.out.println("No. of employees: " + emps.size());
        for ( Employee e : (List<Employee>) emps ) {
            System.out.println(e.getSsn() + "\t" + e.getName() + "\t" + e.getAge() + "\t" + e.getSex() + "\t" + e.getDepartment().getName() );
        }
        session.getTransaction().commit();
        session.close();
    }
}
