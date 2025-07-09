<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html lang="pt-BR">
<head>
    <title>Minhas Reviews</title>
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

        .btn-success {
            background-color: #1DB954;
            border-color: #1DB954;
            box-shadow: 0 0 10px #1db95466;
        }

        .btn-success:hover {
            background-color: #17a64a;
            border-color: #17a64a;
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

        .obra-imagem {
            width: 150px;
            object-fit: cover;
            border-radius: 0.375rem;
        }
    </style>
</head>
<body>

<div class="container mt-4">
    <h1 class="text-center mb-4">Minhas Reviews</h1>

    <c:if test="${empty reviews}">
        <div class="alert alert-info text-center">
            Você ainda não escreveu nenhuma review.
        </div>
    </c:if>

    <c:forEach var="r" items="${reviews}">
        <div class="card mb-3 shadow-sm d-flex flex-row">
            <div class="d-flex align-items-center justify-content-center p-3">
                <c:choose>
                    <c:when test="${not empty r.obra.imagemURL}">
                        <img src="${r.obra.imagemURL}" alt="Imagem da Obra" class="obra-imagem">
                    </c:when>
                    <c:otherwise>
                        <div class="text-muted fst-italic text-center" style="width: 150px;">Sem imagem</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-body d-flex flex-column">
                <div class="flex-grow-1">
                    <h3 class="card-title mb-1">
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
                    <h6 class="card-subtitle text-muted mb-2">${r.obra.nome}</h6>
                    <p class="card-text mt-2">${r.descricao}</p>
                </div>
                <div class="d-flex justify-content-start gap-2 mt-3">
                    <a href="${pageContext.request.contextPath}/reviews/${r.id}/editar" class="btn btn-sm btn-outline-primary">
                        <i class="bi bi-pencil-square"></i> Editar
                    </a>
                    <a href="${pageContext.request.contextPath}/reviews/${r.id}/excluir"
                       class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('Tem certeza que deseja excluir esta review?');">
                        <i class="bi bi-trash"></i> Excluir
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="text-start mt-3">
        <a href="${pageContext.request.contextPath}/menu" class="btn btn-outline-secondary"><i class="bi bi-arrow-left"></i> Voltar ao menu</a>
    </div>
</div>

</body>
</html>
