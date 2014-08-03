package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class GeneralJournalForm {
	private String generaljournalid;
	private String coaid;
	private String transactiontype;
	private String clientaccountid;
	private String postedby;
	private String approvedby;
	private String transactionamount;
	private boolean status;
	
	public String getGeneraljournalid() {
		return generaljournalid;
	}
	public void setGeneraljournalid(String generaljournalid) {
		this.generaljournalid = generaljournalid;
	}
	public String getCoaid() {
		return coaid;
	}
	public void setCoaid(String coaid) {
		this.coaid = coaid;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getClientaccountid() {
		return clientaccountid;
	}
	public void setClientaccountid(String clientaccountid) {
		this.clientaccountid = clientaccountid;
	}
	public String getPostedby() {
		return postedby;
	}
	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(String transactionamount) {
		this.transactionamount = transactionamount;
	}
}
