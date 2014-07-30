package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class LoanDisburseForm {
	
	private String clientId;
	private String loanaccountid;
	private String disbursedon;
	private String transactionreference;
	private String transactionnote;
	private String postedby;
	private String disbursedamount;
	private boolean status;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getLoanaccountid() {
		return loanaccountid;
	}
	public void setLoanaccountid(String loanaccountid) {
		this.loanaccountid = loanaccountid;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDisbursedon() {
		return disbursedon;
	}
	public void setDisbursedon(String disbursedon) {
		this.disbursedon = disbursedon;
	}
	public String getDisbursedamount() {
		return disbursedamount;
	}
	public void setDisbursedamount(String disbursedamount) {
		this.disbursedamount = disbursedamount;
	}
}
