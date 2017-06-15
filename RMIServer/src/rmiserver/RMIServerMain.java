package rmiserver;

import interfaces.IntegralFunction;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaces.Integrand;

public class RMIServerMain extends UnicastRemoteObject implements Integrand
{   
    public static void main(String[] args)
    {
        try
        {
            Naming.rebind("//localhost/integrand", new RMIServerMain());
            System.out.println("Server ready!");
        }
        catch(Exception e)
        {
            System.out.println("Some exception happened!");
            System.out.println(e.getMessage());
        }
    }

    public RMIServerMain() throws RemoteException
    {
        super();
    }
    
    @Override
    public double calculateIntegrand(double bottom, double top) throws RemoteException
    {
        System.out.println("Making calculation...");
        double step = 0.01;
        double temp_point=bottom;
        double sum = 0; //integral_func(bottom);
        while (temp_point<top)
        {
            sum += integral_func(temp_point)*step;
            temp_point += step;
        }
        return sum;
    }
    
    @Override
    public double calculateIntegrandWithFunction(double bottom, double top) throws RemoteException
    {
        System.out.println("Making calculation...");
        double step = 0.01;
        double temp_point=bottom;
        double sum = 0; //integral_func(bottom);
        IntegralFunction func;
        try
        {
            func = (IntegralFunction)Naming.lookup("//localhost/function");
            while (temp_point<top)
            {
                sum += func.functionToIntegrand(temp_point)*step;
                temp_point += step;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sum;
    }
    
    private double integral_func(double arg)
    {
        //return Math.sin(arg);
        return 5*arg - 3;
    }
}
