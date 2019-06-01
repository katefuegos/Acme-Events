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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h2>
	<spring:message code='event.saveds' />
	<br>
</h2>

<display:table name="eventsFinal" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="event.ticker" />
	<display:column property="title" titleKey="event.title" />
	<display:column titleKey="event.poster">
		<img src='<jstl:out value="${row.poster }"/>'
			alt="No <spring:message code='actor.photo' />" height="150"
			width="auto">
	</display:column>
	<display:column property="price" titleKey="event.price" />
	<display:column property="score" titleKey="administrator.score" />
	<display:column property="momentStart" titleKey="event.momentStart" />
	<display:column property="momentEnd" titleKey="event.momentEnd" />

	<display:column property="club.name" titleKey="event.club" />
	<display:column titleKey="event.category">
		<jstl:forEach var="entry" items="${row.category.title}">
			<jstl:if test="${lang==entry.key}">
				<jstl:out value="${entry.value}" />
			</jstl:if>
		</jstl:forEach>
	</display:column>
	<display:column titleKey="event.total.participations">
		<jstl:out value="${fn:length(row.participationsEvent) }" />
	</display:column>
	<display:column titleKey="opinion.number">
		<jstl:out value="${fn:length(row.opinions) }" />
	</display:column>
	<display:column property="status" titleKey="event.status" />
	<display:column>
		<a href="event/manager/show.do?eventId=${row.id}"> <spring:message
				code="event.show" />
		</a>
		<br />
		<br />
		<jstl:if test="${row.status=='AVAILABLE'}">
			<a href="event/manager/cancel.do?eventId=${row.id}"> <spring:message
					code="event.cancel" />
			</a>
		</jstl:if>
	</display:column>
	<display:column>
		<jstl:if test="${row.draftMode==false }">
			<a href="participationEvent/manager/list.do?eventId=${row.id}"> <spring:message
					code="event.participationes" />
			</a>
			<br>
			<br>
			<a href="event/opinions.do?eventId=${row.id}"> <spring:message
					code="event.opinions" />
			</a>

		</jstl:if>
	</display:column>



</display:table>

<h2>
	<spring:message code='event.drafts' />
	<br>
</h2>

<display:table name="eventsDraft" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="event.ticker" />
	<display:column property="title" titleKey="event.title" />
	<display:column titleKey="event.poster">
		<img src='<jstl:out value="${row.poster }"/>'
			alt="No <spring:message code='actor.photo' />" height="150"
			width="auto">
	</display:column>
	<display:column property="price" titleKey="event.price" />
	<display:column property="score" titleKey="administrator.score" />
	<display:column property="momentStart" titleKey="event.momentStart" />
	<display:column property="momentEnd" titleKey="event.momentEnd" />

	<display:column property="club.name" titleKey="event.club" />
	<display:column titleKey="event.category">
		<jstl:forEach var="entry" items="${row.category.title}">
			<jstl:if test="${lang==entry.key}">
				<jstl:out value="${entry.value}" />
			</jstl:if>
		</jstl:forEach>
	</display:column>
	<display:column>
		<a href="event/manager/edit.do?eventId=${row.id}"> <spring:message
				code="event.edit" />
		</a>
	</display:column>

</display:table>
<br />
<a href="event/manager/create.do"> <spring:message
		code="event.create" />
</a>