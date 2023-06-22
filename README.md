# Em construção 

# A fazer
- Terminar o relátorio
- Fazer vídeo tutorial de como iniciar o projeto
- Fazer vídeo rodando o projeto e mostrando ele rodando 

# Requisitos
- Necessário o Java instalado na máquina , no qual a versão 8 utilizada no desenvolvimento. Versão do jdk"1.8.0_362".
- Os shell scripts só foram testadas nas distros linux. No caso  Ubuntu 22.04 .

# Iniciar o projeto
Gravamos um [Vídeo](https://drive.google.com/file/d/14DNM64LpQYE9q8KR5HHowkntDMpV7gh0/view) realizando as etapas abaixo :
- Clonar o repositório
- Abrir na pasta raiz do projeto
- `cd 
- Executar o script para iniciar os processos na pasta `./iniciaTodosProcessos.sh `. Será aberto 5 processos, 1 Servidor, 2 gerentes e 2 vendedores. 
- Caso queira derrubar o servidor e inicia-lo basta rodar o comando `./iniciaServidor.sh`
Gravamos um [Vídeo Testando o Projeto](https://drive.google.com/file/d/1QdLnvW0hrELT_wSSOe0t58Mk5pI0XbmP/view)

# Entidades cadastradas 
As operações de venda e busca só podem ocorrer utilizando os produtos e vendedores cadastrados no banco (Podendo ser visto na classe [Database](https://github.com/WillianR381/trabalhoSDA3/blob/main/src/main/java/com/mycompany/trabalhosda3/config/Database.java#L80), no caso são:

## Produtos
- arroz
- feijao
- macarrao

## Vendedores
- carlos
- roberto
- junior
