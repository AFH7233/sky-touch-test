<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<script>
    function request(){
        var request = document.getElementById("request");
        request.submit();
    }
</script>
    <body onload=request()>
        <h3>Wait pleeeeaseeee</h3>
                <form:form method="${method}"
                  action="${location}" id="request">
                    <input type="hidden" name="id" value="${id}"/>
                </form:form>
    </body>
</html>