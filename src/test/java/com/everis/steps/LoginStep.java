package com.everis.steps;

import static org.junit.Assert.assertEquals;

import com.everis.pages.ListaProdutoPage;
import com.everis.pages.LoginPage;
import com.everis.util.Hook;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class LoginStep {

	@Dado("^que acessei o app de loja$")
	public void acessarApp() {
		Hook.openApplicationAndroid("/app/alura_esporte.apk");
	}
	
	@Quando("^acessou formulario de cadastro$")
	public void acessarMenuTextFields() {
		LoginPage inicialPage = new LoginPage();
		inicialPage.acessarFormularioCadastro();		
	}
	
	
	@Quando("^preenche o campo de usuario \"(.*?)\" e senha \"(.*?)\"$")
	public void preencher_campo_usuario_senha_login(String usuario, String senha) {
		LoginPage inicialPage = new LoginPage();
		inicialPage.preencherCamposParaFazerLogin(usuario, senha);
	}
	
	@E("^efetua o login com senha inválida e válida$")
	public void clicarBotaoLogin() {
		LoginPage inicialPage = new LoginPage();
		inicialPage.clicarBotaoLogin();
	}
	
	@E("^efetua o login com senha válida$")
	public void acessarLista() {
		LoginPage inicialPage = new LoginPage();
		inicialPage.clicarBotaoLogin();
	}
	
	
	
	@Entao("^deve exibir uma mensagem escrito \"(.*?)\"$")
	public void verificando_mensagem_de_login_invalido(String mensagem) {
		LoginPage inicialPage = new LoginPage();
		switch(mensagem) {
		case "Usuário ou senha inválidos":
			assertEquals(mensagem, inicialPage.textoDeFalhaNoLogin());
			break;
		case "Lista de produtos":
			ListaProdutoPage listaProduto = new ListaProdutoPage();
			assertEquals(mensagem, listaProduto.tituloDaListaDeProdutos(mensagem));
			break;
		}
		
	}
		
		

	
	
	
}
