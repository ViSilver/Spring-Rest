<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta> charset="utf8">

        <title>Address - Error</title>

        <link rel="stylesheet" href="<c:url value=" /css/company.css"/>" type="text/css">
    </head>

    <body>
    <h1>Error</h1>

    <p>
        An unrecoverable error has occurred.
    </p>

    <!--
    Failed URL: ${pageContext.request.requestURL}
    Exception:  ${exception.message}
    <c:forEach items="${exception.stackTrace}" var="ex">
        ${ex}
    </c:forEach>
    -->

    <a href="<c:url value=" /home"/>" >
        &laquo; home
    </a>

    </body>
</html>