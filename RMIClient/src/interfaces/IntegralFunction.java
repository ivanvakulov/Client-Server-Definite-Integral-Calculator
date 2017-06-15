package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntegralFunction extends Remote, Serializable
{
    double functionToIntegrand(double arg) throws RemoteException;
}
