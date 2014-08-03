package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class TransactionForm {
	
    private String transactionid;
    private String fromaccountid;
    private String toaccountid;
    private String clientId;
    private String dateoftransaction;
    private String productid;
    private boolean status;
    private String postedby;
    private String approvedby;
    private String transactiontype;
    
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getFromaccountid() {
		return fromaccountid;
	}
	public void setFromaccountid(String fromaccountid) {
		this.fromaccountid = fromaccountid;
	}
	public String getToaccountid() {
		return toaccountid;
	}
	public void setToaccountid(String toaccountid) {
		this.toaccountid = toaccountid;
	}
	public String getDateoftransaction() {
		return dateoftransaction;
	}
	public void setDateoftransaction(String dateoftransaction) {
		this.dateoftransaction = dateoftransaction;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
