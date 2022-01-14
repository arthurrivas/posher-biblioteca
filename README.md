# posher-biblioteca
Api simples simulando o backend de uma biblioteca onde se é possivel todas as opereções de CRUD nos livros. Criada para o teste adminissional da posher

Para conseguir acessar os end points é necessario estar logado, usuario "admin",senha "1234", método de autenticação Basic;
Por não possuir a parte do front-end, é recomendado testar a api através do Postman;
Abaixo os end points e a função de cada um.


 Retorna um Json com todos os livros cadastrados usando o método Http GET;
 É possivel tambem pesquisar atraves dos titulos dos livros pela url de requisição
 https://posher-biblioteca.herokuapp.com/livros?titulo=Exemplo+titulo


 Passando um id como parametro é possivel retornar os dados de apenas 1 livro usando o método Http GET; 
 https://posher-biblioteca.herokuapp.com/livros/{id}


 End point para o cadastro de um livro, para o cadastro é necessario passar atraves do corpo da requisição as informações do novo livro utilizando o métoodo Http POST,
 exemplo {"titulo":"exemplo titulo" , " descricao" : "exemplo descrição"} ; 
 https://posher-biblioteca.herokuapp.com/livros/cadastro


 End point para atualizar um livro baseado em seu identificador, para atualizar o cadastro é necessario passar atraves do corpo da requisição as novas informações do 
 livro utilizando o métoodo Http PUT.
 exemplo {"titulo":"exemplo titulo" , " descricao" : "exemplo descrição"} ; 
 https://posher-biblioteca.herokuapp.com/livros/atualizar/{id}

 End point para deletar cadasro passando como parametro o identificador utilizando o métpdp Http DELETE;
 https://posher-biblioteca.herokuapp.com/livros/deletar/{id}

