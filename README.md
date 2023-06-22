# trabalhoSDA3

## 📁 Descrição do projeto
Projeto desenvolvido para consolidar o conhecimento sobre os assuntos abordados nas aulas de Sistemas distribuidos, no caso estamos criando uma aplicação na arquitetura cliente-servidor, utilizamos o sockets para que os processos se comunicam através de mensagens, essa comunicação é feita através da rede local, implementação a detecção de falha da comunicação com servidor, . 

## Requisitos
- Necessário ter o Java 8 ou superior instalado na máquina. Versão do jdk "1.8.0_362".
- Git ( caso desejar clonar o projeto ) 

## 🛠️ Abrir e rodar o projeto
Caso queira gravamos um [Vídeo](https://drive.google.com/file/d/14DNM64LpQYE9q8KR5HHowkntDMpV7gh0/view) realizando as etapas abaixo :

### 🐧 Linux:
- Clonar o repositório
- Abrir na pasta raiz do projeto
- `cd scriptsIniciacaoLinux`
- Para iniciar o projeto basta executar o script `./iniciaTodosProcessos.sh`. Será aberto 5 processos : 1 Servidor, 2 gerentes e 2 vendedores.
- Para matar o servidor basta clicar no ícone 'X' no terminal referente ao processo **servidor**, com o servidor finalizado, basta selecionar um dos processo, digitar uma mensagem de operação, ou digitar enter, o processo vai verificar que o servidor não tá respondendo e iniciando a eleição do líder para assumir o papel de servidor principal.
- Escrevemos um script para iniciar o servidor novamente, basta executar `iniciaServidor.sh`   

Gravamos um [Vídeo](https://drive.google.com/file/d/1QdLnvW0hrELT_wSSOe0t58Mk5pI0XbmP/view) testando o projeto

### 🪟 Windows:
- Clonar o repositório
- Abrir na pasta raiz do projeto
- Acessar a pasta `scriptsIniciacaoWindows`
- Para iniciar o projeto basta executar (2 cliques) o script bat `./iniciaTodosProcessos.bat`. Será aberto 5 processos : 1 Servidor, 2 gerentes e 2 vendedores.
-  Para matar o servidor basta clicar no ícone 'X' no terminal referente ao processo **servidor**, com o servidor finalizado, basta selecionar um dos processo, digitar uma mensagem de operação, ou digitar enter, o processo vai verificar que o servidor não tá respondendo e iniciando a eleição do líder para assumir o papel de servidor principal.
- Escrevemos um script para iniciar o servidor novamente, basta executar (2 cliques) o bat `iniciaServidor.bat`   

## Entidades cadastradas 
As operações de venda e busca só podem ocorrer utilizando os produtos e vendedores cadastrados no banco (Podendo ser visto na classe [Database](https://github.com/WillianR381/trabalhoSDA3/blob/main/src/main/java/com/mycompany/trabalhosda3/config/Database.java#L80) ), no caso são:

## Produtos
- arroz
- feijao
- macarrao

## Vendedores
- carlos
- roberto
- junior
