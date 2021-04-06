package server;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
	public static void main(String args[]){
		 if(System.getSecurityManager() == null){
	       	System.setSecurityManager(new RMISecurityManager());
		 } else {
	       	System.out.println("Already has a security manager, so cant set RMI SM");
	     }
		 System.out.println("RMISecurityManager OK");
		 try{
			 Registry rgsty = LocateRegistry.createRegistry(1095);
	         System.out.println("LocateRegistry OK");
			 RemoteInterface refServente = new Servante();
			 rgsty.rebind("server_nickname", refServente);
			 System.out.println("Running server");
		 }catch(Exception e) {
			 System.out.println("ShapeList server main " +
			 e.getMessage());
		 }
	}
	
}
