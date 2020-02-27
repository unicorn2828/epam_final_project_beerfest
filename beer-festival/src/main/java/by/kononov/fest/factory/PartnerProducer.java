package by.kononov.fest.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Partner;

/**
 * The PartnerProducer class; it implements the singleton pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-08
 */
class PartnerProducer{
	private static final PartnerProducer INSTANCE = new PartnerProducer();
	private static final String PARTNER_ID = "partner_id";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String PARTNER_BAR = "partner_bar";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private PartnerProducer() {
	}

	/**
	 * This method returns the instance of class.
	 * 
	 * @return INSTANCE - the single instance of this class;
	 */
	static PartnerProducer getInstance() {
		return INSTANCE;
	}

	List<Entity> producePartner(ResultSet resultSet, List<Entity> partnerList) throws SQLException {
		Partner partner;
		while (resultSet.next()) {
			long partnerId = resultSet.getLong(PARTNER_ID);
			String name = resultSet.getString(NAME);
			String description = resultSet.getString(DESCRIPTION);
			int bar = resultSet.getInt(PARTNER_BAR);
			partner = new Partner(partnerId, name, description, bar);
			partnerList.add(partner);
		}
		return partnerList;
	}
}