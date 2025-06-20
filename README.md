Dario Bebidas - Site de vendas de bebidas
(https://dario-bebidas.vercel.app/)

Projeto freelancer desenvolvido com Java, Spring Boot, MySQL, Postman para testar a API, Railway para deploy. No Front-End foi usado Node.js, React.js, Tailwind CSS e vercel para deploy. Este sistema foi criado para um familiar que está com a ideia de fazer vendas de bebidas.

---

● Funcionalidades

-  Visualização de produtos disponíveis
- Adicionar e remover itens do carrinho
-  Finalização de pedido com:
  - Endereço
  - Forma de pagamento
  - Telefone de contato
- Histórico de pedidos do usuário com:
  - Data e hora do pedido
  - Itens pedidos
  - Endereço e telefone do cliente
  - Status atual do pedido
-  Registro e login com autenticação via JWT (email e senha)
-  Área "Minha Conta" com informações do usuário e todos os seus pedidos
-  Cancelamento de pedidos possível apenas se o status estiver em **Aguardando** ou ** Em Preparação**

● Área de Administração (restrita a usuários com role ADMIN)

- Tela exclusiva que lista **todos os pedidos feitos no site**
- Possibilidade de atualizar o status dos pedidos:
  - Aguardando
  - Em preparação
  - Saiu para entrega
  - Entregue
  - Cancelado
- Visualização completa dos pedidos:
  - Itens
  - Dados do cliente
  - Endereço
  - Telefone
  - Data/Hora
  - Forma de pagamento
- Opção "Entrar como administrador" disponível na tela de register e login, com a função de fazer um login automático com a ROLE de Admin, para recrutadores acessarem o site como Administradores e obterem todas as permissões de um Administrador, como ver o painel de Admin com todos os pedidos dos usuários e com possibilidade de atualizar os status de cada pedido.

---

● Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **MySQL**
- **DTOs** para comunicação entre camadas
- **Arquitetura em camadas**:
  - Controllers
  - Services
  - Models
  - **JWT** para autenticação
  - DTOs
  - Repositories
  - Configs
- **Git & GitHub** para versionamento do código
- **Postman** para testes de API REST
- **Railway** para deploy do back-end
- **Node.js**
- **React.js**
- **Vercel** para deploy do front-end 
