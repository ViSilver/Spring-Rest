<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>

    <title>World - Address Details</title>

    <link rel="stylesheet" href="<c:url value="/css/company.css" />" type="text/css"/>
</head>

<body>

<c:if test="${msg != null}">
    <div><span class="message shadow">${fn:escapeXml(msg)}</span></div>
</c:if>

<h1><a href="<c:url value=" /home"/>">Company</a></h1>

<section>
    <c:if test="${isForUpdate}">
        <form:form modelAttribute="address" action="${pageContext.request.contextPath}/addresses" method="POST">
            <button type="submit" name="delete" >
                Delete
            </button>
        </form:form>
    </c:if>

    <table class="details silver">
        <tr>
            <th colspan="2">Address Details</th>
        </tr>
        <tr>
            <td>City</td>
            <td>${fn:escapeXml(address.city)}</td>
        </tr>
        <tr>
            <td>Street</td>
            <td>${fn:escapeXml(address.street)}</td>
        </tr>
        <tr>
            <td>Number</td>
            <td>${fn:escapeXml(address.number)}</td>
        </tr>
    </table>

</section>

<a href="<c:url value=" /addresses"/>" >
    &laquo; back
</a>

</body>
</html>