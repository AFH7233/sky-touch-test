<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="/resources/tables.css">
    </head>
    <body>
        <h1>Todos los productos creados desde el inicio de los tiempos</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Creation Date</th>
            </tr>
            <c:forEach items="${product}" var="singleProduct">
                <tr>
                    <td><c:out value="${singleProduct.id}"/></td>
                    <td><c:out value="${singleProduct.name}"/></td>
                    <td><c:out value="${singleProduct.description}"/></td>
                    <td><c:out value="${singleProduct.creationDate}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/genericProduct/home">Return home</a>
    </body>
</html>

