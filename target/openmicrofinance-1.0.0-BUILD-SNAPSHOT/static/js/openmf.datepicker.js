$(document).ready(function (){
		var dateToday = new Date();
		$(".date-picker").datepicker({format: "dd/mm/yyyy", minDate: dateToday});
		$("#dateofbirth").datepicker({format: "dd/mm/yyyy"}); //TODO do not allow future date for dob
});