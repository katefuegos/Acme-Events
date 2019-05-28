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

<form:form action="${requestURI}" modelAttribute="eventManagerForm">

	<form:hidden path="id" />
	<form:hidden path="club" />
	<form:hidden path="status" />
	
	<acme:textbox code="event.name" path="name"/>
	<acme:textbox code="event.address" path="address"/>
	<acme:textbox code="event.description" path="description"/>
	<acme:textbox code="event.price" path="price"/>
	<acme:textbox code="event.ticker" path="ticker"/>
	<acme:textbox code="event.momentPublished" path="momentPublished"/>
	<acme:textbox code="event.poster" path="poster"/>
	<acme:textbox code="event.momentStart" path="momentStart"/>
	<acme:textbox code="event.momentEnd" path="momentEnd"/>
	
	
	<form:label path="draftMode">
		<spring:message code="event.draftMode" />
	</form:label>
	<form:checkbox path="draftMode" />
	<form:errors path="draftMode" cssClass="error" />
	<br />
	
	<jstl:if test="${isRead == false }">		
		<acme:selectCollection items="${categories}" itemLabel="title" code="audit.category" path="category"/>
	</jstl:if>
	
	<jstl:if test="${isRead == false}">
		<acme:submit name="save" code="event.save" />
		<jstl:if test="${id != 0}">
			<acme:delete confirmDelete="event.confirm.delete" name="delete"
				code="event.delete" />

		</jstl:if>
		<acme:cancel url="event/manager/list.do" code="event.cancel" />
	</jstl:if>


	<jstl:if test="${isRead == true}">
		<acme:cancel url="event/manager/list.do" code="event.cancel" />

	</jstl:if>
</form:form>
