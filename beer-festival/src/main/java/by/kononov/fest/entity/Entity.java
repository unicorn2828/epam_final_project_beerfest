package by.kononov.fest.entity;

import java.io.Serializable;

/**
 * The Entity class; it is abstract class; it implements the Cloneable
 * and Serializable interfaces.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public abstract class Entity implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * This is an inner class; it is enum of entity types.
	 */
	public enum EntityType {
		BAR, ORDER, PARTNER, USER
	}
}