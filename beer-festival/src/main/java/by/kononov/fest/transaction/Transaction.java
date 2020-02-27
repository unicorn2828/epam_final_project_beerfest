package by.kononov.fest.transaction;

import by.kononov.fest.repository.Repository;

/**
 * The Transaction interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-20
 */
public interface Transaction{

	/**
	 * This method is used to begin a transaction.
	 *
	 * @param repositories - array of repositories involved in the
	 *                     transaction;
	 */
	void beginTransaction(Repository... repositories);

	/**
	 * This method is used to commit a transaction.
	 */
	void commit();

	/**
	 * This method is used to complete a transaction.
	 */
	void endTransaction();

	/**
	 * This method is used to rollback a transaction.
	 */
	void rollback();
}