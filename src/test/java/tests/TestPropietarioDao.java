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
import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestPropietarioDao {

	static PropietarioDAO pdao;
	static ArrayList<Propietario> propietarios;
	static ArrayList<Vehiculo> vp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		propietarios = new ArrayList<Propietario>();
		Propietario propietario = new Propietario();
		Propietario propietario2 = new Propietario();
		Vehiculo vehiculo = new Vehiculo();
		Vehiculo vehiculo2 = new Vehiculo();
		Vehiculo vehiculo3 = new Vehiculo();

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

		vehiculo3.setMatricula("1111AAA");
		vehiculo3.setMarca("Kia");
		vehiculo3.setModelo("Ceed");
		vehiculo3.setPropietario(propietario2);

		ArrayList<Vehiculo> vp1 = new ArrayList<Vehiculo>();
		vp1.add(vehiculo);
		propietario.setVehiculos(vp1);

		List<Vehiculo>vp2 = new ArrayList<Vehiculo>();
		vp2.add(vehiculo2);
		vp2.add(vehiculo3);
		propietario2.setVehiculos(vp2);

		propietarios.add(propietario);
		propietarios.add(propietario2);

		vp = (ArrayList<Vehiculo>) vp2;
	
		pdao = new PropietarioDAO();
		for (Propietario p : propietarios) {
			pdao.addPropietario(p);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		VehiculoDAO vdao = new VehiculoDAO();
		List<Propietario> props = pdao.getAllPropietarios();
		for (Propietario p : props) {
			pdao.deletePropietario(p.getDni());
			List<Vehiculo> vs = p.getVehiculos();
			for (Vehiculo v: vs) {
				vdao.deleteVehiculo(v.getMatricula());
			}
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
		Propietario aniadir = new Propietario();
		aniadir.setDni("34567890H");
		aniadir.setNombre("Lauri");
		aniadir.setApellido("Lacostra");

		vehiculo.setMatricula("0100BAA");
		vehiculo.setMarca("Audi");
		vehiculo.setModelo("A5");
		vehiculo.setPropietario(aniadir);

		ArrayList<Vehiculo> vp1 = new ArrayList<Vehiculo>();
		vp1.add(vehiculo);
		aniadir.setVehiculos(vp1);

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
		Propietario p = propietarios.get(0);
		List<Vehiculo> vs = pdao.getVehiculosPropietario(p);
		
		assertEquals(vp.size(), vs.size());
		for (int i = 0; i < p.getVehiculos().size(); i++) {
			assertEquals(p.getVehiculos().get(i).getMatricula(), vp.get(i).getMatricula());
		}
	}
}
