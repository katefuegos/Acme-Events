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


<display:table name="managers" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="manager.name" />
	<display:column property="middleName" titleKey="manager.middleName" />
	<display:column property="surname" titleKey="manager.surname" />
	<display:column property="address" titleKey="manager.address" />
	<display:column property="phone" titleKey="manager.phone" />
	<display:column property="photo" titleKey="manager.photo" />
	<display:column property="email" titleKey="manager.email" />
	
	
	<display:column>
		<a href="manager/listClubs.do?managerId=${row.id}"> <spring:message
				code="manager.listClub" />
		</a>
	</display:column>
	

</display:table>




