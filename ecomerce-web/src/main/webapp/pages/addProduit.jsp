<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="s" uri="/struts-tags"%>




<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ taglib prefix="sjdt" uri="/struts-jquery-datatables-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
<sj:head jquerytheme="redmond" />

</head>
<body>
	<div align="center">
		<h1>Gestion des produits</h1>
		<h2>Ajout d'un produit</h2>

		<s:form action="addProduit" method="post">

			<div class="type-text">
				<s:textfield label="nom produit" name="produit.nom" />
			</div>
			<div class="type-text">
				<s:textfield label="Prix " name="produit.prix" />
			</div>
			<sj:submit value="Save produit" />

		</s:form>

	</div>
</body>
</html>