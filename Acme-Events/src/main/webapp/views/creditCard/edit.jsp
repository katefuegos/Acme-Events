<%--
 * edit.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>


<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<form:form action="${requestURI}" modelAttribute="creditCardForm">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textbox code="creditcard.holderName" path="holderName" />
	<acme:textbox code="creditcard.brandName" path="brandName" />
	<acme:textbox code="creditcard.number" path="number" />
	<acme:textbox code="creditcard.expirationMonth" path="expirationMonth" />
	<acme:textbox code="creditcard.expirationYear" path="expirationYear" />
	<acme:textbox code="creditcard.CVVCode" path="CVVCode" />


	<jstl:if test="${isRead == false}">
		<br />
		<input type="submit" name="save"
			value='<spring:message code="creditcard.save"/>'>

		<jstl:if test="${creditCardForm.id != 0}">
			<input type="submit" name="delete"
				value='<spring:message code="box.delete"/>'>
		</jstl:if>
		
		<acme:cancel url="welcome/index.do" code="creditcard.cancel" />
		<br />
	</jstl:if>

</form:form>

