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
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>
<jstl:out value="${event.title }"/>
<br>
<jstl:if test="${event.poster != null }">
<img src='<jstl:out value="${event.poster }"/>' alt="<spring:message code='event.poster' />" height="200" width="auto"> 
 </jstl:if>

</h2>

<display:table name="opinions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column property="title" titleKey="opinion.title" />
	<display:column property="description" titleKey="opinion.description" />
	<display:column property="score" titleKey="opinion.score" />
	<display:column property="moment" titleKey="opinion.moment" />
	
</display:table>

