package br.as.devops.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "C:\\Programas\\drivers\\seleniumDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher descrição
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("13/06/2021");
			
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
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// Acionar Add To
			driver.findElement(By.id("addTodo")).click();
						
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("13/06/2021");
			
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
