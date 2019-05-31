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

<display:table name="participationEventForms" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<display:column property="title" titleKey="participationEvent.title" />
	<display:column property="ticker" titleKey="participationEvent.ticker" />
	<display:column property="creditCardNumber" titleKey="participationEvent.creditCardNumber" />
	<display:column property="moment" titleKey="participationEvent.moment" />

	<display:column>
			<%-- <a href="socialProfile/show.do?socialProfileId=${row.client.socialProfile.id}"> <spring:message
					code="socialProfile.show" />
			</a> --%>

			<a href="event/show.do?eventId=${row.id}"> 
			<spring:message code="event.show" />
			</a>
	
	</display:column>
</display:table>








