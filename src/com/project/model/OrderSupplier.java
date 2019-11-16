package com.project.model;

public class OrderSupplier {
	private String orderId;
	private Float totalPrice;
	private String datePlaced;
	private String dateDelivered;
	private String status;
	private Float discount;
	private String supplierId;
	
	public OrderSupplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderSupplier(String orderId, Float totalPrice, String datePlaced, String dateDelivered, String status,
			Float discount, String supplierId) {
		super();
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.datePlaced = datePlaced;
		this.dateDelivered = dateDelivered;
		this.status = status;
		this.discount = discount;
		this.supplierId = supplierId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(String datePlaced) {
		this.datePlaced = datePlaced;
	}

	public String getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(String dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
