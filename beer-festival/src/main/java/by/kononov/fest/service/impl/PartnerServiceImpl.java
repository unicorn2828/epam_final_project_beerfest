package by.kononov.fest.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.kononov.fest.entity.Bar;
import by.kononov.fest.entity.Bar.BarStatus;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Order;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.repository.Repository;
import by.kononov.fest.repository.impl.BarRepository;
import by.kononov.fest.repository.impl.OrderRepository;
import by.kononov.fest.repository.impl.PartnerRepository;
import by.kononov.fest.repository.impl.UserRepository;
import by.kononov.fest.service.PartnerService;
import by.kononov.fest.specification.FestSpecification;
import by.kononov.fest.specification.impl.ReceivingBarByIdSpecification;
import by.kononov.fest.specification.impl.ReceivingPartnerByNameSpecification;
import by.kononov.fest.transaction.Transaction;
import by.kononov.fest.transaction.impl.TransactionImpl;

/**
 * The PartnerServiceImpl class; it implements the PartnerService
 * interface.
 * <p>
 * {@link by.kononov.fest.service.PartnerService}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class PartnerServiceImpl implements PartnerService{
	private static final String DEFAULT = "default";
	private Repository barRepository;
	private Repository partnerRepository;

	public PartnerServiceImpl() {
		barRepository = new BarRepository();
		partnerRepository =  new PartnerRepository();
	}

	@Override
	public boolean rentRegister(User user, Partner partner, String barName, String comment, String barId)
			throws ServiceException {
		boolean isValid = false;
		if (user.getOrder().getOrderId() == 0) {
			Order referenceOrder = user.getOrder();
			registerLogic(user, partner, barName, comment, barId);
			isValid = referenceOrder != user.getOrder();
		}
		return isValid;
	}

	@Override
	public User deleteRent(User user, Partner partner) throws ServiceException {
		Order order = user.getOrder();
		Bar bar = user.getOrder().getBar();
		bar.setName(DEFAULT);
		bar.setStatus(BarStatus.AVAILABLE);
		Bar defaultBar = new Bar(0, 0, DEFAULT, BarStatus.AVAILABLE, DEFAULT);
		Order defaultOrder = new Order(0, defaultBar, 0, DEFAULT);
		user.setOrder(defaultOrder);
		partner.setBar(defaultBar.getBarId());
		Transaction transaction = new TransactionImpl();
		PartnerRepository partnerRepositoryInTransaction = new PartnerRepository();
		BarRepository barRepositoryInTransaction = new BarRepository();
		OrderRepository orderRepositoryInTransaction = new OrderRepository();
		UserRepository userRepositoryInTransaction = new UserRepository();
		transaction.beginTransaction(
				partnerRepositoryInTransaction,
				barRepositoryInTransaction,
				orderRepositoryInTransaction,
				userRepositoryInTransaction);
		try {
			partnerRepositoryInTransaction.updateEntity(partner);
			barRepositoryInTransaction.updateEntity(bar);
			orderRepositoryInTransaction.deleteEntity(order);
			userRepositoryInTransaction.updateEntity(user);
			transaction.commit();
		} catch (RepositoryException e) {
			transaction.rollback();
			throw new ServiceException("query to repository failed ", e);
		} finally {
			transaction.endTransaction();
		}
		return user;
	}

	@Override
	public boolean checkRegistration(String name) throws ServiceException {
		List<Entity> list = new ArrayList<>();
		FestSpecification specification = new ReceivingPartnerByNameSpecification(name);
		try {
			list = partnerRepository.query(specification);
		} catch (RepositoryException e) {
			throw new ServiceException("query to repository failed ", e);
		}
		return list.isEmpty();
	}

	@Override
	public Partner registerPartner(long partnerId, String name, String description) throws ServiceException {
		int bar = 0;
		Partner partner = new Partner(partnerId, name, description, bar);
		Transaction transaction = new TransactionImpl();
		PartnerRepository partnerRepositoryInTransaction = new PartnerRepository();
		transaction.beginTransaction(partnerRepositoryInTransaction);
		try {
			partnerRepositoryInTransaction.addEntity(partner);
			transaction.commit();
		} catch (RepositoryException e) {
			transaction.rollback();
			throw new ServiceException("query to repository failed ", e);
		} finally {
			transaction.endTransaction();
		}
		return partner;
	}

	private void registerLogic(User user, Partner partner, String barName, String comment, String barId)
			throws ServiceException {
		Transaction transaction = new TransactionImpl();
		PartnerRepository partnerRepositoryInTransaction = new PartnerRepository();
		BarRepository barRepositoryInTransaction = new BarRepository();
		OrderRepository orderRepositoryInTransaction = new OrderRepository();
		UserRepository userRepositoryInTransaction = new UserRepository();
		transaction.beginTransaction(
				barRepositoryInTransaction,
				userRepositoryInTransaction,
				orderRepositoryInTransaction,
				partnerRepositoryInTransaction);
		FestSpecification specification = new ReceivingBarByIdSpecification(barId);
		try {
			List<Entity> barList = barRepository.query(specification);
			Bar currentBar = (Bar) barList.get(0);
			if (Bar.BarStatus.AVAILABLE.equals(currentBar.getStatus())) {
				currentBar.setName(barName);
				currentBar.setStatus(Bar.BarStatus.OCCUPIED);
				barRepositoryInTransaction.updateEntity(currentBar);
				Order order = new Order(user.getUserId(), currentBar, 0, comment);
				user.setOrder(order);
				orderRepositoryInTransaction.addEntity(order);
				partner.setBar(currentBar.getBarId());
				partnerRepositoryInTransaction.updateEntity(partner);
				userRepositoryInTransaction.updateEntity(user);
				transaction.commit();
			}
		} catch (RepositoryException e) {
			transaction.rollback();
			throw new ServiceException("query to repository failed ", e);
		} finally {
			transaction.endTransaction();
		}
	}
}
