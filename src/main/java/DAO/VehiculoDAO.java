package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IVehiculoDAO;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class VehiculoDAO implements IVehiculoDAO{

	public void addVehiculo(Vehiculo v) {
		
	}

	public void updateVehiculo(Vehiculo v) {
		
	}

	public void deleteVehiculo(String Matricula) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo deleted = new Vehiculo(Matricula);
		
		session.delete(deleted);

		session.beginTransaction().commit();
		session.close();
	}

	public List<Vehiculo> getAllVehiculos() {

	}
	
}
