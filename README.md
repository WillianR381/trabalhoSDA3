# Em construção 

# A fazer
- Terminar o relátorio
- Fazer vídeo tutorial de como iniciar o projeto
- Fazer vídeo rodando o projeto e mostrando ele rodando 

# Requisitos
- Necessário o Java instalado na máquina , no qual a versão 8 utilizada no desenvolvimento. Versão do jdk"1.8.0_362".
- Os shell scripts só foram testadas nas distros linux. No caso testado no Ubuntu 22.04 .

# Iniciar o projeto
- Abrir na pasta raiz do projeto, no caso trabalhoSDA3
- Fazer o build para criar o jar do projeto
- Executar o script para iniciar os processos `./iniciaTodosProcessos.sh `. Será aberto 5 processos, 1 Servidor, 2 gerentes e 2 vendedores. 
- Caso queira derrubar o servidor e inicia-lo basta rodar o comando `./iniciaServidor.sh`

# Entidades cadastradas 
As operações de venda e busca só podem ocorrer utilizando os produtos e vendedores cadastrados no banco (Arquivo Database.java, método insereRegistros), no caso são:

## Produtos
- arroz
- feijao
- macarrao

## Vendedores
- carlos
- roberto
- junior