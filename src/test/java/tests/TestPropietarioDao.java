	package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.PropietarioDAO;
import pojo.Propietario;

public class TestPropietarioDao {

	static PropietarioDAO pdao;
	static Propietario p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new Propietario();
		p.setDni("12345678H");
		p.setNombre("fsafsa");
		p.setApellido("dfdsfs");
		pdao = new PropietarioDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllPropietarios() {
		assertNotNull(pdao.getAllPropietario());
	}

	@Test
	public void testDeletePropietario() {
	}

	@Test
	public void testAddPropietario() {
	}

	@Test
	public void testUpdatePropietario() {
		assertTrue(pdao.updatePropietario(p));
	}

}
