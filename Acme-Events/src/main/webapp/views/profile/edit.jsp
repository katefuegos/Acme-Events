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

<form:form action="club/actor/edit.do" modelAttribute="club">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="subclubes" />
	<form:hidden path="actor" />

<acme:textclub code="club.name" path="name"/>
	

	<jstl:choose>
		<jstl:when test="${club.id == 0}">
			<form:label path="rootclub">
				<spring:message code="club.rootclub" />:
			</form:label>
			<form:select id="clubes" path="rootclub">
				<form:option value="" label="------" />
				<form:options items="${clubes}" itemValue="id" itemLabel="name" />
			</form:select>
			<form:errors cssClass="error" path="rootclub" />
			<br />
		</jstl:when>
		<jstl:otherwise>
			<form:hidden path="rootclub" />
			<spring:message code="club.rootclub" />:
				<jstl:out value="${club.rootclub.name}" />
			<br />
		</jstl:otherwise>
	</jstl:choose>

 
	<acme:submit name="save" code="club.save"/>
	
	<jstl:if test="${club.id != 0}">
	<acme:delete confirmDelete="club.confirm.delete" name="delete" code="club.delete"/>
	</jstl:if>

	<acme:cancel url="club/actor/list.do" code="club.cancel"/>
	<br />
</form:form>
