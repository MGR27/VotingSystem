<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/admin_dishes.js" defer></script>
<script type="text/javascript" src="resources/js/restaurants.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <table id="commonTable" border="0" width="100%">
            <tr>
                <td  width="50%">
                    <form id="restEditForm">
                        <table class="table table-striped" id="restTable" width="98%">
                            <thead>
                            <tr>
                                <th><spring:message code="restaurant.title"/></th>
                            </tr>
                            <tr>
                                <td class="btn btn-primary" onclick="add()">
                                    <span class="fa fa-plus"></span>
                                    <spring:message code="common.add"/>
                                </td>
                            </tr>
                            <tr>
                                <th width="200px"><spring:message code="restaurant.name"/></th>
                                <th><spring:message code="restaurant.registered"/></th>
                                <th><spring:message code="restaurant.active"/></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                        </table>
                    </form>
                </td>
                <td  width="50%">
                    <form id="menu">
                        <table class="table table-striped" id="menuTable" align="right" width="98%">
                            <thead>
                            <tr>
                                <th id="dishRestTitle"><spring:message code="dish.title"/></th>
                            </tr>
                            <tr>
                                <td class="btn btn-primary" id="dishAdd" onclick="dishAdd()">
                                    <span class="fa fa-plus"></span>
                                    <spring:message code="common.add"/>
                                </td>
                            </tr>
                            <tr id="dishHead">
                                <th><spring:message code="dish.name"/></th>
                                <th><spring:message code="dish.price"/></th>
                                <th><spring:message code="dish.date"/></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="restaurant.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="restaurant.name"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="dishEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="dishModalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="dishDetailsForm">
                    <input type="hidden" id="dishId" name="id">

                    <div class="form-group">
                        <label for="dishName" class="col-form-label"><spring:message code="dish.name"/></label>
                        <input type="text" class="form-control" id="dishName" name="name"
                               placeholder="<spring:message code="dish.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="dish.price"/></label>
                        <input type="text" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="dish.price"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="dishSave()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="restaurant"/>
</jsp:include>
</html>
