package tests;

import static org.junit.Assert.assertEquals;
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

		List<Vehiculo> vp2 = new ArrayList<Vehiculo>();
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
			for (Vehiculo v : vs) {
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
	public void testAddPropietario_FailWhenKeyExist() {
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

		List<Propietario> obtenidosPrevious = pdao.getAllPropietarios();
		int indexPrevious = binarySearch(obtenidosPrevious, aniadir);

		boolean operacion = pdao.addPropietario(aniadir);

		assertTrue(indexPrevious >= 0 && !operacion);
	}

	@Test
	public void testAddPropietario_SuccessWhenKeyNotExist() {
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

		List<Propietario> obtenidosNotExist = pdao.getAllPropietarios();
		int indexNotExist = binarySearch(obtenidosNotExist, aniadir);

		boolean operacion = pdao.addPropietario(aniadir);

		List<Propietario> obtenidosExist = pdao.getAllPropietarios();
		int indexExist = binarySearch(obtenidosExist, aniadir);

		assertTrue(indexNotExist < 0 && operacion && indexExist >= 0);
		propietarios = (ArrayList<Propietario>) obtenidosExist;
	}

	@Test
	public void testDeletePropietario_SuccessWhenKeyExists() {
		Propietario p = new Propietario("12345678H");
		p.setNombre("fsafsa");
		p.setApellido("dfdsfs");

		List<Propietario> obtenidosExist = pdao.getAllPropietarios();
		int indexExist = binarySearch(obtenidosExist, p);

		boolean operacion = pdao.deletePropietario(p.getDni());

		List<Propietario> obtenidosNotExist = pdao.getAllPropietarios();
		int indexNotExist = binarySearch(obtenidosNotExist, p);

		assertTrue(indexExist >= 0 && operacion && indexNotExist < 0);

		propietarios = (ArrayList<Propietario>) obtenidosNotExist;
	}

	@Test
	public void testDeletePropietario_FailWhenKeyNotExists() {
		Propietario p = new Propietario("12345689L");
		p.setNombre("fsafsa");
		p.setApellido("dfdsfs");

		List<Propietario> obtenidosExist = pdao.getAllPropietarios();
		int indexExist = binarySearch(obtenidosExist, p);

		boolean operacion = pdao.deletePropietario(p.getDni());

		assertTrue(indexExist < 0 && !operacion);
	}

	@Test
	public void testUpdatePropietario_SuccessWhenKeyExist() {
		Propietario update = new Propietario();
		update.setDni("23456789H");
		update.setNombre("Lauri");
		update.setApellido("Lacaustra");

		List<Propietario> obtenidos = pdao.getAllPropietarios();
		int index = binarySearch(obtenidos, update);

		boolean operacion = pdao.updatePropietario(update);

		assertTrue(index >= 0 && operacion);

		propietarios = (ArrayList<Propietario>) obtenidos;
	}

	public void testUpdatePropietario_FailWhenKeyNotExist() {
		Propietario update = new Propietario();
		update.setDni("23456789H");
		update.setNombre("Lauri");
		update.setApellido("Lacaustra");

		List<Propietario> obtenidos = pdao.getAllPropietarios();
		int index = binarySearch(obtenidos, update);

		boolean operacion = pdao.updatePropietario(update);

		assertTrue(index < 0 && !operacion);

		propietarios = (ArrayList<Propietario>) obtenidos;
	}

	@Test
	public void getVehiculosPropietario() {
		Propietario p = propietarios.get(0);
		List<Vehiculo> vs = pdao.getVehiculosPropietario(p);

		for (Vehiculo vehiculo : vs) {
			int index = binarySearch(p.getVehiculos(), vehiculo);
			assertTrue(index >= 0);
		}
	}

	public <T extends Comparable<T>> int binarySearch(List<T> list, T target) {
		Collections.sort(list);
		int init = 0;
		int end = list.size() - 1;
		int pos = -1;

		while (init <= end && pos < 0) {
			int med = init + (end - init) / 2;
			if (list.get(med).compareTo(target) == 0) {
				pos = med;
			} else if (list.get(med).compareTo(target) == -1) {
				init = med + 1;
			} else {
				end = med - 1;
			}
		}
		return pos;
	}
}
