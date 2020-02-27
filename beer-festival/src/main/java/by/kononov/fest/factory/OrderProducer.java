package by.kononov.fest.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.kononov.fest.entity.Bar;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Order;
import by.kononov.fest.entity.Entity.EntityType;

/**
 * The OrderProducer class; it implements the singleton pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-08
 */
class OrderProducer{
	private static final OrderProducer INSTANCE = new OrderProducer();
	private static final String ORDER_ID = "order_id";
	private static final String ORDER_SEATS = "order_seats";
	private static final String ORDER_COMMENT = "order_comment";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private OrderProducer() {
	}

	/**
	 * This method returns the instance of class.
	 * 
	 * @return INSTANCE - the single instance of this class
	 */
	static OrderProducer getInstance() {
		return INSTANCE;
	}

	List<Entity> produceOrder(ResultSet resultSet, List<Entity> barList) throws SQLException {
		while (resultSet.next()) {
			Order order = produceOrder(resultSet);
			barList.add(order);
		}
		return barList;
	}

	Order produceOrder(ResultSet resultSet) throws SQLException {
		long orderId = resultSet.getLong(ORDER_ID);
		int seats = resultSet.getInt(ORDER_SEATS);
		String comment = resultSet.getString(ORDER_COMMENT);
		Bar bar = (Bar) EntityFactory.getInstance().produce(resultSet, EntityType.BAR);
		return new Order(orderId, bar, seats, comment);
	}
}