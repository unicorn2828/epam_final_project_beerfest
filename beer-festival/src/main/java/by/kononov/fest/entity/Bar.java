package by.kononov.fest.entity;

/**
 * The Bar class; it represents Bar; it extends the Entity class.
 * <p>
 * Please see the {@link by.kononov.fest.entity.Entity} class for true
 * identity.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class Bar extends Entity{
	private static final long serialVersionUID = 1L;

	/**
	 * This is an inner class; it is enum of bar statuses.
	 */
	public enum BarStatus {
		AVAILABLE, OCCUPIED
	}

	private long barId;
	private int availableSeats;
	private String barDescription;
	/**
	 * Represents the bar’s status.
	 */
	private BarStatus barStatus;
	private String barName;

	/**
	 * Class constructor; it creates a new Bar with initial values.
	 */
	public Bar(long barId, int seats, String description, BarStatus status, String name) {
		this.barId = barId;
		this.availableSeats = seats;
		this.barDescription = description;
		this.barStatus = status;
		this.barName = name;
	}

	public long getBarId() {
		return barId;
	}

	public void setBarId(long barId) {
		this.barId = barId;
	}

	public int getSeats() {
		return availableSeats;
	}

	public void setSeats(int seats) {
		this.availableSeats = seats;
	}

	public String getDescription() {
		return barDescription;
	}

	public void setDescription(String description) {
		this.barDescription = description;
	}

	public BarStatus getStatus() {
		return barStatus;
	}

	public void setStatus(BarStatus status) {
		this.barStatus = status;
	}

	public String getName() {
		return barName;
	}

	public void setName(String name) {
		this.barName = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + (barId ^ (barId >>> 32)));
		result = prime * result + ((barDescription == null) ? 0 : barDescription.hashCode());
		result = prime * result + ((barName == null) ? 0 : barName.hashCode());
		result = prime * result + availableSeats;
		result = prime * result + ((barStatus == null) ? 0 : barStatus.hashCode());
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
		Bar other = (Bar) obj;
		if (barId != other.barId) {
			return false;
		}
		if (barDescription == null) {
			if (other.barDescription != null)
				return false;
		} else if (!barDescription.equals(other.barDescription)) {
			return false;
		}
		if (barName == null) {
			if (other.barName != null)
				return false;
		} else if (!barName.equals(other.barName)) {
			return false;
		}
		if (availableSeats != other.availableSeats || barStatus != other.barStatus) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bar [barId=");
		builder.append(barId);
		builder.append(", availableSeats=");
		builder.append(availableSeats);
		builder.append(", description=");
		builder.append(barDescription);
		builder.append(", status=");
		builder.append(barStatus);
		builder.append(", name=");
		builder.append(barName);
		builder.append("]");
		return builder.toString();
	}
}