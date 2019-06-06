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

	<spring:message code="club.accepted" />

</h3>
<display:table name="clubs" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column property="score" titleKey="administrator.score" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<jstl:if test="${row.draftMode==true }">
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
		</jstl:if>
		<jstl:if test="${row.draftMode==false }">
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
		</jstl:if>
	</display:column>
	<display:column>
		<a href="actor/manager/show.do?managerId=${row.manager.id}"> <spring:message
				code="club.showManager" />
		</a>
		<br>
		<br>

		<a href="club/manager/listFollows.do?clubId=${row.id }"> <spring:message
				code="club.follow.list" />
		</a>
		<br>
		<br>

		<a href="event/manager/list.do?clubId=${row.id }"> <spring:message
				code="club.event.list" />
		</a>
	</display:column>

</display:table>

<h3>

	<spring:message code="club.pending" />

</h3> 

<display:table name="clubsPending" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<jstl:if test="${row.draftMode==true }">
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
		</jstl:if>
		<jstl:if test="${row.draftMode==false }">
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
		</jstl:if>
	</display:column>

</display:table>

<h3>

	<spring:message code="club.rejected" />

</h3>

<display:table name="clubsRejected" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column property="reasonReject" titleKey="club.reasonReject" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<jstl:if test="${row.draftMode==true }">
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
		</jstl:if>
		<jstl:if test="${row.draftMode==false }">
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
		</jstl:if>
	</display:column>

</display:table>

<h3>

	<spring:message code="club.listdraftmode" />

</h3>

<display:table name="clubsDraftMode" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column  titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100" width="auto">
	</display:column>
	<display:column>
		<jstl:if test="${row.draftMode==true }">
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
		</jstl:if>
		<jstl:if test="${row.draftMode==false }">
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
		</jstl:if>
	</display:column>

</display:table>
<br />
<a href="club/manager/create.do"> <spring:message code="club.create" />
</a>

