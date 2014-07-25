function goback(){
	window.history.back();
};

function viewClientFn(clientId){
	window.location.href = "/viewclient.htm?clientId=" + clientId;
};

function viewUserDetailsFn(omfuId){
	window.location.href = "/viewuser.htm?omfuId=" + omfuId;
};

function viewLoanDetailsFn(lpId){
	window.location.href = "/loanproductdetails.htm?lpId=" + lpId;
};

function viewSavingsDetailsFn(spId){
	window.location.href = "/savingsproductdetails.htm?spId=" + spId;
};

function viewLoanAccountFn(){
	window.location.href = "/viewloanaccount.htm";
};

function viewRolesFn(){
	window.location.href = "/viewroles.htm";
};

function onFileSelected() {
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
};

function changePwd(){
	//toggle show or hide
};