# The-Cientistas
Projeto Spring Boot em sala de aula

Um jogo no qual o usuário pode "roubar" atributos de mulheres cientistas.

## Requisitos
- Java 21
- PostgreSQL
- NetBeans ou outra IDE com suporte a Maven/Spring Boot

O Banco de dados com as cientistas e outras informações estão em um arquivo .txt dentro da pasta do projeto. 
Os arquivos estão na aba Master do github.

Passo a passo para rodar:
1. Crie uma base chamada "Fenomenas" no banco de dados
2. Configure no application properties a senha e usuario do seu postgree. Por padrão o usuário e a senha são postgres e ifsp, respectivamente.
3. Rode o projeto usando uma plataforma como o Netbeans 30 para criar as tabelas. O banco ainda estará vazio.
4. Cole o banco que está no arquivo txt em uma query no postgree.
5. Rode a aplicação novamente. Funcionará desta vez! 
