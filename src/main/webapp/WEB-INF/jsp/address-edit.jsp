<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8"/>

        <title>Company - Address Maintenance</title>

        <link rel="stylesheet" href="<c:url value=" /css/company.css"/>" type="text/css"/>
    </head>

    <body>
        <h1><a href="<c:url value=" /home"/>">Company</a></h1>

        <section>

            <form:form modelAttribute="address" action="${pageContext.request.contextPath}/addresses" method="POST">

                <c:choose>
                    <c:when test="${address.newAddress}">
                        <button type="submit" name="create">
                            Create
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="update">
                            Update
                        </button>
                    </c:otherwise>
                </c:choose>

                <table class="editor sliver">
                    <tr>
                        <th colspan="2">Edit Address Details</th>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>
                            <form:input path="city" size="20" maxlength="30"/><br/>
                            <form:errors path="city" cssClass="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td>
                            <form:input path="street" size="20" maxlength="30"/><br/>
                            <form:errors path="street" cssClass="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Number</td>
                        <td>
                            <form:input path="number" size="5" maxlength="15"/><br/>
                            <form:errors path="number" cssClass="error"/>
                        </td>
                    </tr>
                </table>

            </form:form>

        </section>

        <a href="<c:url value='/addresses'/>">
            &laquo; cancel
        </a>
    </body>
</html>