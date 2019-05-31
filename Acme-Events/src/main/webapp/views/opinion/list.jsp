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

<security:authorize access="hasRole('CLIENT')">
<display:table name="opinionsForms" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column property="ticker" titleKey="opinion.ticker" />
	<display:column property="titleEvent" titleKey="opinion.titleEvent" />
	<display:column property="title" titleKey="opinion.title" />
	<display:column property="description" titleKey="opinion.description" />
	<display:column property="score" titleKey="opinion.score" />
	<display:column property="moment" titleKey="opinion.moment" />
	<display:column>
	<a href="event/show.do?eventId=${row.id}"> 
			<spring:message code="event.show" />
			</a>
	
	</display:column>
</display:table>

<a href="opinion/client/create.do"> <spring:message
					code="opinion.create" />
</a>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">

<%-- <display:table name="opinionsForms" id="row" requestURI="${requestURI}"
	pagesize="3" class="displaytag">
	
	<display:column property="event.ticker" titleKey="opinion.ticker" />
	<display:column property="event.title" titleKey="opinion.titleEvent" />
	<display:column property="event.club.name" titleKey="opinion.club" />
	<display:column property="title" titleKey="opinion.title" />
	<display:column property="description" titleKey="opinion.description" />
	<display:column property="score" titleKey="opinion.score" />
	<display:column property="moment" titleKey="opinion.moment" />
	
</display:table> --%>

<%-- <h3>
	<spring:message code="opinion.events.size" />
</h3>
 --%>
<display:table name="events" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column property="ticker" titleKey="opinion.ticker" />
	<display:column property="title" titleKey="opinion.titleEvent" />
	<display:column property="club.name" titleKey="opinion.club" />
	<display:column  titleKey="opinion.number" >
		<jstl:out value="${fn:length(row.opinions) }"/>
	</display:column>
	<display:column>
	<a href="event/opinions.do?eventId=${row.id}"> 
			<spring:message code="event.opinions" />
		</a>
	</display:column>
	
</display:table>



</security:authorize>
