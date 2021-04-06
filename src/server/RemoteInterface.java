package server;

import java.rmi.*;
import java.util.ArrayList;

import pojo.Employee;
import pojo.Hour;
public interface RemoteInterface extends Remote{
	public void addManager(String nome, int age, int idManager, ArrayList<Employee> employees, double lossWithLate) throws RemoteException;
	public void addEmployee(String nome, int age, int idEmployee, Hour arrivalTime, Hour departureTime) throws RemoteException;
	public Employee winningEmployee(ArrayList<Employee> employees) throws RemoteException;
}
