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

<jstl:if test="${eventManagerForm.poster != null }">
<img src='<jstl:out value="${eventManagerForm.poster }"/>'  height="200" width="auto"> 
 </jstl:if>

<form:form action="${requestURI}" modelAttribute="eventManagerForm">

	<form:hidden path="id" />

	<acme:textbox code="event.title" path="title" readonly="${isRead}" />
	<acme:textbox code="event.address" path="address" readonly="${isRead}" />
	<acme:textarea code="event.description" path="description"
		readonly="${isRead}" />
	<jstl:if test="${isRead == false}">
	<acme:textbox code="event.price" path="price" readonly="${isRead}" />
	</jstl:if>
	<jstl:if test="${isRead == true}">
	<spring:message code="event.price" /> <input type="text" readonly="true" name="price" value="<jstl:out value="${eventManagerForm.price*((varTax/100) +1) }"/>">	
	</jstl:if>
	<acme:textbox code="event.poster" path="poster" readonly="${isRead}" />
	<acme:textbox code="event.momentStart" path="momentStart"
		readonly="${isRead}" />
	<acme:textbox code="event.momentEnd" path="momentEnd"
		readonly="${isRead}" />
	
<jstl:if test="${isRead == true}">
	<acme:textbox code="administrator.score" path="score"
		readonly="${isRead}" />
</jstl:if>
<jstl:if test="${isRead == false}">
	<acme:selectCollection items="${categories}" itemLabel="title"
		code="event.category" path="category" />
	<br />
	<acme:selectCollection items="${clubs}" itemLabel="name"
		code="event.club" path="club" />
	<br />
	
	<form:label path="draftMode">
		<spring:message code="event.draftMode" />
	</form:label>

	<form:checkbox path="draftMode" />
	<form:errors path="draftMode" cssClass="error" readonly="${isRead}" />
	<br />
	
		<acme:submit name="save" code="event.save" />
		<jstl:if test="${id != 0}">
			<acme:delete confirmDelete="event.confirm.delete" name="delete"
				code="event.delete" />

		</jstl:if>
		<acme:cancel url="event/manager/myList.do" code="event.cancel" />
	</jstl:if>


<%-- 	<jstl:if test="${isRead == true}">
		<acme:cancel url="event/manager/myList.do" code="event.cancel" />

	</jstl:if> --%>
</form:form>
