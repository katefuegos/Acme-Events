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

<h3>

	<spring:message code="club.admin.pending" />

</h3>

<display:table name="pendingClubs" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<a href="manager/show.do?clubId=${row.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>
	<display:column>
		<a href="club/administrator/accept.do?clubId=${row.id}"> <spring:message
				code="club.acceptClub" />
		</a>
	</display:column>
	<display:column>
		<a href="club/administrator/reject.do?clubId=${row.id}"> <spring:message
				code="club.rejectClub" />
		</a>
	</display:column>

</display:table>

<h3>

	<spring:message code="club.admin.accepted" />

</h3>

<display:table name="acceptedClubs" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<a href="manager/show.do?clubId=${row.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>

</display:table>

<h3>

	<spring:message code="club.admin.rejected" />

</h3>

<display:table name="rejectedClubs" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column property="reasonReject" titleKey="club.reasonReject" />
	<display:column>
		<a href="manager/show.do?clubId=${row.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>

</display:table>



