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

<form:form action="${requestURI}" modelAttribute="configurationForm">


	<jstl:if test="${isRead==true}">
		<img src="${configurationForm.banner}" height="250px" width="350px" />
		<br />
	</jstl:if>

	<form:label path="varTax">
		<spring:message code="configuration.varTax" />
	</form:label>
	<form:input path="varTax" readonly="${isRead}" />
	<form:errors cssClass="error" path="varTax" />
	<br />

	<form:label path="countryCode">
		<spring:message code="configuration.countryCode" />
	</form:label>
	<form:input path="countryCode" readonly="${isRead}" />
	<form:errors cssClass="error" path="countryCode" />
	<br />
	
	<form:label path="systemName">
		<spring:message code="configuration.systemName" />
	</form:label>
	<form:input path="systemName" readonly="${isRead}" />
	<form:errors cssClass="error" path="systemName" />
	<br />

	<jstl:if test="${isRead==false}">
		<form:label path="banner">
			<spring:message code="configuration.banner" />
		</form:label>
		<form:input path="banner" />
		<form:errors cssClass="error" path="banner" />
		<br />
	</jstl:if>
	
	<form:label path="welcomeMessageES">
		<spring:message code="configuration.welcomeMessageES" />
	</form:label>
	<form:input path="welcomeMessageES" readonly="${isRead}" />
	<form:errors cssClass="error" path="welcomeMessageES" />
	<br />

	<form:label path="welcomeMessageEN">
		<spring:message code="configuration.welcomeMessageEN" />
	</form:label>
	<form:input path="welcomeMessageEN" readonly="${isRead}" />
	<form:errors cssClass="error" path="welcomeMessageEN" />
	<br />

	<form:label path="spamWordsES">
		<spring:message code="configuration.spamES" />
	</form:label>
	<form:input path="spamWordsES" readonly="${isRead}" />
	<form:errors cssClass="error" path="spamWordsES" />
	<br />

	<form:label path="spamWordsEN">
		<spring:message code="configuration.spamEN" />
	</form:label>
	<form:input path="spamWordsEN" readonly="${isRead}" />
	<form:errors cssClass="error" path="spamWordsEN" />
	<br />

	<jstl:if test="${isRead == false}">
		<input type="submit" name="save"
			value="<spring:message code="configuration.save" />" />

		<input type="button" name="cancel"
			value="<spring:message code="configuration.cancel" />"
			onclick="javascript: relativeRedir('configuration/administrator/list.do');" />
	</jstl:if>

	<jstl:if test="${isRead == true}">
		<input type="button" name="cancel"
			value="<spring:message code="actor.back" />"
			onclick="javascript: relativeRedir('configuration/administrator/list.do');" />
	</jstl:if>
</form:form>
