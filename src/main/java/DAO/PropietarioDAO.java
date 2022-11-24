package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IPropietarioDAO;
import pojo.Propietario;
import pojo.Vehiculo;
import utils.HibernateUtils;

public class PropietarioDAO implements IPropietarioDAO {

	// Método para añadir un propietario en la BBDD
	public boolean addPropietario(Propietario p) {
        boolean resul=false;
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (p.getDni() != null) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            resul=true;
        }
        session.close();
        return resul;
    }

	// Método para actualizar un propietario en la BBDD
	public boolean updatePropietario(Propietario p) {
		boolean resul = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Propietario mod = getPropietarioByDni(p.getDni());
		if (mod != null) {
			mod.setNombre(p.getNombre());
			mod.setApellido(p.getApellido());

			session.update(mod);
			session.beginTransaction().commit();
			resul=true;
		}
		session.close();
		return resul;
	}

	// Método para eliminar un propietario en la BBDD
	public boolean deletePropietario(String dni) {
		boolean resul=false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Propietario deleted = getPropietarioByDni(dni);
		if (deleted != null) {
			session.delete(deleted);
			session.beginTransaction().commit();
			resul=true;
		}
		session.close();
		return resul;
	}

	// Método para obtener todos los propietarios en la BBDD
	public List<Propietario> getAllPropietario() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		List<Propietario> vs = session.createQuery("From Propietario").list();
		for (Propietario p : vs) {
			System.out.println(p);
		}
		session.close();
		return vs;

	}
	
	// Método para obetener de la BBDD un propietario con un dni específico
	private Propietario getPropietarioByDni(String dni) {
		Propietario p = null; // se crea un objeto propietario null
		Session session = HibernateUtils.getSessionFactory().openSession(); // se abre la conexión
		session.beginTransaction(); // se ejecuta la query para obtener resultados
		p = session.get(Propietario.class, dni); // metemos el valor resultado filtrando por el dni en un obeto
		session.close(); // cerramos la conexión
		return p; // devolvemos el objeto
	}
}
