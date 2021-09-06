<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/user_dishes.js" defer></script>
<script type="text/javascript" src="resources/js/votes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <table id="commonTable" width="100%">
            <tr>
                <td  width="20%">
                    <form id="restForm">
                        <table width="100%" cellpadding="8">
                            <c:forEach items="${restaurants}" var="restaurant">
                                <jsp:useBean id="restaurant" type="ru.graduation.topjava.model.Restaurant"/>
                                <tr>
                                    <td><a class="btn btn-primary" onclick="showMenu(${restaurant.id}, '${restaurant.name}')">${restaurant.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                </td>
                <td  width="80%">
                    <form id="menu">
                        <table class="table table-striped" id="menuTable" width="70%">
                            <thead>
                            <tr>
                                <th id="restaurantName"></th>
                            </tr>
                            <tr>
                                <th><spring:message code="dish.name"/></th>
                                <th><spring:message code="dish.price"/></th>
                            </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td class="btn btn-primary" id="votingBtn" onclick="vote()">
                                        <spring:message code="vote.doVoting"/>
                                    </td>
                                    <td class="btn btn-primary" id="showVotes" onclick="getVotes()">
                                        <spring:message code="vote.showVotes"/>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="todayVotes">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="todayVotesForm">
                    <table class="table table-striped" id="voteTable">
                        <thead>
                        <tr>
                            <th><spring:message code="vote.userName"/></th>
                            <th><spring:message code="vote.userEmail"/></th>
                            <th><spring:message code="vote.restName"/></th>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.ok"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="dish"/>
</jsp:include>
</html>
