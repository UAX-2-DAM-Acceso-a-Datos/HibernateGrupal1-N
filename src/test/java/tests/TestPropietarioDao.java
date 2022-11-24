package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.PropietarioDAO;
import pojo.Propietario;

public class TestPropietarioDao {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Propietario p = new Propietario();
		PropietarioDAO pdao = new PropietarioDAO();
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
	}

	@Test
	public void testDeletePropietario() {
	}

	@Test
	public void testAddPropietario() {
	}

	@Test
	public void testUpdatePropietario() {

	}

}
