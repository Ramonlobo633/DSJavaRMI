package client;

import java.rmi.*;
import java.util.ArrayList;

import pojo.Employee;
import pojo.Hour;
import pojo.Manager;

public interface RemoteInterface extends Remote {
	public Manager addManager(String nome, int age, int idManager, ArrayList<Employee> employees) throws RemoteException;
	public Employee addEmployee(String nome, int age, int idEmployee, Hour arrivalTime, Hour departureTime) throws RemoteException;
	public Employee winningEmployee(ArrayList<Employee> employees) throws RemoteException;
	public void lateEmployees(Manager manager) throws RemoteException;
	public double calcLossWithLate(Manager manager) throws RemoteException;
	
}
