package test;

import employee.Employee;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import java.util.Iterator;

public class QueryEmp {

  private static PersistenceManager pm;
  private static Transaction tx;

  public static void main(String[] args) {
    try {
      pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
      tx = pm.currentTransaction();
      getAllEmployees(pm);
      pm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void getAllEmployees(PersistenceManager pm) {
    System.out.println("The following employees are in the database:");
    tx = pm.currentTransaction();
    tx.begin();
    Extent extent = pm.getExtent(Employee.class,false);
    for (Iterator i = extent.iterator(); i.hasNext();) {
      Employee emp = (Employee)(i.next());
      System.out.println(emp);
    }
    tx.commit();
  }


}


