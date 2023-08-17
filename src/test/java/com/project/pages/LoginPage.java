package com.everis.pages;

import com.everis.map.LoginMap;

public class LoginPage extends LoginMap {

	public void acessarFormularioCadastro() {
		//botaoLogin.click();
		botaoDeAcesso("login_botao_cadastrar_usuario").click();
	}
	
	public void preencherCamposParaFazerLogin(String usuario, String senha) {
		campoUsuario.sendKeys(usuario);
		campoSenha.sendKeys(senha);
	}

	public void clicarBotaoLogin() {
		botaoLogin.click();
		
	}

	public String textoDeFalhaNoLogin() {
		return mensagemDeFalha.getText();
		
	}

	public String tituloMaiorListaDeProduto() {
		return  tituloListaProduto.getText();
	}
	
	
}