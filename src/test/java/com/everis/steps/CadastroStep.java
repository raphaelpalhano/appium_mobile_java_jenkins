package com.everis.steps;

import static org.junit.Assert.assertEquals;

import com.everis.pages.CadastroPage;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;


public class CadastroStep {

	@E("preencher o usuario \"(.*?)\" e senha \"(.*?)\" e confirmar \"(.*?)\"$")
	public void informarValor(String usuario, String senha, String confirmarSenha) {
		CadastroPage cadastroPage = new CadastroPage();
		cadastroPage.preencherCamposCadastro(usuario, senha, confirmarSenha);
	}
	
	@E("^clica no botao para efetuar o cadastro$")
	public void apresentouTitulo() {
		CadastroPage cadastroPage = new CadastroPage();
		cadastroPage.clicarEmBotaoCadastrar();
	}
	
	@Entao("^deve exibir uma mensagem \"(.*?)\"$")
	public void confirmandoAMensagem(String valorMensagem) {
		CadastroPage cadastroPage = new CadastroPage();
		assertEquals(valorMensagem, cadastroPage.valorMensagemComErro());
	}
	
	@Entao("^deve exibir um botao escrito \"(.*?)\"$")
	public void transicaoParaPaginaDeLogin(String valorBotao) {
		CadastroPage cadastroPage = new CadastroPage();
		assertEquals(valorBotao, cadastroPage.valorDoBotaoLogin());
	}
}