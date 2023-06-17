# Em construção 

# Perguntas 

1. Por ser um processo sincrono, quando derruba o servidor principal ou a conexão com o servidor principal é estabelecida é preciso apertar enter ou qual valor referente a operação para reestabelecer a nova conexão podendo ser com o servidor ou servidor temporario. Assim estaria correto ? 
2. O requisito: Ao retornar, o servidor deve informar aos clientes e assumir novamente a responsabilidade, gostaria de saber se com a justificativa a seguir cumpre o requisito ? Antes do cliente estabelece a conexaõ com o servidor ele tenta a comunicação, caso não obtenha uma resposta ele segue para uma segunda etapa. No qual ele verifica se existe um lider elegido, caso sim  o cliente utilizará esse lider como servidor temporário, caso não existir um lider esse cliente inicia a eleição .
3. Qual a quantidade minima de cada processo (Gerente e Vendedor).

# Requisitos
- Necessário o Java instalado na máquina , no qual a versão 8 utilizada no desenvolvimento. Versão do jdk"1.8.0_362".
- Os scripts só abrangem distros linux. No caso testado no Ubuntu 22.04 .

# Iniciar o projeto
- Abrir na pasta raiz do projeto, no caso trabalhoSDA3
- Fazer o build para criar o jar do projeto
- Executar o script para iniciar os processos `./iniciaTodosProcessos.sh `. Será aberto 5 processos, 1 Servidor, 2 gerentes e 2 vendedores. 
- Caso queira derrubar o servidor e inicia-lo basta rodar o comando `./iniciaServidor.sh`

# Entidades cadastradas 
As operações de venda e busca só porem ocorrer utilizando os produtos e vendedores cadastrados no banco (Arquivo Database.java, método insereRegistros), no caso são:

## Produtos
- arroz
- feijao
- macarrao

## Vendedores
- carlos
- roberto
- junior