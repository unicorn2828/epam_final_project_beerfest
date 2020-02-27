package test.kononov.fest.factory;

import static org.testng.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Entity.EntityType;
import by.kononov.fest.factory.EntityFactory;

public class EntityFactoryTest{
	List<Entity> actual;
	List<Entity> expected;

	@Mock
	private ResultSet resultSet;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		actual = new ArrayList<>();
		expected = new ArrayList<>();
	}

	@AfterClass
	public void tierDown() {
		resultSet = null;
		actual = null;
		expected = null;
	}

	@Test(description = "entity factory test")
	public void produceBarTest() throws SQLException {
		EntityType type = EntityType.BAR;
		actual = EntityFactory.getInstance().produce(resultSet, actual, type);
		assertEquals(actual, expected);
	}

	@Test(description = "entity factory test")
	public void produceOrderTest() throws SQLException {
		EntityType type = EntityType.ORDER;
		actual = EntityFactory.getInstance().produce(resultSet, actual, type);
		assertEquals(actual, expected);
	}

	@Test(description = "entity factory test")
	public void producePartnerTest() throws SQLException {
		EntityType type = EntityType.PARTNER;
		actual = EntityFactory.getInstance().produce(resultSet, actual, type);
		assertEquals(actual, expected);
	}

	@Test(description = "entity factory test")
	public void produceUserTest() throws SQLException {
		EntityType type = EntityType.USER;
		actual = EntityFactory.getInstance().produce(resultSet, actual, type);
		assertEquals(actual, expected);
	}
}