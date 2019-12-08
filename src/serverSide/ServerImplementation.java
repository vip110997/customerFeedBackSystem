package serverSide;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class ServerImplementation {
	public static void main(String[] argv) {
		// TODO Auto-generated method stub
		try {

			LocateRegistry.createRegistry(2000);
			Naming.rebind("rmi://0.0.0.0:2000/connect", new FunctionsImplementation());

			System.out.println("Congratulations! Your server is running");

		} catch (Exception e) {
			System.out.println("Server has failed : " + e);
		}
	}
}
