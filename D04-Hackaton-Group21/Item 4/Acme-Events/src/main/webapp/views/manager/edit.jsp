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

<img src='<jstl:out value="${actorForm.photo }"/>'  height="100" width="auto"> 
 
 
   <br><br>
<form:form action="${requestURI}" modelAttribute="actorForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />
	<form:hidden path="auth" />


	<acme:textbox code="actor.name" path="name" readonly="${isRead}"/>
	<acme:textbox code="actor.surnames" path="surname" readonly="${isRead}"/>
	<acme:textbox code="actor.middlename" path="middleName" readonly="${isRead}"/>

	<acme:textbox code="actor.photo" path="photo" readonly="${isRead}"/>
	<acme:textbox code="actor.email" path="email" readonly="${isRead}"/>
	<form:label path="phone">
		<spring:message code="actor.phone" />
	</form:label>
	<form:input path="phone" id="tlf" readonly="${isRead}" />
	<form:errors path="phone" cssClass="error" />
	<br />
	<acme:textbox code="actor.address" path="address" readonly="${isRead}"/>


	<jstl:if
		test="${actorForm.auth != 'CLIENT'}">
		<form:hidden path="DNI" />
	</jstl:if>

	<jstl:if test="${actorForm.auth == 'CLIENT' }">
		<acme:textbox code="actor.dni" path="DNI" />
	</jstl:if>
	

<%-- <acme:cancel url="/actor/manager/list.do" code="manager.cancel"/>
 --%>

</form:form>

