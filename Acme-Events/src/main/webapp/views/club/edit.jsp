<%--
 * action-1.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="clubManagerForm">

	<form:hidden path="id" />
	<form:hidden path="manager" />
	
	

	<acme:textbox code="club.name" path="name"/>
	<acme:textbox code="club.address" path="address"/>
	<acme:textbox code="club.description" path="description"/>
	<acme:textbox code="club.pictures" path="pictures"/>
	
	<form:label path="draftMode">
		<spring:message code="club.draftMode" />
	</form:label>
	<form:checkbox path="draftMode" />
	<form:errors path="draftMode" cssClass="error" />
	<br />
	
	
	<jstl:if test="${isRead == false}">
		<acme:submit name="save" code="club.save" />
		<jstl:if test="${id != 0}">
			<acme:delete confirmDelete="club.confirm.delete" name="delete"
				code="club.delete" />

		</jstl:if>
		<acme:cancel url="club/list.do" code="club.cancel" />
	</jstl:if>


	<jstl:if test="${isRead == true}">
		<acme:cancel url="club/list.do" code="club.cancel" />

	</jstl:if>
</form:form>
