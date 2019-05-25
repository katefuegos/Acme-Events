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


<display:table name="clubsAccepted" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column>
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
	</display:column>
	
	<display:column>
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
	</display:column>
	
	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column property="pictures" titleKey="club.pictures" />
	<display:column>
		<a href="manager/show.do?managerId=${row.manager.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>

</display:table>

<display:table name="clubsCanceled" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column>
			<a href="club/manager/edit.do?clubId=${row.id}"> <spring:message
					code="club.edit" />
			</a>
	</display:column>
	
	<display:column>
			<a href="club/manager/show.do?clubId=${row.id}"> <spring:message
					code="club.show" />
			</a>
	</display:column>

	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column property="pictures" titleKey="club.pictures" />
	<display:column>
		<a href="manager/show.do?managerId=${row.manager.id}"> <spring:message
				code="club.showManager" />
		</a>
	</display:column>

</display:table>

<a href="club/manager/create.do"> <spring:message
					code="club.create" />
</a>




