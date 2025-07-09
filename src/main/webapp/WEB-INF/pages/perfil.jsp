<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html lang="pt-BR">
<head>
    <title>Editar Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="https://cdn-icons-png.flaticon.com/512/616/616489.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap');

        body {
            background-color: #121212;
            color: #ffffff;
            font-family: 'Inter', sans-serif;
        }

        .navbar {
            background-color: #1f1f1f;
            border-bottom: 1px solid #2a2a2a;
        }

        .navbar-brand {
            font-weight: bold;
            color: #d64b00;
            font-size: 1.8rem;
            letter-spacing: 1px;
        }

        .navbar-nav .nav-link {
            color: #d1d1d1;
            transition: color 0.3s ease;
        }

        .navbar-nav .nav-link:hover,
        .navbar-nav .nav-link.active {
            color: #1DB954;
        }

        .dropdown-menu {
            background-color: #2a2a2a;
            color: #ffffff;
        }

        .dropdown-item {
            color: #ffffff;
        }

        .dropdown-item:hover {
            background-color: #1DB954;
            color: #000000;
        }

        .btn-success {
            background-color: #1DB954;
            border-color: #1DB954;
            box-shadow: 0 0 10px #1db95466;
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background-color: #17a64a;
            border-color: #17a64a;
            box-shadow: 0 0 15px #1db954aa;
        }

        .card {
            background-color: #1e1e1e;
            border: 1px solid #2c2c2c;
            color: #e0e0e0;
            transition: transform 0.3s ease;
        }

        .card-title {
            color: #1DB954;
        }

        .text-muted {
            color: #aaaaaa !important;
        }

        .toast {
            background-color: #2a2a2a;
            border: 1px solid #444;
            color: #fff;
        }

        .toast .btn-close-white {
            filter: invert(1);
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="card p-4 mx-auto" style="max-width: 500px;">
        <h2 class="text-center mb-4">Editar Perfil</h2>

        <form action="${pageContext.request.contextPath}/perfil" method="post">
            <div class="mb-3">
                <label class="form-label">Nome</label>
                <input type="text" name="nome" class="form-control" required
                       value="${sessionScope.usuarioLogado.nome}" />
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" disabled
                       value="${sessionScope.usuarioLogado.email}" />
                <input type="hidden" name="email" value="${sessionScope.usuarioLogado.email}" />
            </div>

            <div class="mb-3">
                <label class="form-label">Nova Senha</label>
                <input type="password" name="senha" class="form-control" placeholder="Deixe em branco para não mudar" />
            </div>

            <button type="submit" class="btn btn-primary w-100">Salvar Alterações</button>
        </form>

        <c:if test="${not empty msg}">
            <div class="alert alert-success text-center mt-3">${msg}</div>
        </c:if>

        <div class="text-start mt-3">
            <a href="/menu" class="btn btn-outline-secondary"><i class="bi bi-arrow-left"></i> Voltar</a>
        </div>
    </div>
</div>

</body>
</html>
