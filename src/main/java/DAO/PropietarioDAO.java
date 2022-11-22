package DAO;

import java.util.List;

import org.hibernate.Session;

import IDAO.IPropietarioDAO;
import pojo.Propietario;
import utils.HibernateUtils;

public class PropietarioDAO implements IPropietarioDAO {

	// Método para añadir un propietario en la BBDD
	public boolean addPropietario(Propietario p) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Propietario existente = getPropietarioByDni(p.getDni());
		if (existente == null) {
			session.save(p); // declaramos la query de inserción que mete el objeto que recibe (p)
			session.beginTransaction().commit(); // se ejecuta la query y se guardan los cambios
			success = true;
		}
		session.close();
		return success;
	}

	// Método para actualizar un propietario en la BBDD
	public boolean updatePropietario(Propietario p) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession(); // abrimos la conexión
		Propietario updated = getPropietarioByDni(p.getDni()); // creamos un objeto Propietario vacio
		if(updated != null) {
			updated.setDni(p.getDni()); // Le metemos los valores del objeto anterior para que se actualice con un nuevo
			updated.setNombre(p.getNombre());
			updated.setApellido(p.getApellido());
			session.update(p); // declaramos la query de actualización que mete el nuevo objeto
			session.beginTransaction().commit();
			success = true;
		}
		session.close();
		return success;
	}

	// Método para eliminar un propietario en la BBDD
	public boolean deletePropietario(String dni) {
		boolean success = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Propietario deleted = getPropietarioByDni(dni);
		
		if(deleted != null) {
			session.delete(deleted);
			session.beginTransaction().commit();
			success = true;
		}
		session.close();
		return success;
	}

	// Método para obtener todos los propietarios en la BBDD
	public List<Propietario> getAllPropietario() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		List<Propietario> vs = session.createQuery("From vehiculos").list();
		for (Propietario v : vs) {
			System.out.println(v);
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
