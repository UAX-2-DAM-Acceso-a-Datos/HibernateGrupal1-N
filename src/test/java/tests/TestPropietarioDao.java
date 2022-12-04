package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.PropietarioDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestPropietarioDao {

	static PropietarioDAO pdao;
	static ArrayList<Propietario> propietarios;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		propietarios = new ArrayList<Propietario>();
		Propietario propietario = new Propietario();
		Propietario propietario2 = new Propietario();
		Vehiculo vehiculo = new Vehiculo();
		Vehiculo vehiculo2 = new Vehiculo();
		Vehiculo vehiculo3 = new Vehiculo();

		vehiculo.setMatricula("0000AAA");
		vehiculo.setMarca("Toyota");
		vehiculo.setModelo("3080ti");

		vehiculo2.setMatricula("0000BBB");
		vehiculo2.setMarca("Audi");
		vehiculo2.setModelo("Q3");

		vehiculo3.setMatricula("1111AAA");
		vehiculo3.setMarca("Kia");
		vehiculo3.setModelo("Ceed");

		propietario.setDni("12345678H");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");

		propietario2.setDni("23456789H");
		propietario2.setNombre("Lauri");
		propietario2.setApellido("Lacaustra");

		ArrayList<Vehiculo> vp1 = new ArrayList<Vehiculo>();
		vp1.add(vehiculo);
		propietario.setVehiculo(vp1);

		ArrayList<Vehiculo> vp2 = new ArrayList<Vehiculo>();
		vp2.add(vehiculo2);
		vp2.add(vehiculo3);
		propietario.setVehiculo(vp2);

		propietarios.add(propietario);
		propietarios.add(propietario2);

		pdao = new PropietarioDAO();
		for (Propietario p : propietarios) {
			pdao.addPropietario(p);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		List<Propietario> props = pdao.getAllPropietarios();
		for (Propietario p : props) {
			pdao.deletePropietario(p.getDni());
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllPropietarios() {
		List<Propietario> obtenidos = pdao.getAllPropietarios();
		assertEquals(propietarios.size(), obtenidos.size());
		for (int i = 0; i < obtenidos.size(); i++) {
			assertEquals(propietarios.get(i).getDni(), obtenidos.get(i).getDni());
		}
	}

	@Test
	public void testAddPropietario() {
		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setMatricula("0100BAA");
		vehiculo.setMarca("Audi");
		vehiculo.setModelo("A5");

		Propietario aniadir = new Propietario();
		aniadir.setDni("34567890H");
		aniadir.setNombre("Lauri");
		aniadir.setApellido("Lacostra");

		ArrayList<Vehiculo> vp1 = new ArrayList<Vehiculo>();
		vp1.add(vehiculo);
		aniadir.setVehiculo(vp1);

		propietarios.add(aniadir);

		assertTrue(pdao.addPropietario(aniadir));

		List<Propietario> obtenidos = pdao.getAllPropietarios();
		assertEquals(propietarios.size(), obtenidos.size());
		boolean found = false;
		for (int i = 0; i < obtenidos.size() && !found; i++) {
			if (aniadir.getDni().compareToIgnoreCase(obtenidos.get(i).getDni()) == 0) {
				assertEquals(aniadir.getDni(), obtenidos.get(i).getDni());
				found = true;
			} else {
				assertNotEquals(i, obtenidos.size() - 1);
			}
		}
		if (found) {
			propietarios = (ArrayList<Propietario>) obtenidos;
		}
	}

	@Test
	public void testDeletePropietario() {
		Propietario p = new Propietario("12345678H");
		p.setNombre("fsafsa");
		p.setApellido("dfdsfs");

		assertTrue(pdao.deletePropietario(p.getDni()));

		List<Propietario> obtenidos = pdao.getAllPropietarios();
		assertEquals(propietarios.size() - 1, obtenidos.size());
		int i;
		boolean deleted = true;
		for (i = 0; i < obtenidos.size() && deleted; i++) {
			if ("12345678H".compareToIgnoreCase(obtenidos.get(i).getDni()) != 0) {
				assertNotEquals("12345678H", obtenidos.get(i).getDni());
			} else {
				assertEquals(i, obtenidos.size() - 1);
				deleted = false;
			}
		}
		if (deleted) {
			propietarios = (ArrayList<Propietario>) obtenidos;
		}
	}

	@Test
	public void testUpdatePropietario() {
		Propietario update = new Propietario();
		update.setDni("34567890H");
		update.setNombre("Lauri");
		update.setApellido("Lacoste");

		assertTrue(pdao.updatePropietario(update));

		boolean updated = false;
		List<Propietario> obtenidos = pdao.getAllPropietarios();
		assertEquals(propietarios.size(), obtenidos.size());
		for (int i = 0; i < obtenidos.size() && !updated; i++) {
			if (update.getDni().compareToIgnoreCase(obtenidos.get(i).getDni()) == 0) {
				assertEquals(update.getDni(), obtenidos.get(i).getDni());
				updated = true;
			} else {
				assertNotEquals(i, obtenidos.size() - 1);
			}
		}
		if (updated) {
			propietarios = (ArrayList<Propietario>) obtenidos;
		}
	}
	
	@Test
	public void getVehiculosPropietario() {
		
	}
}
