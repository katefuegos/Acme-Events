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


<display:table name="socialProfiles" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column>
			<a href="socialProfile/edit.do?socialProfileId=${row.id}"> <spring:message
					code="socialProfile.edit" />
			</a>
	</display:column>
	
	<display:column>
			<a href="socialProfile/show.do?socialProfileId=${row.id}"> <spring:message
					code="socialProfile.show" />
			</a>
	</display:column>
		
	<display:column property="name" titleKey="socialProfile.name" />
	<display:column property="nick" titleKey="socialProfile.nick" />
	<display:column property="link" titleKey="socialProfile.link" />
	
</display:table>
<br/>
<a href="socialProfile/create.do"> <spring:message
					code="socialProfile.create" />
			</a>

