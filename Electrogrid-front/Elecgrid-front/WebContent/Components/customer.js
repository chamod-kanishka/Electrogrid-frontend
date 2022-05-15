/**
 * 
 */
 
 $(document).ready(function() 
{ 
	if ($("#alertSuccess").text().trim() == "") 
	 { 
	 	$("#alertSuccess").hide(); 
	 } 
	 $("#alertError").hide(); 
});

// SAVE ============================================
	$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide();
 
 // Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
 
 // If valid------------------------
var type = ($("#hidecustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
	 url : "customerAPI", 
	 type : type, 
	 data : $("#formCustomer").serialize(), 
	 dataType : "text", 
	 
 complete : function(response, status) 
 { 
 	onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});  


function onCustomerSaveComplete(response, status)
{ 
if (status == "success") 
 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show(); 
	 $("#divEmpGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
 } else
 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
 } 
 
	 $("#hidecustomerIDSave").val(""); 
	 $("#formCustomer")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidecustomerIDSave").val($(this).closest("tr").find('#customerUpdate').val()); 
 $("#cus_name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#cus_mobile").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#cus_email").val($(this).closest("tr").find('td:eq(2)').text()); 
  
}); 

  // CLIENT-MODEL================================================================
	function validateCustomerForm() 
	{ 
		// NAME
		if ($("#cus_name").val().trim() == "") 
 		{ 
			 return "Insert Customer name."; 
 		} 
		// MOBILE
			if ($("#cus_mobile").val().trim() == "") 
			 { 
 				return "Insert Customer mobile."; 
 			} 
 			
 			// is numerical value
			var cus_mobile = $("#cus_mobile").val().trim(); 
			if (!$.isNumeric(cus_mobile)) 
	 		{ 
	 			return "Insert a numerical value for mobile."; 
	 		} 
 			
		// DESIGNATION
			if ($("#designation").val().trim() == "") 
			 { 
 				return "Insert mail."; 
 			} 
 			return true;
		}
		
	//DELETE
	$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "customerAPI", 
 type : "DELETE", 
 data : "cus_id=" + $(this).data("cus_id"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeDeleteComplete(response.responseText, status); 
 } 
 }); 
});	
		
function onEmployeeDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
 }


