package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Integrand extends Remote
{
    double calculateIntegrand(double top, double bottom) throws RemoteException;
    double calculateIntegrandWithFunction(double top, double bottom) throws RemoteException;
}
