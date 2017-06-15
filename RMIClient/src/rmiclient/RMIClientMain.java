package rmiclient;

import interfaces.IntegralFunction;
import interfaces.Integrand;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientMain
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
        Integrand integral = (Integrand)Naming.lookup("//localhost/integrand");
        System.out.println(integral.calculateIntegrand(1, 2));
        Naming.rebind("//localhost/function", new SinClass());
        System.out.println(integral.calculateIntegrandWithFunction(1, 2));
        Naming.rebind("//localhost/function", new CosClass());
        System.out.println(integral.calculateIntegrandWithFunction(1, 2));
    }
    
    static class SinClass extends UnicastRemoteObject implements IntegralFunction
    {
        public SinClass() throws RemoteException
        {
            super();
        }
        
        @Override
        public double functionToIntegrand(double arg) throws RemoteException
        {
            return Math.sin(arg);
        }
    }
    
    static class CosClass extends UnicastRemoteObject implements IntegralFunction
    {
        public CosClass()  throws RemoteException
        {
            super();
        }
        
        @Override
        public double functionToIntegrand(double arg) throws RemoteException
        {
            return Math.cos(arg);
        }
    }
}
