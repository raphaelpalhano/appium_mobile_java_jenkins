package com.everis.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
//import java.text.Collator;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Finder;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.everis.util.Hook;
import com.everis.util.Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;

public class BasePage {

	protected AndroidDriver<MobileElement> driver = Hook.getDriver();
	protected ExtentTest extentTest = Hook.getExtentTest();
	protected ExtentReports extentReport = Hook.getExtentReports();
	protected Screen sikuli = Hook.getSikuli();
	protected App sikuliApp = Hook.getSikuliApp();
	protected boolean isSikuliAutomation = "sikuli".equals(Hook.getActiveAutomation());
	public final Logger logger = Logger.getLogger(BasePage.class);

	public BasePage() {

	}

	public boolean verificarQuantidadeDeImagensExibidas(String imagem, int quantImagens) throws FindFailed {
		posicionarMouseCentroTela();

		List<Match> totalImagens = new ArrayList<Match>();
		Iterator<Match> imagens = sikuli.findAll(imagem);

		for (Iterator<Match> img = imagens; img.hasNext();) {
			Match image = img.next();
			image.highlight();
			waitMilliseconds(100);
			image.highlight();
			totalImagens.add(image);
		}

		int total = totalImagens.size();

		if (total == quantImagens) {
			log("Total de [" + quantImagens + "] imagens como era esperado.");
			return true;
		}

		logFail("Total de imagens [" + total + "] nao era esperado. O esperado era [" + quantImagens + "]. ");
		return false;
	}

	public int retornarQuantidadeDeImagensExibidas(String imagem) throws FindFailed {
		posicionarMouseCentroTela();

		List<Match> totalImagens = new ArrayList<Match>();
		Iterator<Match> imagens;
		try {
			imagens = sikuli.findAll(imagem);
		} catch (FindFailed e) {
			imagens = null;
		}
		if (imagens != null) {
			for (Iterator<Match> img = imagens; img.hasNext();) {
				Match image = img.next();
				image.highlight();
				waitMilliseconds(100);
				image.highlight();
				totalImagens.add(image);
			}
		}
		int total = totalImagens.size();
		return total;
	}

	protected void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int seconds)", e);
		}
	}

	protected void waitMilliseconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int milliseconds)", e);
		}
	}

	protected WebElement waitElement(By by, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	protected WebElement waitElement(WebElement webElement, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}

	protected List<WebElement> waitElements(By by, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		return element;
	}

	protected boolean waitNotPresent(By by, int timeOutInSeconds) {
		AndroidDriver<MobileElement> driver = Hook.getDriver();
		waitMilliseconds(500);
		@SuppressWarnings("deprecation")
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		boolean isElementInvisible = false;
		try {
			isElementInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			return false;
		}
		return isElementInvisible;
	}

	protected boolean waitUntilElementHasValue(WebElement element, String text) {
		boolean isElementhasText = false;
		try {
			waitMilliseconds(500);
			@SuppressWarnings("deprecation")
			Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(1, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			isElementhasText = wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
			return isElementhasText;
		} catch (Exception e) {
			return isElementhasText;
		}
	}

	protected void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	protected boolean isElementDisplayed(By by) {
		boolean isElementPresent = false;
		boolean isElementDisplayed = false;
		isElementPresent = !driver.findElements(by).isEmpty();
		if (isElementPresent) {
			isElementDisplayed = driver.findElement(by).isDisplayed();
		}
		return isElementPresent && isElementDisplayed;
	}

	protected void waitLoading() {
		try {
			waitElement(By.id("loadingScreen"), 3);
		} catch (Exception e) {
		}
		waitNotPresent(By.id("loadingScreen"), 120);
	}

	private String saveScreenshotInRelatoriosPath() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int milliseconds = calendar.get(Calendar.MILLISECOND);
		String datahora = "" + day + month + year + "_" + hours + minutes + seconds + milliseconds;
		String screenShotName = datahora + ".png";
		File scrFile = null;
		try {
			if (isSikuliAutomation) {
				scrFile = new File("target/report/html/img/" + screenShotName);
				try {
					ImageIO.write(sikuli.capture(App.focusedWindow()).getImage(), "png", scrFile);
				} catch (Exception e) {
					ImageIO.write(sikuli.capture().getImage(), "png", scrFile);
					logger.debug("Erro ao obter screenshot do app, possivelmente o app '" + sikuliApp.getName()
							+ "' nao esta em execucao." + "Obtido screenshot da tela inteira.");
				}
			} else {
				scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("target/report/html/img/" + screenShotName));
			}
		} catch (IOException e) {
			logger.error("Erro ao salvar screenshot.", e);
		}
		return screenShotName;
	}

	protected void log(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
		try {
			extentTest.pass(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
		} catch (IOException e) {
			logger.error("Erro ao executar logPrint()", e);
		}
	}

	public void logPrintFail(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
		try {
			extentTest.fail(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
		} catch (IOException e) {
			logger.error("Erro ao executar logPrintFail()", e);
		}
	}

	protected void logInfo(String log) {
		extentTest.info(log);
	}

	protected void logSkip(String log) {
		extentTest.skip(log);
	}

	protected void logFail(String log) {
		extentTest.fail(log);
	}

	protected void logError(String log) {
		extentTest.error(log);
	}

	protected void logPass(String log) {
		extentTest.pass(log);
	}

	protected ExtentTest createChildStart(String strNomeTeste) {
		ExtentTest parentTest = Hook.getExtentTest();
		ExtentTest child = parentTest.createNode(strNomeTeste);
		return child;
	}

	protected void childLogFail(ExtentTest child, String log) {
		child.fail(log);
	}

	protected void childLogPass(ExtentTest child, String log) {
		child.pass(log);
	}

	protected void childLogInfo(ExtentTest child, String log) {
		child.info(log);
	}

	protected boolean imageExists(String imageFile, float similarity0to100) {
		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100));
		boolean imageExists = image != null;
		return imageExists;
	}

	protected boolean imageExists(String imageFile, float similarity0to100, double timeOutInSeconds) {
		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100), timeOutInSeconds);
		boolean imageExists = image != null;
		return imageExists;
	}

	protected boolean assertImageExists(String imageFile, float similarity0to100) {
		try {
			sikuli.wait(imageFile, 15);
		} catch (FindFailed e1) {
			logInfo("Imagem nao foi exibida" + e1);
		}
		try {
			FileUtils.copyFile(new File(ImagePath.find(imageFile).getPath()),
					new File("target/report/html/img/" + imageFile));
			extentTest.info("Imagem esperada: " + imageFile,
					MediaEntityBuilder.createScreenCaptureFromPath("img/" + imageFile).build());
		} catch (IOException e) {
			logger.error("Erro ao executar o metodo assertImageExists()", e);
		}

		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100));
		boolean imageExists = image != null;

		if (imageExists) {
			image.highlight();
			waitMilliseconds(30);
			log("Imagem encontrada com " + new DecimalFormat("#.##").format((image.getScore() * 100))
					+ " % de similaridade");
			image.highlight();
		} else {
			log("A imagem " + imageFile + " nao foi encontrada com a similaridade de " + similarity0to100 + " %");
		}
		return imageExists;
	}

	protected int click(String imageFile) {
		try {
			return sikuli.click(imageFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int click(String imageFile, float similarity0to100) {
		try {
			return sikuli.click(new Pattern(imageFile).similar(similarity0to100 / 100));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int click(String imageFile, float similarity0to100, int x, int y) {
		try {
			return sikuli.click(new Pattern(imageFile).similar(similarity0to100 / 100).targetOffset(x, y));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int doubleClick(String imageFile) {
		try {
			return sikuli.doubleClick(imageFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String getText(String imageFile, float similarity0to100) {
		clearClipboard();
		click(imageFile, similarity0to100);
		sikuli.keyDown(Key.CTRL);
		sikuli.keyDown(Key.INSERT);
		sikuli.keyUp();
		return getClipboard();
	}

	protected String getText() {
		clearClipboard();
		sikuli.type("c", Key.CTRL);
		return getClipboard();
	}

	protected String getText(String imageFile, float similarity0to100, int x, int y) {
		clearClipboard();
		click(imageFile, similarity0to100, x, y);
		type("a", Key.CTRL);
		type("c", Key.CTRL);
		return getClipboard();
	}

	public void clearClipboard() {
		StringSelection selection = new StringSelection("");
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(selection, selection);
	}

	public String getClipboard() {
		String clipboardText = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			waitMilliseconds(500);
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			logger.error("Erro ao obter texto da area de transferencia", e);
		}
		return clipboardText;
	}

	protected Match wait(String imagem, int tempoMaximoSegundos) {
		try {
			return sikuli.wait(imagem, tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected Match wait(SikuliElement imagem, int tempoMaximoSegundos) {
		try {
			return sikuli.wait(imagem, tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected Match wait(String imagem, int tempoMaximoSegundos, float similarity0to100) {
		try {
			return sikuli.wait(new Pattern(imagem).similar(similarity0to100 / 100), tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected String waitRegionChange(Region region, int timeoutInSeconds) {
		long timeoutExpiredMs = System.currentTimeMillis() + timeoutInSeconds * 1000;
		String eventsName = region.onChange();
		region.observe(timeoutInSeconds);
		region.stopObserver();
		if (System.currentTimeMillis() >= timeoutExpiredMs) {
			throw new RuntimeException("Timeout waitRegionChange " + timeoutInSeconds + " seconds: " + region);
		}
		return eventsName;
	}

	public void pressionarAtalho(String atalho) {
		String[] teclas = atalho.replaceAll("ctrl", Key.CTRL).replaceAll("shift", Key.SHIFT).replaceAll("alt", Key.ALT)
				.replace("space", Key.SPACE).split(" ");
		int quantTeclas = teclas.length;
		if (teclas[0].matches(Key.CTRL + "|" + Key.SHIFT + "|" + Key.ALT)) {
			if (quantTeclas == 2) {
				sikuli.type(teclas[1], teclas[0]);
			} else if (quantTeclas == 3) {
				sikuli.type(teclas[2], teclas[0] + teclas[1]);
			} else if (quantTeclas == 4) {
				sikuli.type(teclas[3], teclas[0] + teclas[1] + teclas[2]);
			}
		} else if (quantTeclas == 1) {
			sikuli.type(atalho);
		}
		log("Pressionou o atalho: " + atalho);
	}

	protected App appFocus(String appName) {
		sikuliApp = App.focus(appName);
		Hook.setSikuliApp(sikuliApp);
		return sikuliApp;
	}

	protected void multiType(String arg, int count) {
		for (int i = 0; i < count; i++) {
			type(arg);
		}
	}

	protected int type(String texto) {
		return sikuli.type(texto);
	}

	protected int type(String text, String modifiers) {
		return sikuli.type(text, modifiers);
	}

	protected int paste(String texto) {
		return sikuli.paste(texto);
	}

	protected void multiClick(int clicks, String imageFile) {
		try {
			for (int i = 0; i < clicks; i++) {
				sikuli.click(imageFile);
				waitMilliseconds(500);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * OBS: Quando o titulo da janela a ser aguardada possuir acentuacao ou
	 * caracteres especiais, utilize no paramentro windowTitle apenas parte do
	 * titulo da janela, sem informar o caractere especial. Exemplo: No caso do
	 * titulo se chamar "Módulo de Pesquisa", utilize apenas "de Pesquisa"
	 * 
	 * @param windowTitle
	 */
	public void waitWindow(String windowTitle, int timeoutInSeconds) {
		boolean isWindowOpened = false;
		int pollingEvery = 500;
		double timeout = (double) timeoutInSeconds * 1000.0;
		double elapsedTime = 0.0;

		while (elapsedTime < timeout) {
			appFocus(windowTitle);
			isWindowOpened = sikuliApp.getPID() > 0 && sikuliApp.toString().contains(windowTitle);
			if (!isWindowOpened) {
				waitMilliseconds(pollingEvery);
				elapsedTime += (double) pollingEvery;
			} else {
				break;
			}
		}

		if (!isWindowOpened) {
			logger.error("The window containing the text '" + windowTitle + "' in the title was not opened.");
			throw new RuntimeException(
					"The window containing the text '" + windowTitle + "' in the title was not opened.");
		}
	}

	public void closeWindow(String windowTitle) {
		waitWindow(windowTitle, 5);
		App.close(windowTitle);
	}

	/**
	 * No mac o App.focus nao funciona bem com parte o title do aplicativo. Fuciona
	 * apenas com o nome do aplicativo. como alternativa, este metodo recebe uma
	 * imagem referente a tela que se esteja aguardar.
	 * 
	 * @param windowTitle
	 * @param anyExpectedSikuliElementInWindow
	 * @param timeoutInSeconds
	 */
	public void waitWindow(String windowTitle, SikuliElement anyExpectedSikuliElementInWindow, int timeoutInSeconds) {
		anyExpectedSikuliElementInWindow.wait(timeoutInSeconds);
		if (Utils.isWindows()) {
			waitWindow(windowTitle, timeoutInSeconds);
		}
	}

	public void aguardarDownloadArquivo() {
		String downloadFilepath = System.getProperty("user.dir") + "/target/temp";
		Utils.waitForFileExistsInPath(downloadFilepath, 10);
		waitMilliseconds(500);
	}

	public void clearTextField() {
		sikuli.type("a", Keys.CTRL);
		sikuli.type(Key.DELETE);
	}

	public Location posicionarMouseCentroTela() {
		Location centerOfScreen = sikuli.getScreen().getCenter();
		try {
			sikuli.mouseMove(centerOfScreen);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
		return centerOfScreen;
	}

	public String selecionarTudoECopiar() {
		sikuli.keyDown(Key.CTRL);
		sikuli.type("a");
		waitMilliseconds(500);
		sikuli.type("c");
		sikuli.keyUp();
		return getClipboard();
	}

	public List<List<String>> csvToDataTable(String csv, String separator) {
		List<List<String>> dataTable = new ArrayList<List<String>>();
		List<String> lstLinhas = Arrays.asList(csv.split("\\r?\\n"));
		for (String linha : lstLinhas) {
			dataTable.add(Arrays.asList(linha.split(separator)));
		}
		return dataTable;
	}
	
	public boolean isImageEquals(String expectedImage, String resultImagePath) throws IOException {
		Finder resultImage = new Finder(ImageIO.read(new File(resultImagePath)));
		File file = new File(resultImagePath);
		File file2 = new File(expectedImage);

		extentTest.info("Expected image: ", MediaEntityBuilder.createScreenCaptureFromPath(file2.getAbsolutePath()).build());
		extentTest.info("Result image: ", MediaEntityBuilder.createScreenCaptureFromPath(file.getAbsolutePath()).build());
		resultImage.find(expectedImage);

		if (resultImage.hasNext()) {
			Double comparisonScore = resultImage.next().getScore();

			boolean isImageEquals = comparisonScore > 0.70;

			if (isImageEquals) {
				log("Completely equal images. Measured similarity: " + comparisonScore);
				return true;
			} else {
				logPrintFail("Images not equals. Measured similarity: " + comparisonScore);
				return false;
			}
		}

		logPrintFail("Completely different images.");
		return false;
	}
	
	public void swipeFromBottomToUp(int swipeCount) {
		int counter = 0;
		while (counter < swipeCount) {
			swipeFromBottomToUp();
			counter ++;
		}
	}
	
	public void swipeFromBottomToUp() {
		new TouchAction(driver)
		.press(new PointOption<>().withCoordinates(100, 600))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(200)))
		.moveTo(new PointOption<>().withCoordinates(100, 50))
		.release()
		.perform();
	}

	public void swipeFromUpToBottom() {
		new TouchAction(driver)
		.tap(new PointOption<>().withCoordinates(100, 50))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(100)))
		.moveTo(new PointOption<>().withCoordinates(100, 200))
		.release()
		.perform();
	}

}