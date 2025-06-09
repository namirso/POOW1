<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html lang="pt-BR">
<head>
  <title>Obras</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
  <style>
    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
      min-height: 100vh;
      padding-top: 56px; /* height of navbar for spacing */
    }
    main {
      width: 50%;
      margin-top: 20px;
    }
  </style>
</head>
<body class="bg-body-secondary">
<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
  <div class="container-fluid">
    <h2 class="navbar-brand">REVIEWS</h2>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="menu">Início</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="obras">Obras</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="usuarios">Usuário</a>
        </li>
        <li class="nav-item">
          <a class="nav-link btn btn-danger text-white" href="login">Sair</a> <!-- Botão de Sair -->
        </li>
      </ul>
    </div>
  </div>
</nav>

<main class="card">
  <h1 class="text-center">Usuários</h1>
  <div class="p-3">
    <form action="usuarios" method="post" class="mb-4">
      <c:choose>
        <c:when test="${usuario.id != null}">
          <h2>Editar Usuário</h2>
          <input type="hidden" name="idusuario" value="${usuario.id}">
        </c:when>
        <c:otherwise>
          <h2>Adicionar Usuário</h2>
          <input type="hidden" name="idusuario" value="0">
        </c:otherwise>
      </c:choose>

      <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" placeholder="Nome" name="nome" required value="${usuario.nome}">
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" placeholder="Email" name="email" required value="${usuario.email}">
      </div>
      <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <input type="password" class="form-control" placeholder="Senha" name="senha" required value="${usuario.senha}">
      </div>

      <c:choose>
        <c:when test="${usuario.id != null}">
          <input type="submit" class="btn btn-primary" value="Alterar" name="gravar">
        </c:when>
        <c:otherwise>
          <input type="submit" class="btn btn-primary" value="Cadastrar" name="gravar">
        </c:otherwise>
      </c:choose>
    </form>

    <c:if test="${not empty msg}">
      <h2 class="text-center text-success">${msg}</h2>
    </c:if>

    <div>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Nome</th>
          <th>Email</th>
          <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${usuarios}">
          <tr>
            <td>${usuario.nome}</td>
            <td>${usuario.email}</td>
            <td>
              <a href="usuarios?opcao=editar&&info=${usuario.id}" class="btn btn-warning btn-sm me-2">Editar</a>
              <a href="usuarios?opcao=excluir&&info=${usuario.id}" class="btn btn-danger btn-sm">Excluir</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

</main>

</body>
</html>
