<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <body>
        <h1>Product managment API</h1>
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
        <div>
            <ul>
                <li><a href="/genericProduct/create">Create Product</a></li>
                <li> <a href="/genericProduct/requestProducts">Show Products</a></li>
            </ul>
        </div>
    </body>
</html>