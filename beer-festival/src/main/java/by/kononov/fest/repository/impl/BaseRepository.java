package by.kononov.fest.repository.impl;

import java.sql.Connection;

import by.kononov.fest.repository.Repository;

public abstract class BaseRepository implements Repository{
	protected Connection connection;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}