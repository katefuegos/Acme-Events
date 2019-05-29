<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/cabeceraacmeevents2.jpg"
		alt="Sample Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->


		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/actor.do?authority=CLIENT"><spring:message
								code="master.page.register.client" /></a></li>

					<li><a href="register/actor.do?authority=MANAGER"><spring:message
								code="master.page.register.manager" /></a></li>

					<li><a href="register/actor.do?authority=PUBLICITER"><spring:message
								code="master.page.register.publiciter" /></a></li>
				</ul></li>

		</security:authorize>




		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>

					<li><a href="actor/edit.do"><spring:message
								code="master.page.actor.edit" /> </a></li>
					<li><a href="data/list.do"><spring:message
								code="master.page.data" /> </a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message
						code="master.page.opinion" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="opinion/manager/list.do"><spring:message
								code="master.page.opinion.list" /></a></li>

				</ul></li>
				
			<li><a href="manager/listClubs.do"><spring:message
								code="master.page.listmyclubs" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CLIENT')">
			<li><a class="fNiv"><spring:message
						code="master.page.follows.unfollows" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="club/client/myList.do"><spring:message
								code="master.page.client.clubs" /></a></li>
					<li><a href="event/client/myList.do"><spring:message
								code="master.page.client.events" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.creditCard" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="creditCard/client/edit.do"><spring:message
								code="master.page.creditCard.edit" /></a></li>

				</ul></li>
			<li><a class="fNiv" href="participationEvent/client/list.do"><spring:message
						code="master.page.myParticipations" /></a></li>


			<li><a class="fNiv"><spring:message
						code="master.page.opinion" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="opinion/client/list.do"><spring:message
								code="master.page.opinion.list" /></a></li>
					<li><a href="opinion/client/create.do"><spring:message
								code="master.page.opinion.create" /></a></li>

				</ul></li>



		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">

			<li><a href="dashboard/administrator/dashboard.do"><spring:message
						code="master.page.administrator.dashboard" /></a> <br></li>
			<li><a href="actor/administrator/list.do"><spring:message
						code="master.page.administrator.actors" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/administrator/actor.do?authority=ADMIN"><spring:message
								code="master.page.register.admin" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.categories" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="category/administrator/list.do"><spring:message
								code="master.page.categories.list" /></a></li>
					<li><a href="category/administrator/create.do"><spring:message
								code="master.page.categories.create" /></a></li>
				</ul>
			<li><a href="club/administrator/list.do"><spring:message
						code="master.page.club.admin" /> </a></li>


		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="box/actor/list.do"><spring:message
						code="master.page.box" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/actor/exchangeMessage.do"><spring:message
								code="master.page.message.exchange" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="message/administrator/broadcastMessage.do"><spring:message
									code="master.page.message.broadcast" /></a></li>
					</security:authorize>

				</ul></li>
			<li>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="message/administrator/broadcastMessage.do"><spring:message
									code="master.page.message.broadcast" /></a></li>
						<li><a href="message/administrator/broadcastMessageNotify.do"><spring:message
									code="master.page.message.notify" /></a></li>
					</security:authorize>

				</ul>
			</li>

		</security:authorize>
		<security:authorize access="isAnonymous()">
		
		<li><a class="fNiv" href="club/list.do"><spring:message
					code="master.page.listclub" /></a></li>
					
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
		
		<li><a class="fNiv" href="club/list.do"><spring:message
					code="master.page.listclub" /></a></li>
					
		</security:authorize>
		<li><a class="fNiv" href="manager/list.do"><spring:message
					code="master.page.listManagers" /></a></li>
		<li><a class="fNiv" href="event/list.do"><spring:message
					code="master.page.listevent" /></a></li>
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="welcome/terms.do"><spring:message
						code="master.page.privacyPolicy" /></a></li>
		</security:authorize>


		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="welcome/terms.do"><spring:message
						code="master.page.privacyPolicy" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="socialProfile/list.do"><spring:message
						code="master.page.listProfiles" /></a></li>
		</security:authorize>


	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

