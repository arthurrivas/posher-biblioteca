# posher-biblioteca
Api simples simulando o backend de uma biblioteca onde se é possivel todas as opereções de CRUD nos livros. Criada para o teste adminissional da posher

Para conseguir testar toda a api, é necessario usar o PostMan para fazer as requisições e modificar os headers

Para conseguir acessar os end points é necessario estar logado;
Utilizando o método Http POST crie um login pelo endpoint https://posher-biblioteca.herokuapp.com/usuarios passando como corpo de requisição um email e uma senha.
Exemplo: {"email":"exemploemail@gmail.com","senha":"senhaEscolhida"}.

Logo em seguida faça login pela endPoint https://posher-biblioteca.herokuapp.com/login com o usuario recem criado.
Como não foi criado o frontEnd, manualmente pegue o valor no Header de resposta chamado "Authorization", e cole no header da proxima requisição que ira ser feita.
Esse valor sera o Token de authenticação valido por 1 hora.

Agora que esta autenticado, esta liberado para mexer livremente na api.

Todos os endPoints estão disponiveis no diretorio https://github.com/arthurrivas/posher-biblioteca/tree/master/src/main/java/br/com/poher/biblioteca/resources,
cada um com um breve comentario sobre sua função

