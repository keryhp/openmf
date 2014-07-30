package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class SavingsDepositForm {
	
	private String clientId;
	private String savingsaccountid;
	private String dateofpayment;
	private String transactionreference;
	private String transactionnote;
	private String postedby;
	private String amountpaid;
	private boolean status;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSavingsaccountid() {
		return savingsaccountid;
	}
	public void setSavingsaccountid(String loanaccountid) {
		this.savingsaccountid = loanaccountid;
	}
	public String getDateofpayment() {
		return dateofpayment;
	}
	public void setDateofpayment(String dateofpayment) {
		this.dateofpayment = dateofpayment;
	}
	public String getTransactionreference() {
		return transactionreference;
	}
	public void setTransactionreference(String transactionreference) {
		this.transactionreference = transactionreference;
	}
	public String getTransactionnote() {
		return transactionnote;
	}
	public void setTransactionnote(String transactionnote) {
		this.transactionnote = transactionnote;
	}
	public String getPostedby() {
		return postedby;
	}
	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}
	public String getAmountpaid() {
		return amountpaid;
	}
	public void setAmountpaid(String amountpaid) {
		this.amountpaid = amountpaid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
