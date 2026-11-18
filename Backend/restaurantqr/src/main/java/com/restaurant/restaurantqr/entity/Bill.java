package com.restaurant.restaurantqr.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long billId;
	private BigDecimal subtotal;
	private BigDecimal tax;
	private BigDecimal totalAmount;
	private LocalDateTime billTime;
	private String paymentStatus;
	@OneToOne
	@JoinColumn(name="session_id")
	private TableSession tableSession;
	public Bill() {

	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId=billId;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal=subtotal;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax=tax;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount=totalAmount;
	}
	public LocalDateTime getBillTime() {
		return billTime;
	}
	public void setBillTime(LocalDateTime billTime) {
		this.billTime=billTime;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus=paymentStatus;
	}
	public TableSession getTableSession() {
		return tableSession;
	}
	public void setTableSession(TableSession tableSession) {
		this.tableSession=tableSession;
	}

}
