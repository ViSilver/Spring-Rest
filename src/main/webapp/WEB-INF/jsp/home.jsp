<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

  <head>
    <meta charset="utf-8"/>

    <title>World</title>

    <link rel="stylesheet" href="<c:url value=" /css/company.css"/>" type="text/css"/>
  </head>

  <body>
    <h1>Company</h1>

    <p>
      <a href="<c:url value=" employees"/>">Employees</a>
    </p>

    <p>
        <a href="<c:url value=" addresses"/>">Addresses</a>
    </p>

  </body>
</html>