package by.kononov.fest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.fest.entity.Bar;
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
import by.kononov.fest.security.Encription;
import by.kononov.fest.service.UserService;
import by.kononov.fest.specification.FestSpecification;
import by.kononov.fest.specification.impl.ReceivingBarByIdSpecification;
import by.kononov.fest.specification.impl.ReceivingPartnerByIdSpecification;
import by.kononov.fest.specification.impl.ReceivingUserByLoginSpecification;
import by.kononov.fest.transaction.Transaction;
import by.kononov.fest.transaction.impl.TransactionImpl;
import by.kononov.fest.validator.RequestContentDataValidator;
import by.kononov.fest.validator.UserDataValidator;

/**
 * The UserServiceImpl class; it implements the UserService interface.
 * <p>
 * {@link by.kononov.fest.service.UserService}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class UserServiceImpl implements UserService{
    private static final String AVATAR_URL = "images/avatar/default_avatar.png";
    private static final Pattern INT_REGEX = Pattern.compile("\\d{1,3}");
    private static final String DEFAULT = "default";
    private Repository partnerRepository;
    private Repository orderRepository;
    private Repository userRepository;
    private Repository barRepository;
    private User currentUser;

    public UserServiceImpl() {
        partnerRepository = new PartnerRepository();
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
        barRepository = new BarRepository();
    }

    @Override
    public boolean checkLogin(Optional<String> login, Optional<String> password) throws ServiceException {
        boolean isCorrectLogPass = false;
        if (RequestContentDataValidator.isDataExist(login, password) && validateLogin(login.get(), password.get())) {
            currentUser.setPassword(null);
            currentUser.setEmail(null);
            isCorrectLogPass = true;
        }
        return isCorrectLogPass;
    }

    @Override
    public Partner getPartner() throws ServiceException {
        String partnerId = String.valueOf(currentUser.getUserId());
        FestSpecification specification = new ReceivingPartnerByIdSpecification(partnerId);
        List<Entity> partner = null;
        try {
            partner = partnerRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException("query to repository failed ", e);
        }
        return (Partner) partner.get(0);
    }

    @Override
    public User deleteOrder(User user) throws ServiceException {
        Order order = user.getOrder();
        Bar bar = user.getOrder().getBar();
        int seats = bar.getSeats() + order.getSeats();
        bar.setSeats(seats);
        Bar defaultBar = new Bar(0, 0, DEFAULT, Bar.BarStatus.AVAILABLE, DEFAULT);
        Order defaultOrder = new Order(0, defaultBar, 0, DEFAULT);
        user.setOrder(defaultOrder);
        Transaction transaction = new TransactionImpl();
        BarRepository barRepositoryInTransaction = new BarRepository();
        OrderRepository orderRepositoryInTransaction = new OrderRepository();
        UserRepository userRepositoryInTransaction = new UserRepository();
        transaction.beginTransaction(barRepositoryInTransaction, orderRepositoryInTransaction, userRepositoryInTransaction);
        try {
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
    public boolean orderRegister(User user, Optional<String> optionalComment, Optional<String> optionalSeats,
                                 Optional<String> optionalBarId) throws ServiceException {
        boolean isValid = false;
        if (RequestContentDataValidator.isDataExist(optionalComment, optionalSeats, optionalBarId)) {
            String comment = optionalComment.get();
            String seats = optionalSeats.get();
            String barId = optionalBarId.get();
            Matcher matcher = INT_REGEX.matcher(seats);
            if (matcher.matches() && user.getOrder().getOrderId() == 0 && Integer.parseInt(seats) != 0) {
                int currentSets = Integer.parseInt(seats);
                Order referenceOrder = user.getOrder();
                registerLogic(user, currentSets, comment, barId);
                isValid = referenceOrder != user.getOrder();
            }
        }
        return isValid;
    }

    @Override
    public boolean register(Optional<String> optionalLogin, Optional<String> optionalPassword,
                            Optional<String> optionalEmail, Optional<String> optionalRole) throws ServiceException {
        boolean isRegister = false;
        if (RequestContentDataValidator.isDataExist(optionalLogin, optionalPassword, optionalEmail, optionalRole)) {
            String login = optionalLogin.get();
            String password = optionalPassword.get();
            String email = optionalEmail.get();
            String role = optionalRole.get();
            if (checkRegistration(login) && UserDataValidator.isLogin(login)
                    && UserDataValidator.isPassword(password)) {
                registerUser(login, password, email, role);
                isRegister = true;
            }
        }
        return isRegister;
    }

    @Override
    public User getUser() {
        return currentUser;
    }

    private boolean checkRegistration(String login) throws ServiceException {
        List<Entity> list = new ArrayList<>();
        FestSpecification specification = new ReceivingUserByLoginSpecification(login);
        try {
            list = userRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException("query to repository failed ", e);
        }
        return list.isEmpty();
    }

    private void registerLogic(User user, int seats, String comment, String barId) throws ServiceException {
        FestSpecification specification = new ReceivingBarByIdSpecification(barId);
        Transaction transaction = new TransactionImpl();
        BarRepository barRepositoryInTransaction = new BarRepository();
        OrderRepository orderRepositoryInTransaction = new OrderRepository();
        UserRepository userRepositoryInTransaction = new UserRepository();
        transaction.beginTransaction(barRepositoryInTransaction, orderRepositoryInTransaction, userRepositoryInTransaction);
        try {
            List<Entity> barList = barRepositoryInTransaction.query(specification);
            if (!barList.isEmpty()) {
                Bar currentBar = (Bar) barList.get(0);
                if (currentBar.getSeats() >= seats) {
                    int availableSeats = currentBar.getSeats() - seats;
                    currentBar.setSeats(availableSeats);
                    barRepositoryInTransaction.updateEntity(currentBar);
                    Order order = new Order(user.getUserId(), currentBar, seats, comment);
                    user.setOrder(order);
                    orderRepositoryInTransaction.addEntity(order);
                    userRepositoryInTransaction.updateEntity(user);
                    transaction.commit();
                }
            }
        } catch (RepositoryException e) {
            transaction.rollback();
            throw new ServiceException("query to repository failed ", e);
        } finally {
            transaction.endTransaction();
        }
    }

    private User registerUser(String login, String password, String email, String role) throws ServiceException {
        User.UserRole userRole = User.UserRole.valueOf(role.toUpperCase());
        User.UserStatus userStatus = User.UserStatus.VALID;
        Bar.BarStatus barStatus = Bar.BarStatus.OCCUPIED;
        Bar defaultBar = new Bar(0, 0, DEFAULT, barStatus, DEFAULT);
        Order defaultOrder = new Order(0, defaultBar, 0, DEFAULT);
        String defaultAvatarUrl = AVATAR_URL;
        currentUser = new User(0, login, password, email, userRole, userStatus, defaultOrder, defaultAvatarUrl);
        Transaction transaction = new TransactionImpl();
        UserRepository userRepositoryInTransaction = new UserRepository();
        transaction.beginTransaction(userRepositoryInTransaction);
        try {
            userRepositoryInTransaction.addEntity(currentUser);
            FestSpecification specification = new ReceivingUserByLoginSpecification(login);
            List<Entity> user = userRepositoryInTransaction.query(specification);
            long userId = ((User) user.get(0)).getUserId();
            currentUser.setUserId(userId);
            transaction.commit();
        } catch (RepositoryException e) {
            transaction.rollback();
            throw new ServiceException("query to repository failed ", e);
        } finally {
            transaction.endTransaction();
        }
        return currentUser;
    }

    private boolean validateLogin(String login, String password) throws ServiceException {
        if (!UserDataValidator.isLogin(login) || !UserDataValidator.isPassword(password)) {
            return false;
        }
        List<Entity> list = new ArrayList<>();
        try {
            password = Encription.encode(password);
            FestSpecification specification = new ReceivingUserByLoginSpecification(login);
            list = userRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException("query to repository failed ", e);
        }
        if (!list.isEmpty()) {
            currentUser = (User) list.get(0);
        }
        return currentUser != null && login.equals(currentUser.getLogin())
                && password.equals(currentUser.getPassword());
    }
}
