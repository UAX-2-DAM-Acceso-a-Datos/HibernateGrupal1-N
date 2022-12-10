package DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import IDAO.IVehiculoDAO;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class VehiculoDAO implements IVehiculoDAO {
	static Logger logger = Logger.getLogger(VehiculoDAO.class);

	public boolean addVehiculo(Vehiculo v) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		logger.info("[VehiculoDao] [addVehiculo] – buscando vehiculo con matricula: " + v.getMatricula());
		boolean notFound = false;
		if (v.getMatricula() != null) {
			Vehiculo vehiculo = getVehiculoByMatr(v.getMatricula());
			if (vehiculo == null) {
				notFound = true;
			}
		}
		if (notFound) {
			logger.info("[VehiculoDao] [addVehiculo] – No hay ningun vehiculo con matricula: " + v.getMatricula());
			session.beginTransaction();
			session.save(v.getPropietario());
			session.save(v);
			session.getTransaction().commit();
			resul = true;
//			logger.debug("[CLASE] [método] – Lista" + e.getMessage());
		} else {
			logger.info("[VehiculoDao] [addVehiculo] – Hay un vehiculo con matricula: " + v.getMatricula());
		}
		session.close();
		return resul;
	}

	public boolean updateVehiculo(Vehiculo v) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();

		logger.info("[VehiculoDao] [updateVehiculo] – buscando si hay vehiculo con matricula: " + v.getMatricula());
		boolean found = true;
		if (v.getMatricula() != null) {
			Vehiculo vehiculo = getVehiculoByMatr(v.getMatricula());
			if (vehiculo == null) {
				found = false;
			}
		}
		if (found) {
			logger.info("[VehiculoDao] [updateVehiculo] – vehiculo con matricula \"" + v.getMatricula()
					+ "\" esta en bbdd");
			session.update(v);
			session.beginTransaction().commit();
			resul = true;
		} else {
			logger.debug("[VehiculoDao] [updateVehiculo] – vehiculo con matricula \"" + v.getMatricula()
					+ "\" NO esta en bbdd");
		}
		session.close();
		return resul;
	}

	public boolean deleteVehiculo(String matricula) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Vehiculo deleted = null;

		logger.info("[VehiculoDao] [deleteVehiculo] – buscando si hay vehiculo con matricula: " + matricula);
		boolean found = false;
		if (matricula != null) {
			deleted = getVehiculoByMatr(matricula);
			if (deleted != null) {
				found = true;
			}
		}
		if (found) {
			logger.info("[VehiculoDao] [deleteVehiculo] – encontrado vehiculo con matricula: " + matricula);
			session.delete(deleted);
			session.beginTransaction().commit();
			resul = true;
		} else {
			logger.info("[VehiculoDao] [updateVehiculo] – vehiculo con matricula \"" + matricula + "\" esta en bbdd");
		}
		session.close();
		return resul;
	}

	public List<Vehiculo> getAllVehiculos() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Vehiculo> vs = (List<Vehiculo>) session.createQuery("from Vehiculo").getResultList();
		session.beginTransaction().commit();
		session.close();
		return vs;
	}

	private Vehiculo getVehiculoByMatr(String matricula) {
		Vehiculo v = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		v = session.get(Vehiculo.class, matricula);
		session.beginTransaction().commit();
		session.close();
		return v;
	}

}
