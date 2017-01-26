<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>

    <title>World - Employee Details</title>

    <link rel="stylesheet" href="<c:url value="/css/company.css" />" type="text/css"/>
</head>

<body>

<c:if test="${msg != null}">
    <div><span class="message shadow">${fn:escapeXml(msg)}</span></div>
</c:if>

<h1><a href="<c:url value=" /home"/>">Company</a></h1>

<section>
    <form:form modelAttribute="employee" action="${pageContext.request.contextPath}/addresses" method="POST">
        <button type="submit" name="delete" >
            Delete
        </button>
    </form:form>

    <table class="details silver">
        <tr>
            <th colspan="2">Employee Details</th>
        </tr>
        <tr>
            <td>First Name</td>
            <td>${fn:escapeXml(employee.firstName)}</td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td>${fn:escapeXml(employee.lastName)}</td>
        </tr>
        <c:if test="${employee.address != null}">
            <tr>
                <td>Address</td>
                <td>${fn:escapeXml(employee.address)}</td>
            </tr>
        </c:if>
    </table>

</section>

<a href="<c:url value="/employees"/>" >
    &laquo; back
</a>

</body>
</html>