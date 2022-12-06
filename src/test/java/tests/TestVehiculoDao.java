package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
		for (Vehiculo v : vehiculos) {
			pdao.deletePropietario(v.getPropietario().getDni());
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
	public void testGetAllVehiculos_success() {
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		Collections.sort(obtenidos);
		assertEquals(vehiculos.size(), obtenidos.size());
		for (int i = 0; i < obtenidos.size(); i++) {
			assertEquals(vehiculos.get(i).getMatricula(), obtenidos.get(i).getMatricula());
		}
	}

	@Test
	public void testDeleteVehiculo_success() {

		Propietario propietario = new Propietario();
		propietario.setDni("12345678B");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");

		Vehiculo v = new Vehiculo("0000AAA");
		v.setMarca("Toyota");
		v.setModelo("3080ti");
		v.setPropietario(propietario);

		assertTrue(vdao.deleteVehiculo(v.getMatricula()));

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		assertEquals(vehiculos.size() - 1, obtenidos.size());
		int i;
		boolean deleted = true;
		for (i = 0; i < obtenidos.size() && deleted; i++) {
			if ("0000AAA".compareToIgnoreCase(obtenidos.get(i).getMatricula()) != 0) {
				assertNotEquals("0000AAA", obtenidos.get(i).getMatricula());
			} else {
				assertEquals(i, obtenidos.size() - 1);
				deleted = false;
			}
		}
		if (deleted) {
			vehiculos = (ArrayList<Vehiculo>) obtenidos;
		}
	}

	@Test
	public void testAddVehiculo_success() {
		Vehiculo vehiculo = new Vehiculo();
		Propietario aniadir = new Propietario();
		aniadir.setDni("34567890H");
		aniadir.setNombre("Lauri");
		aniadir.setApellido("Lacostra");

		vehiculo.setMatricula("0100BAA");
		vehiculo.setMarca("Audi");
		vehiculo.setModelo("A5");
		vehiculo.setPropietario(aniadir);

		vehiculos.add(vehiculo);

		assertTrue(vdao.addVehiculo(vehiculo));

		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		assertEquals(vehiculos.size(), obtenidos.size());
		boolean found = false;
		for (int i = 0; i < obtenidos.size() && !found; i++) {
			if (vehiculo.getMatricula().compareToIgnoreCase(obtenidos.get(i).getMatricula()) == 0) {
				assertEquals(vehiculo.getMatricula(), obtenidos.get(i).getMatricula());
				found = true;
			} else {
				assertNotEquals(i, obtenidos.size() - 1);
			}
		}
		if (found) {
			vehiculos = (ArrayList<Vehiculo>) obtenidos;
		}
	}

	@Test
	public void testUpdateVehiculo_success() {
		Propietario prop = new Propietario();
		Vehiculo update = new Vehiculo();
		prop.setDni("34534590J");
		prop.setNombre("SIWI");
		prop.setApellido("Hernán");
		update.setMarca("nissan");
		update.setMatricula("0000AAA");
		update.setModelo("350z");
		update.setPropietario(prop);

		assertTrue(vdao.updateVehiculo(update));

		boolean updated = false;
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		assertEquals(vehiculos.size(), obtenidos.size());
		for (int i = 0; i < obtenidos.size() && !updated; i++) {
			if (update.getMatricula().compareToIgnoreCase(obtenidos.get(i).getMatricula()) == 0) {
				assertEquals(update.getMatricula(), obtenidos.get(i).getMatricula());
				updated = true;
			} else {
				assertNotEquals(i, obtenidos.size() - 1);
			}
		}
		if (updated) {
			vehiculos = (ArrayList<Vehiculo>) obtenidos;
		}
	}
}
