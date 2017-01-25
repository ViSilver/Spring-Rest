<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>Spring Example</title>
    <link rel="stylesheet" href="<c:url value=" /css/company.css"/>" type="text/css"/>
</head>

<body>

<c:if test="${msg != null}">
    <div><span class="message shadow">${fn:escapeXml(msg)}</span></div>
</c:if>

<h1><a href="<c:url value=" /home"/>">Home</a></h1>

<section>

    <a href="<c:url value=" /addresses/new"/>">
        <button>Add New</button>
    </a>

    <table class="list silver">
        <tr>
            <th> </th>
            <th>Addresses</th>
        </tr>
        <c:forEach items="${addresses}" var="address">
            <tr>
                <td>
                    <a href="<c:url value='/addresses/${address.id}?for-update=true'/>">
                        <img src="<c:url value=" /images/edit.gif"/>"/>
                    </a>
                </td>
                <td>
                    <a href="<c:url value='/addresses/${address.id}'/>">
                            ${fn:escapeXml(address.city)}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

</section>

</body>
</html>