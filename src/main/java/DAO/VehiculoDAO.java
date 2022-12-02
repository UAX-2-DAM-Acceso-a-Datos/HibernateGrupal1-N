package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IVehiculoDAO;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class VehiculoDAO implements IVehiculoDAO {

	public boolean addVehiculo(Vehiculo v) {
		boolean resul=false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		if (v.getMatricula() != null) {
			session.beginTransaction();
			session.save(v.getPropietario());
			session.save(v);
			session.getTransaction().commit();
			resul=true;
		}
		session.close();
		return resul;
	}

	public boolean updateVehiculo(Vehiculo v) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo mod = getVehiculoByMatr(v.getMatricula());
		if (mod != null) {
			mod.setMarca(v.getMarca());
			mod.setModelo(v.getModelo());
			mod.setPropietario(v.getPropietario());

			session.update(mod);
			session.beginTransaction().commit();
			resul=true;
		}
		session.close();
		return resul;
	}

	public boolean deleteVehiculo(String Matricula) {
		boolean resul=false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo deleted = getVehiculoByMatr(Matricula);
		if (deleted != null) {
			session.delete(deleted);
			session.beginTransaction().commit();
			resul=true;
		}
		session.close();
		return resul;
	}

	public List<Vehiculo> getAllVehiculos() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Vehiculo> vs =  (List<Vehiculo>) session.createQuery("from Vehiculo").getResultList();
		session.beginTransaction().commit();
		session.close();
		return vs;
	}

	private Vehiculo getVehiculoByMatr(String matricula) {
		Vehiculo v = new Vehiculo (matricula);
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		v = session.get(Vehiculo.class, matricula);
		
		session.close();
		return v;
	}

}

