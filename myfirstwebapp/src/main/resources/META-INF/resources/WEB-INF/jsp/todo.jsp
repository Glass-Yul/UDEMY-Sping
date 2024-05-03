    <%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
    <div class="container">
        <h1>Todo Details</h1>
        <form:form method="post" modelAttribute="todo">
            <form:label path="description">Description</form:label>
            <form:input type="text" required="required" path="description"/>
            <br>
            <fieldset class="mb-2">
                <form:label path="targetDate">Target Date</form:label>
                <form:input type="text" path="targetDate"></form:input>
            </fieldset>
            <br>
            <form:input type="hidden" path="done"/>
            <form:input type="hidden" path="id"/>
            <input type="submit" class="btn btn-success">

        </form:form>

    </div>
    <%@ include file="common/footer.jspf" %>