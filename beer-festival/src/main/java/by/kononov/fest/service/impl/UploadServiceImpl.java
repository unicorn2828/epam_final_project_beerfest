package by.kononov.fest.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.entity.User;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.repository.Repository;
import by.kononov.fest.repository.impl.UserRepository;
import by.kononov.fest.service.UploadService;
import by.kononov.fest.transaction.Transaction;
import by.kononov.fest.transaction.impl.TransactionImpl;

/**
 * The UploadServiceImpl class; it implements the UploadService
 * interface.
 * <p>
 * {@link by.kononov.fest.service.UploadService}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class UploadServiceImpl implements UploadService{
	static final Logger logger = LogManager.getLogger();
	private static final String UPLOAD_DIRECTORY = "images\\avatar\\";
	private Repository userRepository;
	private Transaction transaction;
	private String realPath;
	private Part part;

	public UploadServiceImpl(String realPath, Part part) {
		userRepository = UserRepository.getInstance();
		transaction = new TransactionImpl();
		this.realPath = realPath;
		this.part = part;
	}

	@Override
	public String receiveFileName(Part part) {
		return Paths.get(part.getSubmittedFileName()).getFileName().toString();
	}

	@Override
	public void updateUser(User user, String filePath) throws ServiceException {
		String fileName = receiveRandomFileName(filePath);
		user.setAvatarUrl(fileName);
		transaction.beginTransaction(userRepository);
		try {
			userRepository.updateEntity(user);
			transaction.commit();
		} catch (RepositoryException e) {
			transaction.rollback();
			throw new ServiceException("query to repository failed ", e);
		} finally {
			transaction.endTransaction();
		}
	}

	private String receiveDirectory() {
		String uploadFileDirectory = realPath;
		File fileSaveDirectory = new File(uploadFileDirectory);
		if (!fileSaveDirectory.exists()) {
			fileSaveDirectory.mkdirs();
		}
		return uploadFileDirectory;
	}

	private String receiveRandomFileName(String fileName) {
		String randomFileName = UPLOAD_DIRECTORY + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf('.'));
		try {
			part.write(receiveDirectory() + randomFileName);
		} catch (IOException e) {
			logger.error("write file failed ", e);
		}
		return randomFileName;
	}
}