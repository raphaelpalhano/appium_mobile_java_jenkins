package com.everis.pages;

import com.everis.map.CadastroMap;

public class CadastroPage extends CadastroMap {

	public void preencherCamposCadastro(String usuario, String senha, String confirmarSenha) {
		campoDeTexto("input_nome").sendKeys(usuario);
		campoDeTexto("input_senha").sendKeys(senha);
		campoDeTexto("input_confirmar_senha").sendKeys(confirmarSenha);	
	}
	
	public void clicarEmBotaoCadastrar() {
		botaoCadastrar.click();
	}
	
	public String valorMensagemComErro() {
		return getTextMensagemErro.getText();
	}

	
	public String valorDoBotaoLogin() {
		return nomeDoBotaoLogin.getText();
	}
	

	

}