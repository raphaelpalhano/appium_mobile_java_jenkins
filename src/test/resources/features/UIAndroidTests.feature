#language: pt
#encoding: utf-8

@android
Funcionalidade: Navegar no aplicativo android

  Cenario: Interagir com TexFields
		Dado que acessei o app android
		E acesso o menu "Views > TextFields"
		Quando informo o valor "Este é um teste automatizado"
		Entao deve o titulo da pagina deve apresentar o valor "Views/TextFields"
	
  Cenario: Acionar animacao View Flip
		Dado que acessei o app android
		E acesso o menu "Animation > View Flip"
		Quando aciono o "Flip"
		Entao os registros devem girar apresentando o valor "Le Five"