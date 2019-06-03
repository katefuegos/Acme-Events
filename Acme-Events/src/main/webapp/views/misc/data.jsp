<%--
 * index.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen" type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">

<input type="button"
	value="<spring:message code="misc.exportPersonalData"/>"
	onclick="javascript: window.location.href = 'data/downloadPersonalData.do';" />
<br>
<br>
<security:authorize access="hasRole('CLIENT')">
	<a class="enlaceboton"
		href="data/deletePersonalData.do" onclick="return confirm('<spring:message code="actor.confirm.delete.personalData"/>')">
			<spring:message code="misc.deletePersonalData" />
	</a><br>
	<spring:message code="misc.deletePersonalDataInfo" />
</security:authorize>
<security:authorize access="hasRole('MANAGER')">
	<%-- <input type="button"
		value="<spring:message code="misc.deletePersonalData"/>"
		onclick="return confirm('<spring:message code="actor.confirm.delete.personalData"/>') javascript: window.location.href = 'data/deletePersonalData.do';" />
	<br> --%>
	<a class="enlaceboton"
		href="data/deletePersonalData.do" onclick="return confirm('<spring:message code="actor.confirm.delete.personalData"/>')">
			<spring:message code="misc.deletePersonalData" />
	</a><br>
	<spring:message code="misc.deletePersonalDataInfo" />
</security:authorize>
<security:authorize access="hasRole('PUBLICITER')">
	<a class="enlaceboton"
		href="data/deletePersonalData.do" onclick="return confirm('<spring:message code="actor.confirm.delete.personalData"/>')">
			<spring:message code="misc.deletePersonalData" />
	</a><br>
	<spring:message code="misc.deletePersonalDataInfo" />
</security:authorize>