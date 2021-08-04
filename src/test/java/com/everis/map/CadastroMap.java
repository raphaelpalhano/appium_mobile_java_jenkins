package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CadastroMap extends BasePage {

	public CadastroMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/cadastro_usuario_botao_cadastrar")
	protected MobileElement botaoCadastrar;
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/erro_cadastro")
	protected MobileElement getTextMensagemErro;
	
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_logar")
	protected MobileElement nomeDoBotaoLogin;
	
	
	
	protected MobileElement campoDeTexto(String valorCampo) {
		By campoTexto = By.id("br.com.alura.aluraesporte:id/"+valorCampo+"");
		MobileElement campoTextoCadastro = this.driver.findElement(campoTexto);
		return campoTextoCadastro;

	}
	
	

}