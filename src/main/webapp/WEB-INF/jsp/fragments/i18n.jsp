<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    const i18n = [];

    <%-- user.add/user.edit or dish.add/dish.edit --%>
    i18n["addTitle"] = '<spring:message code="${param.page}.add"/>';
    i18n["editTitle"] = '<spring:message code="${param.page}.edit"/>';

    i18n["dishAddTitle"] = '<spring:message code="dish.add"/>';
    i18n["dishEditTitle"] = '<spring:message code="dish.edit"/>';

    i18n["voteTitle"] = '<spring:message code="vote.title"/>';
    i18n["voteSuccess"] = '<spring:message code="vote.success"/>';
    i18n["restMenu"] = '<spring:message code="dish.menu"/>';

    <c:forEach var='key' items='<%=new String[]{"common.deleted", "common.edit", "common.saved", "common.select", "common.enabled", "common.disabled", "common.confirm"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>