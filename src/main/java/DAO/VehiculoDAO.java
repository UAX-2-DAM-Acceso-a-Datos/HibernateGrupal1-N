package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IVehiculoDAO;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class VehiculoDAO implements IVehiculoDAO{

	public void addVehiculo(Vehiculo v) {
			
		Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(v);
        session.getTransaction().commit();
        
        session.close();
	        
			
	}

	public void updateVehiculo(Vehiculo v) {
			
		Session session = HibernateUtils.getSessionFactory().openSession();
        Vehiculo mod = getVehiculoByMatr(v.getMatricula());
        mod.setMarca(v.getMarca());
        mod.setModelo(v.getModelo());
        mod.setPropietario(v.getPropietario());

        session.update(mod);
        session.beginTransaction().commit();

        session.close();
			
	}

	public void deleteVehiculo(String Matricula) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo deleted = new Vehiculo(Matricula);
		
		session.delete(deleted);

		session.beginTransaction().commit();
		session.close();
	}

	public List<Vehiculo> getAllVehiculos() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		List<Vehiculo> vs = session.createQuery("From vehiculos").list();
		for (Vehiculo v : vs) {
			System.out.println(v);
		}
		session.close();
		return vs;
	}
	
	private Vehiculo getVehiculoByMatr(String matricula) {
        Vehiculo v = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        v = session.get(Vehiculo.class, matricula);

        session.close();
        return v;
    } 
	
}
