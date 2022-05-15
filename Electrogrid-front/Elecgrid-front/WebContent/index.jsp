<%@page import="com.customerServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>

<meta charset="ISO-8859-1">
<title>Customer Management</title>
</head>
<body>

<div class="container"><div class="row"><div class="col-8">
 <h1 class="m-3">Customer Management</h1>
 <form id="formCustomer" name = "formCustomer">
 
  Customer name:
 <input id="cus_name" name="cus_name" type="text"
 class="form-control form-control-sm">
 <br> Customer mobile:
 <input id="cus_mobile" name="cus_mobile" type="text"
 class="form-control form-control-sm">
 <br> Email:
 <input id="cus_email" name="cus_email" type="text"
 class="form-control form-control-sm"><br>
 
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidecustomerIDSave"
 name="hidecustomerIDSave" value="">
 
 </form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>

<div id="divEmpGrid">
 <%
 customerServices cusObj = new customerServices();
  out.print(cusObj.viewCus());
 %>
</div>
</div> </div> </div>

</body>
</html>