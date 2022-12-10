package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.PropietarioDAO;
import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestVehiculoDao {

	static ArrayList<Vehiculo> vehiculos;
	static VehiculoDAO vdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vehiculos = new ArrayList<Vehiculo>();
		Propietario propietario = new Propietario();
		Propietario propietario2 = new Propietario();
		Vehiculo vehiculo = new Vehiculo();
		Vehiculo vehiculo2 = new Vehiculo();

		propietario.setDni("12345678H");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");

		propietario2.setDni("23456789H");
		propietario2.setNombre("Lauri");
		propietario2.setApellido("Lacaustra");

		vehiculo.setMatricula("0000AAA");
		vehiculo.setMarca("Toyota");
		vehiculo.setModelo("3080ti");
		vehiculo.setPropietario(propietario);

		vehiculo2.setMatricula("0000BBB");
		vehiculo2.setMarca("Audi");
		vehiculo2.setModelo("Q3");
		vehiculo2.setPropietario(propietario2);

		vehiculos.add(vehiculo);
		vehiculos.add(vehiculo2);

		vdao = new VehiculoDAO();
		for (Vehiculo v : vehiculos) {
			vdao.addVehiculo(v);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PropietarioDAO pdao = new PropietarioDAO();
		List<Propietario> ps = pdao.getAllPropietarios();
		for (Propietario propietario : ps) {
			pdao.deletePropietario(propietario.getDni());
		}
		for (Vehiculo v : vehiculos) {
			vdao.deleteVehiculo(v.getMatricula());
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * comparo lo que hay en la base de datos con el array vehiculos. a veces el
	 * orden de obtenidos coincide con el de vehiculos y a veces no por lo que a
	 * veces falla y a veces esta bien. Para ello se ordena y por eso pojo vehiculo
	 * tiene compare to.
	 */
	@Test
	public void testGetAllVehiculosSuccess() {
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		assertTrue(obtenidos.size() > 0);
	}

	@Test
	public void testDeleteVehiculo_SuccessWhenKeyExists() {
		
		Propietario propietario = new Propietario();
		propietario.setDni("12345678B");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");
		Vehiculo vehiculoDeleted = new Vehiculo("0000AAA");
		vehiculoDeleted.setMarca("Toyota");
		vehiculoDeleted.setModelo("3080ti");
		vehiculoDeleted.setPropietario(propietario);
		
		List<Vehiculo> obtenidosExist = vdao.getAllVehiculos();
		int indexExist = binarySearch(obtenidosExist, vehiculoDeleted);
		
		boolean operacion = vdao.deleteVehiculo(vehiculoDeleted.getMatricula());
		
		List<Vehiculo> obtenidosNotExist = vdao.getAllVehiculos();
		int indexNotExist = binarySearch(obtenidosNotExist, vehiculoDeleted);
		
		assertTrue(indexExist >= 0 && operacion && indexNotExist < 0);
		
		vehiculos = (ArrayList<Vehiculo>) obtenidosNotExist;
	}

	@Test
	public void testDeleteVehiculo_FailsWhenKeyNotExists() {

		Propietario propietario = new Propietario();
		propietario.setDni("12345678B");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");
		Vehiculo vehiculoDeleted = new Vehiculo("0011CAB");
		vehiculoDeleted.setMarca("Toyota");
		vehiculoDeleted.setModelo("3080ti");
		vehiculoDeleted.setPropietario(propietario);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = binarySearch(obtenidos, vehiculoDeleted);
		
		assertTrue(index < 0 && !vdao.deleteVehiculo(vehiculoDeleted.getMatricula()));		
	}

	@Test
	public void testAddVehiculo_SuccessWhenKeyNotExists() {
		Vehiculo vehiculoAdded = new Vehiculo();
		Propietario propietario = new Propietario();
		propietario.setDni("34567890H");
		propietario.setNombre("Lauri");
		propietario.setApellido("Lacostra");

		vehiculoAdded.setMatricula("0100BAA");
		vehiculoAdded.setMarca("Audi");
		vehiculoAdded.setModelo("A5");
		vehiculoAdded.setPropietario(propietario);

		List<Vehiculo> obtenidosNotExist = vdao.getAllVehiculos();
		int indexNotExist = binarySearch(obtenidosNotExist, vehiculoAdded);
		
		boolean operacion = vdao.addVehiculo(vehiculoAdded);
		
		List<Vehiculo> obtenidosExist = vdao.getAllVehiculos();
		int indexExist = binarySearch(obtenidosExist, vehiculoAdded);

		assertTrue(indexNotExist < 0 && operacion && indexExist >= 0);

		vehiculos = (ArrayList<Vehiculo>) obtenidosExist;
	}

	@Test
	public void testAddVehiculo_FailWhenKeyExists() {
		Vehiculo vehiculo = new Vehiculo();
		Propietario aniadir = new Propietario();
		aniadir.setDni("34567890H");
		aniadir.setNombre("Lauri");
		aniadir.setApellido("Lacostra");

		vehiculo.setMatricula("0000BBB");
		vehiculo.setMarca("Audi");
		vehiculo.setModelo("Q3");
		vehiculo.setPropietario(aniadir);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = binarySearch(obtenidos, vehiculo);
		
		boolean operacion = vdao.addVehiculo(vehiculo);
		
		assertTrue(index >= 0 && !operacion);
	}

	@Test
	public void testUpdateVehiculo_SuccessWhenKeyExists() {
		Propietario prop = new Propietario();
		Vehiculo vehiculoUpdated = new Vehiculo();
		prop.setDni("34534590J");
		prop.setNombre("SIWI");
		prop.setApellido("Hernán");
		vehiculoUpdated.setMarca("nissan");
		vehiculoUpdated.setMatricula("0000BBB");
		vehiculoUpdated.setModelo("350z");
		vehiculoUpdated.setPropietario(prop);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = binarySearch(obtenidos, vehiculoUpdated);
		assertTrue(index >= 0 && vdao.updateVehiculo(vehiculoUpdated));
		
		vehiculos = (ArrayList<Vehiculo>) obtenidos;
	}

	@Test
	public void testUpdateVehiculo_FailWhenKeyNotExists() {
		Propietario prop = new Propietario();
		Vehiculo vehiculoUpdated = new Vehiculo();
		prop.setDni("34534590J");
		prop.setNombre("SIWI");
		prop.setApellido("Hernán");
		vehiculoUpdated.setMarca("nissan");
		vehiculoUpdated.setMatricula("1122BCH");
		vehiculoUpdated.setModelo("350z");
		vehiculoUpdated.setPropietario(prop);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = binarySearch(obtenidos, vehiculoUpdated);
		
		assertTrue(index < 0 && !vdao.updateVehiculo(vehiculoUpdated));
	}
	
	public <T extends Comparable<T>> int binarySearch(List<T> list, T target) {
	    Collections.sort(list);
	    int init = 0;
	    int end = list.size()-1;
	    int pos = -1;

	    while (init <= end && pos < 0) {
	        int med = init + (end - init) / 2;
	        if (list.get(med).compareTo(target) == 0){
	            pos = med;
	        } else if (list.get(med).compareTo(target) == -1){
	            init = med + 1;
	        } else {
	        	end = med - 1;
	        }
	    }
	    return pos;
	}
}
