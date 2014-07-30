function goback(){
	"use strict";
	window.history.back();
};

function viewClientFn(clientId){
	"use strict";
	window.location.href = "/viewclient.htm?clientId=" + clientId;
};

function viewGroupFn(groupId){
	"use strict";
	window.location.href = "/viewgroup.htm?groupId=" + groupId;
};

function viewUserDetailsFn(omfuId){
	"use strict";
	window.location.href = "/viewuser.htm?omfuId=" + omfuId;
};

function viewLoanDetailsFn(lpId){
	"use strict";
	window.location.href = "/loanproductdetails.htm?lpId=" + lpId;
};

function viewSavingsDetailsFn(spId){
	"use strict";
	window.location.href = "/savingsproductdetails.htm?spId=" + spId;
};

function viewLoanAccountFn(lnaccId){
	"use strict";
	window.location.href = "/viewloanaccount.htm?lnaccId=" + lnaccId;
};

function viewSavingsAccountFn(sgaccId){
	"use strict";
	window.location.href = "/viewsavingsaccount.htm?sgaccId=" + sgaccId;
};

function createLoanAccountFn(clientaccountid){
	"use strict";
	window.location.href = "/createloanaccount.htm?clientId=" + clientaccountid;
};

function createGroupLoanAccountsFn(groupId){
	"use strict";
	window.location.href = "/createloanaccount.htm?groupId=" + groupId;
};

function createSavingsAccountFn(clientaccountid){
	"use strict";
	window.location.href = "/createsavingsaccount.htm?clientId=" + clientaccountid;
};

function viewRolesFn(){
	"use strict";
	window.location.href = "/viewroles.htm";
};

function onFileSelected() {
	"use strict";
	filename = document.getElementById("input-file").value;
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

function delUsr(){
	//toggle show or hide
	"use strict";
};

function changePwd(){
	//toggle show or hide
	"use strict";
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
	window.location.href = "/loanactualpayment.htm?lnaccId=" + lnaccId;
};

function loanDisburse(lnaccId){
	"use strict";
	window.location.href = "/loandisburse.htm?lnaccId=" + lnaccId;
};

/*function loanPenalty(val){
	"use strict";
	window.location.href = "/loanpenalty.htm?lnaccId=" + lnaccId;
};*/

function savingsDeposit(sgaccId){
	"use strict";
	window.location.href = "/savingsdeposit.htm?sgaccId=" + sgaccId;
};

function savingsWithdrawal(sgaccId){
	"use strict";
	window.location.href = "/savingswithdrawal.htm?sgaccId=" + sgaccId;
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
	window.location.href = "/assigngroup.htm?clientId=" + clientId;
};