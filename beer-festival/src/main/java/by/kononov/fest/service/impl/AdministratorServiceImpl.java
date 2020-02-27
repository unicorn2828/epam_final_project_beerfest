package by.kononov.fest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.repository.Repository;
import by.kononov.fest.repository.impl.BarRepository;
import by.kononov.fest.repository.impl.OrderRepository;
import by.kononov.fest.repository.impl.PartnerRepository;
import by.kononov.fest.repository.impl.UserRepository;
import by.kononov.fest.service.AdministratorService;
import by.kononov.fest.specification.FestSpecification;
import by.kononov.fest.specification.impl.ReceivingAllBarsSpecification;
import by.kononov.fest.specification.impl.ReceivingAllOrdersSpecification;
import by.kononov.fest.specification.impl.ReceivingAllPartnersSpecification;
import by.kononov.fest.specification.impl.ReceivingAllUsersSpecification;
import by.kononov.fest.specification.impl.ReceivingAmountAllUsersSpecification;
import by.kononov.fest.specification.impl.ReceivingUserByIdSpecification;
import by.kononov.fest.transaction.Transaction;
import by.kononov.fest.transaction.impl.TransactionImpl;
import by.kononov.fest.validator.RequestContentDataValidator;

/**
 * The AdministratorServiceImpl class; it implements the
 * AdministratorService interface.
 * <p>
 * {@link by.kononov.fest.service.AdministratorService}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class AdministratorServiceImpl implements AdministratorService{
	static final Logger logger = LogManager.getLogger();
	private static final int PAGINATION_LIMIT = 5;
	private static final int DEFAULT_PAGE_NUMBER = 0;
	private Transaction transaction;
	private Repository barRepository;
	private Repository userRepository;
	private Repository orderRepository;
	private Repository partnerRepository;

	public AdministratorServiceImpl() {
		transaction = new TransactionImpl();
		barRepository = BarRepository.getInstance();
		userRepository = UserRepository.getInstance();
		orderRepository = OrderRepository.getInstance();
		partnerRepository = PartnerRepository.getInstance();
	}

	@Override
	public List<Entity> receiveBar() throws ServiceException {
		List<Entity> list = new ArrayList<>();
		FestSpecification specification = new ReceivingAllBarsSpecification();
		try {
			list = barRepository.query(specification);
		} catch (RepositoryException e) {
			throw new ServiceException("query to repository failed ", e);
		}
		return list;
	}

	@Override
	public List<Entity> receiveUser(String pageNumber) throws ServiceException {
		long page;
		if (pageNumber != null) {
			page = Long.parseLong(pageNumber);
		} else {
			page = DEFAULT_PAGE_NUMBER;
		}
		try {
			long offset = page * PAGINATION_LIMIT;
			FestSpecification specification = new ReceivingAllUsersSpecification(String.valueOf(offset));
			return userRepository.query(specification);
		} catch (RepositoryException e) {
			logger.error("Cannot find users list", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int countPages() throws ServiceException {
		int count = countUsers();
		int pages = count / PAGINATION_LIMIT;
		if (count % PAGINATION_LIMIT > 0) {
			pages++;
		}
		return pages;
	}

	@Override
	public List<Entity> receivePartner() throws ServiceException {
		List<Entity> list = new ArrayList<>();
		FestSpecification specification = new ReceivingAllPartnersSpecification();
		try {
			list = partnerRepository.query(specification);
		} catch (RepositoryException e) {
			throw new ServiceException("query to repository failed ", e);
		}
		return list;
	}

	@Override
	public List<Entity> receiveOrder() throws ServiceException {
		List<Entity> list = new ArrayList<>();
		FestSpecification specification = new ReceivingAllOrdersSpecification();
		try {
			list = orderRepository.query(specification);
		} catch (RepositoryException e) {
			throw new ServiceException("query to repository failed ", e);
		}
		return list;
	}

	@Override
	public void blockUnblockUser(Optional<String> optional) throws ServiceException {
		User currentUser = null;
		if (RequestContentDataValidator.isDataExist(optional)) {
			String userId = optional.get();
			FestSpecification specification = new ReceivingUserByIdSpecification(userId);
			transaction.beginTransaction(userRepository);
			try {
				List<Entity> list = userRepository.query(specification);
				currentUser = (User) list.get(0);
				if (User.UserStatus.VALID.equals(currentUser.getStatus())) {
					currentUser.setStatus(User.UserStatus.BLOCK);
					userRepository.updateEntity(currentUser);
					transaction.commit();
				} else {
					currentUser.setStatus(User.UserStatus.VALID);
					userRepository.updateEntity(currentUser);
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

	private int countUsers() throws ServiceException {
		List<Entity> list = new ArrayList<>();
		try {
			FestSpecification specification = new ReceivingAmountAllUsersSpecification();
			list = userRepository.query(specification);
			return list.size();
		} catch (RepositoryException e) {
			logger.error("can't count users", e);
			throw new ServiceException(e);
		}
	}
}