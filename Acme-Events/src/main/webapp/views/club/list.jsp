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

<h2>
<jstl:out value="${club.accepted }"/>
<br>
</h2>
<display:table name="clubsAccepted" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	
	
	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>

	<display:column>
			<a href="club/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
	</display:column>
	<display:column>
		<a href="manager/show.do?managerId=${row.manager.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>
	
	<display:column>
			<a href="event/list.do?clubId=${row.id }"> <spring:message
					code="club.event.list" />
			</a>
	</display:column>

</display:table>

