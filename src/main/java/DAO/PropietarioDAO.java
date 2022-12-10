package DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import IDAO.IPropietarioDAO;
import pojo.Propietario;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class PropietarioDAO implements IPropietarioDAO {
	static Logger logger = Logger.getLogger(VehiculoDAO.class);

	// Método para añadir un propietario en la BBDD
	public boolean addPropietario(Propietario p) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		logger.info("[PropietarioDao] [updateVehiculo] – buscando si hay propietario con dni: " + p.getDni());
		boolean notFound = false;
		if (p.getDni() != null) {
			Propietario propietario = getPropietarioByDni(p.getDni());
			if (propietario == null) {
				notFound = true;
			}
		}
		if (notFound) {
			session.beginTransaction();
			logger.info("[PropietarioDao] [addPropietario] – Agregando Vehiculos Propietario con dni \"" + p.getDni() + "\"");
			for (Vehiculo v : p.getVehiculos()) {
				session.save(v.getPropietario());
				session.save(v);
			}
			session.getTransaction().commit();
			session.close();
			logger.info("[PropietarioDao] [addPropietario] – Agregando Propietario con dni \"" + p.getDni() + "\"");
			updatePropietario(p);
			resul = true;
		} else {
			logger.debug("[PropietarioDao] [addPropietario] – NO agregado (existe en bbdd) Propietario con dni \""
					+ p.getDni() + "\"");
		}
		return resul;
	}

	// Método para actualizar un propietario en la BBDD
	public boolean updatePropietario(Propietario p) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();

		logger.info("[PropietarioDao] [updateVehiculo] – buscando si hay propietario con dni: " + p.getDni());
		boolean found = true;
		if (p.getDni() != null) {
			Propietario propietario = getPropietarioByDni(p.getDni());
			if (propietario == null) {
				found = false;
			}
		}
		if (found) {
			logger.info("[PropietarioDao] [updatePropietario] – Update Propietario con dni \"" + p.getDni() + "\"");
			session.update(p);
			session.beginTransaction().commit();
			resul = true;
		} else {
			logger.debug("[PropietarioDao] [updatePropietario] – NO Update(falta en bbdd) Propietario con dni \""
					+ p.getDni() + "\"");
		}
		session.close();
		return resul;
	}

	// Método para eliminar un propietario en la BBDD
	public boolean deletePropietario(String dni) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		logger.info("[PropietarioDao] [deletePropietario] – buscando si hay propietario con dni: " + dni);
		boolean found = true;
		if (dni != null) {
			Propietario propietario = getPropietarioByDni(dni);
			if (propietario == null) {
				found = false;
			}
		}
		
		if (found) {
			logger.info("[PropietarioDao] [deletePropietario] – delete Propietario con dni \"" + dni + "\"");
			session.delete(new Propietario(dni));
			session.beginTransaction().commit();
			resul = true;
		} else {
			logger.info("[PropietarioDao] [deletePropietario] – No delete (falta el propietario) Propietario con dni \"" + dni + "\"");
		}
		session.close();
		return resul;
	}

	// Método para obtener todos los propietarios en la BBDD
	public List<Propietario> getAllPropietarios() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		List<Propietario> vs = session.createQuery("From Propietario").list();
		return vs;
	}

	public List<Vehiculo> getVehiculosPropietario(Propietario p) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		List<Vehiculo> vs = session
				.createQuery("FROM Vehiculo v WHERE v.propietario.dni = :dni", Vehiculo.class)
				.setParameter("dni", p.getDni()).list();
		return vs;
	}

	// Método para obetener de la BBDD un propietario con un dni específico
	private Propietario getPropietarioByDni(String dni) {
		Propietario p = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		p = session.get(Propietario.class, dni);
		session.beginTransaction().commit();
		session.close();
		return p;
	}
}
