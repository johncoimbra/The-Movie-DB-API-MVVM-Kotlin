**Objetivo**

Projeto para listar os filmes em cartaz no cinema e seus detalhes ao ser clicado utilizando a API The Movie Database (TMDb).

**API**

Documentação da rota “filmes em cartaz” https://developers.themoviedb.org/3/movies/get-now-playing

Documentação da rota “poster do filme” https://developers.themoviedb.org/3/getting-started/images

Documentação da rota "filmes similares"
https://developers.themoviedb.org/3/movies/get-similar-movies

**Requisitos:**

**Criação do projeto no repositório Git:**

- Tela com uma lista paginada contendo os filmes providos pela API (now_playing).
- Tela que exibe os detalhes de um filme ao se clicar em um filme na tela de lista de filmes
- Na tela de detalhes do filme, contém uma lista de filmes similares (provido da api: /movies/{movies}/similares)
- Nesta tela de detalhes também são exibidos os dados de popularidade e likes que este filme selecionado possui.
- O ícone de like é modificado quando clicado, alternando entre preenchido e vazio.
- Utilização da arquitetura MVVM na estruturação do projeto.
- Lint no projeto
- Utilização de outras rotas da API (now_playing)
