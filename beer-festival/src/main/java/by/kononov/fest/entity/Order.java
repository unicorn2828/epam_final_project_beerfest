package by.kononov.fest.entity;

/**
 * The Order class; it represents Order; it extends the Entity class.
 * <p>
 * Please see the {@link by.kononov.fest.entity.Entity} class for true
 * identity.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class Order extends Entity{
	private static final long serialVersionUID = 1L;

	private long orderId;
	/**
	 * Represents the order’s bar.
	 */
	private Bar bar;
	private int seats;
	private String comment;

	/**
	 * Class constructor; it creates a new Order with initial values.
	 */
	public Order(long orderId, Bar bar, int seats, String comment) {
		this.orderId = orderId;
		this.bar = bar;
		this.seats = seats;
		this.comment = comment;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bar == null) ? 0 : bar.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = (int) (prime * result + (orderId ^ (orderId >>> 32)));
		result = prime * result + seats;
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
		Order other = (Order) obj;
		if (bar == null) {
			if (other.bar != null)
				return false;
		} else if (!bar.equals(other.bar)) {
			return false;
		}
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (orderId != other.orderId || seats != other.seats) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", bar=");
		builder.append(bar);
		builder.append(", seats=");
		builder.append(seats);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}
}