<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html lang="pt-BR">
<head>
    <title>
        <c:choose>
            <c:when test="${review.id != 0}">Editar</c:when>
            <c:otherwise>Nova</c:otherwise>
        </c:choose> Review
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
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

        .slider-value {
            font-weight: bold;
            margin-left: 10px;
        }

        .stars-preview i {
            font-size: 1.5rem;
            color: #ffc206;
            transition: color 0.2s ease;
            margin-right: 4px;
            cursor: pointer;
        }


    </style>
</head>
<body>

<main class="container mt-5">
    <a href="${pageContext.request.contextPath}/menu" class="btn btn-outline-secondary mb-4">
        <i class="bi bi-arrow-left"></i> Voltar ao menu
    </a>
    <h2 class="mb-4 text-center">
        <c:choose>
            <c:when test="${review.id gt 0}">Editar Review</c:when>
            <c:otherwise>Nova Review</c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/reviews" method="post" class="card p-4">

        <c:if test="${review.id gt 0}">
            <input type="hidden" name="id" value="${review.id}" />
        </c:if>

        <c:if test="${review.id le 0}">
            <div class="mb-3">
                <label class="form-label">Usuário</label>
                <input type="text" class="form-control" disabled value="${usuarioLogado.nome}" />
                <input type="hidden" name="usuario.id" value="${usuarioLogado.id}" />
            </div>
        </c:if>

        <div class="mb-3">
            <label for="obra" class="form-label">
                Obra
                <c:if test="${review.id gt 0}"> - Para alterar obra, recrie sua REVIEW</c:if>
            </label>
            <select name="obra.id" class="form-select" required
                    <c:if test="${review.id gt 0}">disabled</c:if>>
                <option value="" disabled
                        <c:if test="${empty review.obra.id}">selected</c:if>>
                    Selecione a obra
                </option>
                <c:forEach var="o" items="${obras}">
                    <option value="${o.id}"
                            <c:if test="${review.obra.id == o.id}">selected</c:if>>
                            ${o.nome} - ${o.tipo.nome}
                    </option>
                </c:forEach>
            </select>

            <c:if test="${review.id gt 0}">
                <input type="hidden" name="obra.id" value="${review.obra.id}" />
            </c:if>
        </div>


        <div class="mb-3">
            <label for="titulo" class="form-label">Título da Review</label>
            <input type="text" class="form-control" name="titulo" required value="${review.titulo}" />
        </div>

        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição</label>
            <textarea name="descricao" class="form-control" rows="3" required>${review.descricao}</textarea>
        </div>

        <div class="mb-3">
            <label for="nota" class="form-label">Nota:</label>
            <span class="slider-value" id="valorNota">${review.nota > 0 ? review.nota : 0}</span>/5
            <div class="stars-preview mb-2" id="estrelasNota">
                <c:forEach begin="1" end="5" var="i">
                    <i class="bi bi-star"
                       data-value="${i}"
                       onclick="selecionarNota(${i})"></i>
                </c:forEach>
            </div>
            <input type="hidden" id="nota" name="nota" value="${review.nota > 0 ? review.nota : 0}" />

        </div>


        <button type="submit" class="btn btn-success">
            <c:choose>
                <c:when test="${review.id != 0}">Salvar Alterações</c:when>
                <c:otherwise>Salvar Review</c:otherwise>
            </c:choose>
        </button>
    </form>
</main>
<c:if test="${not empty msg}">
    <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
        <div id="toastMsg" class="toast align-items-center text-bg-success-outline border-0 show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                        ${msg}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
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

<script>
    function selecionarNota(valor) {
        const estrelas = document.querySelectorAll('#estrelasNota i');
        const inputNota = document.getElementById('nota');
        const spanNota = document.getElementById('valorNota');

        estrelas.forEach((estrela, index) => {
            if (index < valor) {
                estrela.classList.remove('bi-star');
                estrela.classList.add('bi-star-fill');
            } else {
                estrela.classList.remove('bi-star-fill');
                estrela.classList.add('bi-star');
            }
        });

        inputNota.value = valor;
        spanNota.innerText = valor;
    }

    window.addEventListener('DOMContentLoaded', () => {
        const notaAtual = parseInt(document.getElementById('nota').value);
        selecionarNota(notaAtual);
    });
</script>


</body>
</html>
