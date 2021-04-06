package client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import pojo.*;

import java.rmi.*;
public class Client {
	public static void main(String[] args) {
		RemoteInterface hello;
		System.setSecurityManager(new RMISecurityManager());
		try {
			Registry rgsty = LocateRegistry.getRegistry(1095);
			hello = (RemoteInterface) rgsty.lookup("src");
			//
			//inserting some Employees and a Manager
			Hour  arrivalTime1 = new Hour(8, 0, 0); 
			Hour  departureTime1 = new Hour(16, 0, 0); 
			
			Hour  arrivalTime2 = new Hour(9, 20, 0); 
			Hour  departureTime2 = new Hour(11, 0, 0);
			
			Hour  arrivalTime3 = new Hour(8, 40, 0); 
			Hour  departureTime3 = new Hour(13, 0, 0);
			
			Employee e1 = hello.addEmployee("Gomes", 31, 1, arrivalTime1, departureTime1);
			Employee e2 = hello.addEmployee("Leopold", 31, 2, arrivalTime2, departureTime2);
			Employee e3 = hello.addEmployee("William", 31, 3, arrivalTime3, departureTime3);
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Manager manager = hello.addManager("Ramon", 24, 1, employees);
			
			manager.getEmployees().add(e1);
			manager.getEmployees().add(e2);
			manager.getEmployees().add(e3);
			//calling methods
			hello.lateEmployees(manager);
			double lossWithLate = hello.calcLossWithLate(manager);
			
			System.out.println("The total amount of losses with late employees is:"+ lossWithLate);
			
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}
