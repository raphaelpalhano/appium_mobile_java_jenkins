#language: pt
#encoding: utf-8

Funcionalidade: acessar um app de lista de produtos

  Com um usuario válido acessar a lista de produtos e criar novos produtos


  @casoTest_1
  Esquema do Cenario:  usuario que cria conta com dados inválidos
    Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "<usuario>" e senha "<senha>" e confirmar "<confirmarSenha>"
    E clica no botao para efetuar o cadastro
    Entao deve exibir uma mensagem "Senhas não conferem"

    Exemplos:
    |usuario|senha|confirmarSenha|
    | rafa | rafa123 | diferente123 |
		

  @casoTest_1
  Cenario:  usuario que cria conta com dados válidos
  	Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "juca" e senha "juca221" e confirmar "juca221"
    E clica no botao para efetuar o cadastro
    Entao deve exibir um botao escrito "LOGAR"


  @casoTest_1
  Esquema do Cenario: o usuario válido que faz login e acessa o sistema
  	Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "juca" e senha "juca221" e confirmar "juca221"
    E clica no botao para efetuar o cadastro
    Quando preenche o campo de usuario "<ususario>" e senha "<senha>"
    E efetua o login com senha inválida e válida
    Entao deve exibir uma mensagem escrito "<mensagem>"
    
    Exemplos: 
    |	ususario|  senha | mensagem|
    |	juca	  | juca213	|	Usuário ou senha inválidos|		
		|	juca		| juca221 |	Lista de produtos|

	
  @casoTest_1
  Esquema do Cenario: o usuario que acessa o app com conta válida e efetua compra de um produto
  	Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "<usuario>" e senha "<senha>" e confirmar "<confirmarSenha>"
    E clica no botao para efetuar o cadastro
    Quando preenche o campo de usuario "<usuario>" e senha "<senha>"
    E efetua o login com senha válida
    * acessa o produto com nome de "<nome>"
    Quando exibir um titulo "Detalhes do produto" com nome do produto "Chuteira" iniciar a compra
    * e efetua a compra do produto
    E acessar a tela de "Pagamentos"
    Entao deve aparecer a tela de "Lista de pagamentos" com quantidade "1"
    
    Exemplos: 
    |	usuario	|  senha 	| 	confirmarSenha    |      nome 		|
		|	juca		| juca221 |  		juca221   	  	|			Chuteira	|
		        
		
  	
	@casoTest_1
  Esquema do Cenario: acessar o app Alura Sport Apk e compra dois produtos
  	Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "<usuario>" e senha "<senha>" e confirmar "<confirmarSenha>"
    E clica no botao para efetuar o cadastro
    Quando preenche o campo de usuario "<usuario>" e senha "<senha>"
    E efetua o login com senha válida
    * acessa o nome do produto e efetua a compra
    """
    Chuteira
    Bola de futebol
    """
    E acessar a tela de "Pagamentos"
    Entao deve aparecer a tela de "Lista de pagamentos" com quantidade "2"
    
    Exemplos: 
    |	usuario	|  senha 	| 	confirmarSenha    |      nome 		|
		|	juca		| juca221 |  		juca221   	  	|			Chuteira	|
		        
		

	@casoTest_1
  Esquema do Cenario: acessar o app Alura Sport Apk e compra todos produtos
  	Dado que acessei o app de loja
    Quando acessou formulario de cadastro
    E preencher o usuario "<usuario>" e senha "<senha>" e confirmar "<confirmarSenha>"
    E clica no botao para efetuar o cadastro
    Quando preenche o campo de usuario "<usuario>" e senha "<senha>"
    E efetua o login com senha válida
    * acessa o nome do produto e efetua a compra
    """
    Chuteira
    Bola de futebol
    Camisa
    Bermuda
    """
    E acessar a tela de "Pagamentos"
    Entao deve aparecer a tela de "Lista de pagamentos" com quantidade "4"
    
    Exemplos: 
    |	usuario	|  senha 	| 	confirmarSenha    |      nome 		|
		|	juca		| juca221 |  		juca221   	  	|			Chuteira	|
		        