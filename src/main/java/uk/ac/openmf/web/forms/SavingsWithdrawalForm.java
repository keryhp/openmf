package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class SavingsWithdrawalForm {
	
	private String clientId;
	private String savingsaccountid;
	private String dateofwithdrawal;
	private String transactionreference;
	private String transactionnote;
	private String postedby;
	private String withdrawalamount;
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
	public String getDateofwithdrawal() {
		return dateofwithdrawal;
	}
	public void setDateofwithdrawal(String dateofwithdrawal) {
		this.dateofwithdrawal = dateofwithdrawal;
	}
	public String getWithdrawalamount() {
		return withdrawalamount;
	}
	public void setWithdrawalamount(String withdrawalamount) {
		this.withdrawalamount = withdrawalamount;
	}
}
