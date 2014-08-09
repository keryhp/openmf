function goback(){
	"use strict";
	window.history.back();
};

function viewClientFn(clientId){
	"use strict";
	window.location.href = "/viewclient?clientId=" + clientId;
};

function viewClientsRepFn(clientId){
	"use strict";
	window.location.href = "/reports/clientsrep?clientId=" + clientId;
};

function viewChartOfAccountsFn(coaId){
	"use strict";
	window.location.href = "/finance/viewcoa?coaId=" + coaId;
};

function viewGroupFn(groupId){
	"use strict";
	window.location.href = "/viewgroup?groupId=" + groupId;
};

function viewUserDetailsFn(omfuId){
	"use strict";
	window.location.href = "/viewuser?omfuId=" + omfuId;
};

function viewLoanDetailsFn(lpId){
	"use strict";
	window.location.href = "/loanproductdetails?lpId=" + lpId;
};

function viewSavingsDetailsFn(spId){
	"use strict";
	window.location.href = "/savingsproductdetails?spId=" + spId;
};

function viewLoanAccountFn(lnaccId){
	"use strict";
	window.location.href = "/viewloanaccount?lnaccId=" + lnaccId;
};

function viewSavingsAccountFn(sgaccId){
	"use strict";
	window.location.href = "/viewsavingsaccount?sgaccId=" + sgaccId;
};

function createLoanAccountFn(clientaccountid){
	"use strict";
	window.location.href = "/createloanaccount?clientId=" + clientaccountid;
};

function createGroupLoanAccountsFn(groupId){
	"use strict";
	window.location.href = "/createloanaccount?groupId=" + groupId;
};

function createSavingsAccountFn(clientaccountid){
	"use strict";
	window.location.href = "/createsavingsaccount?clientId=" + clientaccountid;
};

function viewRolesFn(){
	"use strict";
	window.location.href = "/viewroles";
};

function onFileSelected() {
	"use strict";
	var filename = document.getElementById("input-file").value;
	if (filename == null || filename == "") {
		document.getElementById("btn-post").setAttribute("class",
		"inactive btn");
		document.getElementById("btn-post").disabled = true;
	} else {
		document.getElementById("btn-post").setAttribute("class",
		"active btn");
		document.getElementById("btn-post").disabled = false;
	}
};

function togglePhotoPost(expanded) {
	"use strict";
	onFileSelected();
	if (expanded) {
		document.getElementById("btn-choose-image").style.display = "none";
		document.getElementById("upload-form").style.display = "block";
	} else {
		document.getElementById("btn-choose-image").style.display = "inline-block";
		document.getElementById("upload-form").style.display = "none";
	}
};

function deleteUser(idVal){
	"use strict";
	confirm("Delete user ?");
};

function updateLoanAmount(sel){
	"use strict";
	//var value = sel.options[sel.selectedIndex].value;
	var value = document.getElementById("lavalues").options[sel.selectedIndex].value;
	document.getElementById("availloanamount").textContent = value;
	return false;
};

function showClosedTable(idVal){
	"use strict";
	document.getElementById(idVal).className = "table table-condensed";
};

function loanRepayment(lnaccId){
	"use strict";
	window.location.href = "/loanactualpayment?lnaccId=" + lnaccId;
};

function loanDisburse(lnaccId){
	"use strict";
	window.location.href = "/loandisburse?lnaccId=" + lnaccId;
};

/*function loanPenalty(val){
	"use strict";
	window.location.href = "/loanpenalty?lnaccId=" + lnaccId;
};*/

function savingsDeposit(sgaccId){
	"use strict";
	window.location.href = "/savingsdeposit?sgaccId=" + sgaccId;
};

function savingsWithdrawal(sgaccId){
	"use strict";
	window.location.href = "/savingswithdrawal?sgaccId=" + sgaccId;
};

function showOrHideGroup(sel){
	"use strict";
	var value = document.getElementById("clienttype").options[sel.selectedIndex].value;
	if(value == "individual")
		document.getElementById("groupSelect").className = "col-sm-3 hide";
	else if(value == "group")
		document.getElementById("groupSelect").className = "col-sm-3 show";
};

function assignGroupFn(clientId){
	"use strict";
	window.location.href = "/assigngroup?clientId=" + clientId;
};

function formvalidate(){
	"use strict";
	var f = document.getElementsByTagName('form')[0];
	if(f.checkValidity()) {
		f.submit();
	} else {
		alert("Please provide proper input values");
	}
};

function exportFromHTML(clientId){
	"use strict";
	window.location.href = "/reports/clientspdf?clientId=" + clientId;
};