package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginMap extends BasePage {

	public LoginMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_cadastrar_usuario")
	protected MobileElement acessarBotaoCadastrar;
	
	protected MobileElement botaoDeAcesso(String valorBotao) {
		By botaoValor = By.id("br.com.alura.aluraesporte:id/"+valorBotao+"");
		MobileElement botaoGeral = this.driver.findElement(botaoValor);
		return botaoGeral;

	}
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_usuario")
	protected MobileElement campoUsuario;
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_senha")
	protected MobileElement campoSenha;
	
	
	protected MobileElement preencherCampo(String valorCampo) {
		By campoValor = By.id("br.com.alura.aluraesporte:id/"+valorCampo+"");
		MobileElement pegandoCampo = this.driver.findElement(campoValor);
		return pegandoCampo;
	}
	
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_cadastrar_usuario")
	protected MobileElement botaoAcessarCadastro;
	
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_logar")
	protected MobileElement botaoLogin;

	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/mensagem_erro_login")
	protected MobileElement mensagemDeFalha;
	
	
	@AndroidFindBy(xpath = "android.widget.TextView")
	protected MobileElement tituloListaProduto;
	
	
}