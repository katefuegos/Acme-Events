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

<form:form action="event/client/search.do"
		modelAttribute="searchForm">

		<form:label path="keyWord">
			<spring:message code="event.keyWord" />
		</form:label>
		<form:input path="keyWord" />
		<form:errors cssClass="error" path="keyWord" />
		<br />

		<form:label path="priceMin">
			<spring:message code="event.priceMin" />
		</form:label>
		<form:input path="priceMin" />
		<form:errors cssClass="error" path="priceMin" />
		<br />

		<form:label path="priceMax">
			<spring:message code="event.priceMax" />
		</form:label>
		<form:input path="priceMax" />
		<form:errors cssClass="error" path="priceMax" />
		<br />

		<form:label path="dateMin">
			<spring:message code="event.dateMin" />
		</form:label>
		<form:input path="dateMin" placeholder="yyyy/mm/dd hh:mm" />
		<form:errors cssClass="error" path="dateMin" />
		<br />

		<form:label path="dateMax">
			<spring:message code="event.dateMax" />
		</form:label>
		<form:input path="dateMax" placeholder="yyyy/mm/dd hh:mm" />
		<form:errors cssClass="error" path="dateMax" />
		<br />

		
		<input type="submit" name="search"
			value="<spring:message code="event.search"/>" />

	</form:form>


<display:table name="events" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="event.ticker" />
	<display:column property="title" titleKey="event.title" />
	<display:column titleKey="event.poster">
		<img src='<jstl:out value="${row.poster }"/>' alt="No <spring:message code='actor.photo' />" height="150" width="auto"> 
	</display:column>
	<display:column property="price" titleKey="event.price" />
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
		<a href="opinion/client/list.do?eventId=${row.id}"> 
			<spring:message code="event.opinions" />
		</a>
		<br>
		<a href="event/client/participate.do?eventId=${row.id}"> 
			<spring:message code="event.participations" />
		</a>
	</display:column>


</display:table>








