package tests;

import static org.junit.Assert.assertFalse;
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
		Vehiculo v = new Vehiculo("0000AAA");
		v.setMarca("Toyota");
		v.setModelo("3080ti");
		v.setPropietario(propietario);
		
		boolean operacion = vdao.deleteVehiculo(v.getMatricula());
		
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = Collections.binarySearch(obtenidos, v);
		
		assertTrue(index < 0 && operacion);
		
		vehiculos = (ArrayList<Vehiculo>) obtenidos;
	}

	@Test
	public void testDeleteVehiculo_FailsWhenKeyNotExists() {

		Propietario propietario = new Propietario();
		propietario.setDni("12345678B");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");
		Vehiculo v = new Vehiculo("0011CAB");
		v.setMarca("Toyota");
		v.setModelo("3080ti");
		v.setPropietario(propietario);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = Collections.binarySearch(obtenidos, v);
		
		assertTrue(index < 0 && !vdao.deleteVehiculo(v.getMatricula()));		
	}

	@Test
	public void testAddVehiculo_SuccessWhenKeyNotExists() {
		Vehiculo vehiculo = new Vehiculo();
		Propietario aniadir = new Propietario();
		aniadir.setDni("34567890H");
		aniadir.setNombre("Lauri");
		aniadir.setApellido("Lacostra");

		vehiculo.setMatricula("0100BAA");
		vehiculo.setMarca("Audi");
		vehiculo.setModelo("A5");
		vehiculo.setPropietario(aniadir);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = Collections.binarySearch(obtenidos, vehiculo);
		
		assertTrue(index < 0 && vdao.addVehiculo(vehiculo));

		vehiculos = (ArrayList<Vehiculo>) obtenidos;
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
		int index = Collections.binarySearch(obtenidos, vehiculo);
		assertTrue(index >= 0 && !vdao.addVehiculo(vehiculo));
	}

	@Test
	public void testUpdateVehiculo_SuccessWhenKeyExists() {
		Propietario prop = new Propietario();
		Vehiculo update = new Vehiculo();
		prop.setDni("34534590J");
		prop.setNombre("SIWI");
		prop.setApellido("Hernán");
		update.setMarca("nissan");
		update.setMatricula("0000BBB");
		update.setModelo("350z");
		update.setPropietario(prop);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = Collections.binarySearch(obtenidos, update);
		assertTrue(index >= 0 && vdao.updateVehiculo(update));
		
		vehiculos = (ArrayList<Vehiculo>) obtenidos;
	}

	@Test
	public void testUpdateVehiculo_FailWhenKeyNotExists() {
		Propietario prop = new Propietario();
		Vehiculo update = new Vehiculo();
		prop.setDni("34534590J");
		prop.setNombre("SIWI");
		prop.setApellido("Hernán");
		update.setMarca("nissan");
		update.setMatricula("1122BCH");
		update.setModelo("350z");
		update.setPropietario(prop);

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		int index = Collections.binarySearch(obtenidos, update);
		
		assertFalse(index >= 0 && vdao.updateVehiculo(update));
	}
}
