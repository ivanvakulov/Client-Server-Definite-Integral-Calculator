package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract class IntegralFunction extends Remote, Serializable
{
    abstract double functionToIntegrand(double arg) throws RemoteException;
}
