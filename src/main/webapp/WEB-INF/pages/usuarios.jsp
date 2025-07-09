<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html lang="pt-BR">
<head>
  <title>Usuários</title>
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
  </style>

</head>
<body>

<nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <h2 class="navbar-brand">RE-VIU?</h2>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/menu">Início</a>
        </li>
        <c:if test="${sessionScope.usuarioLogado.permissao.nome eq 'ADMINISTRADOR'}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/obras">Obras</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/tipos">Tipos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/usuarios">Usuários</a>
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



<main class="card container mt-4">
  <div class="p-3">
    <a href="${pageContext.request.contextPath}/menu" class="btn btn-outline-secondary"><i class="bi bi-arrow-left"></i> Voltar ao menu</a>

    <h1 class="text-center mb-4">Usuários</h1>

    <table class="table table-striped table-dark table-bordered shadow-sm">
      <thead>
      <tr>
        <th>Nome</th>
        <th>Email</th>
        <th class="text-center">Permissão</th>
        <th class="text-center">Ação</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="usuario" items="${usuarios}">
        <tr>
          <td>${usuario.nome}</td>
          <td>${usuario.email}</td>
          <td>
            <form action="${pageContext.request.contextPath}/usuarios/${usuario.id}/permissao" method="post" class="d-flex">
              <select name="permissaoId" class="form-select me-2"
                      <c:if test="${usuario.id == 1}">disabled</c:if>>
                <c:forEach var="p" items="${permissoes}">
                  <option value="${p.id}"
                          <c:if test="${usuario.permissao != null and usuario.permissao.id == p.id}">
                            selected
                          </c:if>>
                      ${p.nome}
                  </option>
                </c:forEach>
              </select>

              <c:if test="${usuario.id != 1}">
                <button type="submit" class="btn btn-sm btn-success">Salvar</button>
              </c:if>
            </form>

          </td>
          <td class="text-center">
              <form action="/usuarios/excluir/${usuario.id}" method="get" onsubmit="return confirm('Tem certeza que deseja excluir o usuário ${usuario.nome}?');">
                <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
              </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </div>
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

</body>
</html>
