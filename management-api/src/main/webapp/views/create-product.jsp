<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
    <body>
        <h3>Welcome, Human! Enter a new product</h3>
                <form:form method="POST"
                  action="/genericProduct/add" modelAttribute="product">
                    <label>Id: </label><label>"${product.id}"</label><br>
                    <form:label path="name">Name:</form:label><form:input path="name"/><br>
                    <form:label path="description">Descripcion:</form:label><form:textarea path="description"/><br>
                    <form:hidden path="id"/>
                    <input type="submit" />
                </form:form>
    </body>
</html>