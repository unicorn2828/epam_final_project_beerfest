package by.kononov.fest.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.kononov.fest.entity.Bar;
import by.kononov.fest.entity.Entity;

/**
 * The BarProducer class; it implements the singleton pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-08
 */
class BarProducer{
	private static final BarProducer INSTANCE = new BarProducer();
	private static final String PARTNER_ID = "bar_id";
	private static final String AVAILABLE_SEATS = "available_seats";
	private static final String BAR_DESCRIPTION = "bar_description";
	private static final String BAR_STATUS = "bar_status";
	private static final String BAR_NAME = "bar_name";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private BarProducer() {
	}

	/**
	 * This method returns the instance of class.
	 * 
	 * @return INSTANCE - the single instance of this class;
	 */
	static BarProducer getInstance() {
		return INSTANCE;
	}

	List<Entity> produceBar(ResultSet resultSet, List<Entity> barList) throws SQLException {
		while (resultSet.next()) {
			Bar bar = produceBar(resultSet);
			barList.add(bar);
		}
		return barList;
	}

	Bar produceBar(ResultSet resultSet) throws SQLException {
		long barId = resultSet.getLong(PARTNER_ID);
		int seats = resultSet.getInt(AVAILABLE_SEATS);
		String description = resultSet.getString(BAR_DESCRIPTION);
		Bar.BarStatus status = Bar.BarStatus.values()[resultSet.getInt(BAR_STATUS)];
		String name = resultSet.getString(BAR_NAME);
		return new Bar(barId, seats, description, status, name);
	}
}