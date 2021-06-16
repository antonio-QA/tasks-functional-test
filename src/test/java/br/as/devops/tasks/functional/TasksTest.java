package br.as.devops.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Programas\\drivers\\seleniumDrivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.86:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.86:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher descrição
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("15/06/2021");
			
			// acionar Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
		} finally {
			
			// Fechar browser
			driver.quit();			
			
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
						
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("15/06/2021");
			
			// acionar Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		} finally {			
			driver.quit();					
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
						
			// Preencher descrição
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			
			// acionar Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
		} finally {			
			driver.quit();					
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
						
			// Preencher descrição
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("12/06/2021");
			
			// acionar Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally {			
			driver.quit();					
		}
		
	}
	
}
