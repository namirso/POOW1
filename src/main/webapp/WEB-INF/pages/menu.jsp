<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html lang="pt-BR">
<head>
    <title>Reviews</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="icon" type="image/png" href="https://cdn-icons-png.flaticon.com/512/616/616489.png">

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

        .card:hover {
            transform: scale(1.01);
            box-shadow: 0 0 10px rgba(29, 185, 84, 0.2);
        }

        .card-title {
            color: #1DB954;
        }

        .card-body {
            min-width: 0;
        }

        .card-text {
            word-break: break-word;
            overflow-wrap: break-word;
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

        .stars {
            color: #ffc206;
        }

    </style>

</head>
<body>

<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <h2 class="navbar-brand">RE-VIU?</h2>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/menu">Início</a>
                </li>
                <c:if test="${sessionScope.usuarioLogado.permissao.nome eq 'ADMINISTRADOR'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/obras">Obras</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/tipos">Tipos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/usuarios">Usuários</a>
                    </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        Meu Perfil
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/perfil">Editar Perfil</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reviews/minhas">Minhas Reviews</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout">Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="container mt-4">
    <h1 class="text-center mb-4">Feed de Reviews</h1>

        <div class="d-flex justify-content-end mb-3">
            <a href="reviews/nova" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Nova Review
            </a>
        </div>

    <c:forEach var="r" items="${reviews}">
        <div class="card mb-3 shadow-sm d-flex flex-row">
            <img src="${r.obra.imagemURL}" class="img-fluid rounded-start" style="width: 150px; object-fit: cover;" alt="Imagem da Obra">
            <div class="card-body">
                <h3 class="card-title d-flex justify-content-between align-items-center">
                        ${r.titulo}
                </h3>
                <div class="stars mb-2">
                    <c:forEach begin="1" end="5" var="i">
                        <c:choose>
                            <c:when test="${i <= r.nota}">
                                <i class="bi bi-star-fill" style="font-size: 1.2rem;"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="bi bi-star" style="font-size: 1.2rem;"></i>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    (${r.nota}/5)
                </div>
                <h6 class="card-subtitle text-muted">Por: ${r.usuario.nome} — <em>${r.obra.nome}</em></h6>
                <p class="card-text mt-2">
                        ${r.descricao}
                </p>
            </div>
        </div>
    </c:forEach>
</main>

<c:if test="${not empty msg}">
    <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
        <div id="toastMsg" class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">${msg}</div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
            </div>
        </div>
    </div>
</c:if>

<script>
    setTimeout(() => {
        const toast = bootstrap.Toast.getOrCreateInstance(document.getElementById('toastMsg'));
        toast.hide();
    }, 5000);
</script>

</body>
</html>
