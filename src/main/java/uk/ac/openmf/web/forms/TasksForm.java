package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class TasksForm {
	
    private String taskId;
    private String description;
    private String assignedto;
    private String dateassigned;
    private boolean status;
    private String amount;
    private String cashier;
    private String accountnumber;
    private String collectiontype;
    private boolean newclient;
    private String datecompleted;
    
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignedto() {
		return assignedto;
	}
	public void setAssignedto(String assignedto) {
		this.assignedto = assignedto;
	}
	public String getDateassigned() {
		return dateassigned;
	}
	public void setDateassigned(String dateassigned) {
		this.dateassigned = dateassigned;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getCollectiontype() {
		return collectiontype;
	}
	public void setCollectiontype(String collectiontype) {
		this.collectiontype = collectiontype;
	}
	public boolean isNewclient() {
		return newclient;
	}
	public void setNewclient(boolean newclient) {
		this.newclient = newclient;
	}
	public String getDatecompleted() {
		return datecompleted;
	}
	public void setDatecompleted(String datecompleted) {
		this.datecompleted = datecompleted;
	}
}
