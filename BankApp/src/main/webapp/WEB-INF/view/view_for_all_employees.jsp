<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h3>Information from all employees</h3>
<br>

<security:authorize access="hasRole('HR')">
    Only for HR staff
    <input type="button" value="Salary" onclick="window.location.href = 'hr_info'">
</security:authorize>
<br><br>

<security:authorize access="hasRole('MANAGER')">
    Only for Managers
    <input type="button" value="Performance" onclick="window.location.href = 'manager_info'">
</security:authorize>
<br>


</body>
</html>