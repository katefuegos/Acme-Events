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

<form:form action="${requestURI}" modelAttribute="opinionForm">

	<acme:textbox code="opinion.title" path="title" />
	<acme:textarea code="opinion.description" path="description" />
	<acme:textbox code="opinion.score" path="score" />

	 <form:label path="event">
		<spring:message code="opinion.event" />:
	</form:label>
	<form:select id="events" path="event" readonly="${isRead }">
		<form:options items="${events}" itemValue="id" itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="event" /> 



<br>

		<acme:submit name="save" code="opinion.save" />

		<acme:cancel url="opinion/client/list.do" code="opinion.cancel" />

</form:form>
