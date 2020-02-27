package by.kononov.fest.entity;

/**
 * The Partner class; it represents Partner; it extends the Entity
 * class.
 * <p>
 * Please see the {@link by.kononov.fest.entity.Entity} class for true
 * identity.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class Partner extends Entity{
	private static final long serialVersionUID = 1L;

	private long partnerId;
	private String name;
	private String description;
	private long bar;

	/**
	 * Class constructor; it creates a new Partner with initial values.
	 */
	public Partner(long partnerId, String name, String description, long bar) {
		this.partnerId = partnerId;
		this.name = name;
		this.description = description;
		this.bar = bar;
	}

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getBar() {
		return bar;
	}

	public void setBar(long bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + bar);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = (int) (prime * result + (partnerId ^ (partnerId >>> 32)));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Partner other = (Partner) obj;
		if (bar != other.bar || partnerId != other.partnerId) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner [partnerId=");
		builder.append(partnerId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", bar=");
		builder.append(bar);
		builder.append("]");
		return builder.toString();
	}
}