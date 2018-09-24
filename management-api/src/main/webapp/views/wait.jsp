<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<script>
    function request(){
        var request = document.getElementById("request");
        request.submit();
    }

    function wait(){
        setTimeout(request,100);
    }
</script>
    <body onload=wait()>
        <h3>Wait pleeeeaseeee</h3>
                <form:form method="${method}"
                  action="${location}" id="request">
                    <input type="hidden" name="id" value="${id}"/>
                    <input type="hidden" name="time" value="${time}">
                </form:form>
    </body>
</html>