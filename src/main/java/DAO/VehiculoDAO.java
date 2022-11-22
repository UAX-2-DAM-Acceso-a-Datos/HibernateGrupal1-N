package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IVehiculoDAO;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class VehiculoDAO implements IVehiculoDAO {

	public boolean addVehiculo(Vehiculo v) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo existente = getVehiculoByMatr(v.getMatricula());
		if (v.getMatricula() != null) {
			session.beginTransaction();
			session.save(v);
			session.save(v.getPropietario());
			session.getTransaction().commit();
			success = true;
		}
		session.close();
		return success;
	}

	public boolean updateVehiculo(Vehiculo v) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo mod = getVehiculoByMatr(v.getMatricula());
		if (mod != null) {
			mod.setMarca(v.getMarca());
			mod.setModelo(v.getModelo());
			mod.setPropietario(v.getPropietario());

			session.update(mod);
			session.beginTransaction().commit();
			success = true;
		}
		session.close();
		return success;
	}

	public boolean deleteVehiculo(String Matricula) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo deleted = getVehiculoByMatr(Matricula);
		if (deleted != null) {
			session.delete(deleted);
			session.beginTransaction().commit();
			success = true;
		}
		session.close();
		return success;
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
