<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>LOGAR</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</head>
<body class="bg-body-secondary">
<div class="card text-center position-absolute top-50 start-50 translate-middle">
    <form action="login" method="post" class="form-signin">
        <h2 class="p-4">LOGAR NO SISTEMA</h2>
        <br>
        <div class="form-outline mb-4">
            <label for="email">
                <b>Login</b>
            </label>
            <input type="email" placeholder="E-mail" name="email" required>
        </div>
        <div class="form-outline mb-4">
            <label for="senha">
                <b>Senha</b>
            </label>
            <input type="password" placeholder="Senha" name="senha" required>
            <br />
        </div>
        <input class="btn btn-secondary" type="submit" value="LOGAR" name="login">
    </form>

    <c:if test="${not empty msg}">
        <h2>${msg}</h2>
    </c:if>


</div>

</body>
</html>

