package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class GeneralLedgerForm {
	private String generalledgerid;
	private String coaid;
	private String transactiontype;
	private String clientaccountid;
	private String postedby;
	private String approvedby;
	private boolean status;
	private String balanceavailable;
	private String balancePending;
	private String forDate;
	
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
	public String getGeneralledgerid() {
		return generalledgerid;
	}
	public void setGeneralledgerid(String generalledgerid) {
		this.generalledgerid = generalledgerid;
	}
	public String getBalanceavailable() {
		return balanceavailable;
	}
	public void setBalanceavailable(String balanceavailable) {
		this.balanceavailable = balanceavailable;
	}
	public String getBalancePending() {
		return balancePending;
	}
	public void setBalancePending(String balancePending) {
		this.balancePending = balancePending;
	}
	public String getForDate() {
		return forDate;
	}
	public void setForDate(String forDate) {
		this.forDate = forDate;
	}
}
