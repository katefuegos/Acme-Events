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
	<spring:message code="administrator.dashboard.C1" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out
			value="${avgC1}" /></li>
	<li><spring:message code="administrator.max" />: <jstl:out
			value="${maxC1}" /></li>
	<li><spring:message code="administrator.min" />: <jstl:out
			value="${minC1}" /></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out
			value="${stddevC1}" /></li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C2" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out
			value="${avgC2}" /></li>
	<li><spring:message code="administrator.max" />: <jstl:out
			value="${maxC2}" /></li>
	<li><spring:message code="administrator.min" />: <jstl:out
			value="${minC2}" /></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out
			value="${stddevC2}" /></li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C3" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out
			value="${avgC3}" /></li>
	<li><spring:message code="administrator.max" />: <jstl:out
			value="${maxC3}" /></li>
	<li><spring:message code="administrator.min" />: <jstl:out
			value="${minC3}" /></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out
			value="${stddevC3}" /></li>
</ul>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C4" />
</h3>

<ul>
	<li><spring:message code="administrator.avg" />: <jstl:out
			value="${avgC4}" /></li>
	<li><spring:message code="administrator.max" />: <jstl:out
			value="${maxC4}" /></li>
	<li><spring:message code="administrator.min" />: <jstl:out
			value="${minC4}" /></li>
	<li><spring:message code="administrator.stddev" />: <jstl:out
			value="${stddevC4}" /></li>
</ul>
<br />
<br />

<h3>
	<spring:message code="administrator.dashboard.C5" />
</h3>
<li><spring:message code="administrator.ratio" />: <jstl:out
		value="${resultC5}" /></li>
<br />
<br />
<h3>
	<spring:message code="administrator.dashboard.C6" />
</h3>
<li><spring:message code="administrator.ratio" />: <jstl:out
		value="${resultC6}" /></li>
<br />
<br />


<h3>
	<spring:message code="administrator.dashboard.C7" />
</h3>
<display:table name="queryC7" id="row" class="displaytag">
	<display:column property="name" titleKey="club.name" />
	<display:column property="address" titleKey="club.address" />
	<display:column property="description" titleKey="club.description" />
	<display:column property="score" titleKey="administrator.score" />
	<display:column titleKey="club.pictures">
		<img src='<jstl:out value="${row.pictures }"/>' height="100"
			width="auto">
	</display:column>

	<display:column>
		<a href="club/show.do?clubId=${row.id}"> <spring:message
				code="club.show" />
		</a>
	</display:column>
</display:table>
<br />
<br />