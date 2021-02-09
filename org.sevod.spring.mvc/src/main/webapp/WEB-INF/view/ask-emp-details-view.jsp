<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2>Dear Employee, Please enter your details</h2>
<br>
<br>
<%--<form action="showDetails" method="get">--%>
<%--    <input type="text" name="emloyeeName" placeholder="Write your name"/>--%>
<%--    <input type="submit"/>--%>
<%--</form>--%>

<form:form action="showDetails" modelAttribute="employee">
    Name: <form:input path="name"/>
    <form:errors path="name"/>
    <br>
    <br>
    Surname: <form:input path="surname"/>
    <form:errors path="surname"/>
    <br>
    <br>
    Salary: <form:input path="salary"/>
    <br>
    <br>
    Department: <form:select path="department">
<%--        <form:option value="Information Technology" label="IT"/>--%>
<%--        <form:option value="Human Resources" label="HR"/>--%>
<%--        <form:option value="Sales" label="Sales"/>--%>
        <form:options items="${employee.departments}"/>
    </form:select>
    <br><br>
    Which car do you want?
<%--    BMW <form:radiobutton path="carBrand" value="BMW"/>--%>
<%--    Audi <form:radiobutton path="carBrand" value="Audi"/>--%>
<%--    Mercedes-Benz <form:radiobutton path="carBrand" value="Mercedes-Benz"/>--%>
    <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>
    <br><br>
    Foreign Language(s)
    <form:checkboxes path="languages" items="${employee.languageList}"/>
<%--    EN <form:checkbox path="languages" value="English"/>--%>
<%--    DE <form:checkbox path="languages" value="Deutch"/>--%>
<%--    FR <form:checkbox path="languages" value="French"/>--%>
    <br><br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>