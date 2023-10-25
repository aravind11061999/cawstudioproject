package com.interview;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
public class test {
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@BeforeTest
	public  void launch() throws InterruptedException 	
	{
	driver.manage().window().maximize();	
	driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
	
	
	}
	
	@Test
	public  void InsertData() 
	{
		WebElement TableDataBtn = driver.findElement(By.xpath("//summary"));
		wait.until(ExpectedConditions.elementToBeClickable(TableDataBtn));
		TableDataBtn.click();
		WebElement TextBox=driver.findElement(By.xpath("//textarea[@id=\"jsondata\"]"));
		TextBox.click();
		TextBox.clear();
		TextBox.sendKeys("[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\r\n"
				+ "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]");
		WebElement RefreshBtn=driver.findElement(By.xpath("//button[@id=\"refreshtable\"]"));
		RefreshBtn.click();
		
		//Actual input data
		String[] name = {"Bob", "George", "Sara", "Conor","Jennifer"};
		String[] Age = {"20", "42", "42", "40","42"};
		String[] gender = {"male", "male", "female", "male","female"};
		
		//dummy data in array
		String[] name1={"Volvo", "BMW", "Ford", "Mazda","asdasd"}; 
		String[] Age1 ={"Volvo", "BMW", "Ford", "Mazda","asdasd"};;
		String[] gender1 = {"Volvo", "BMW", "Ford", "Mazda","asdasd"};
		
		//copying website data to local array to compare
		List<WebElement> nameweb= driver.findElements(By.cssSelector("table#dynamictable tr td:nth-of-type(1)"));
		for(int i=0;i<name.length;++i)
		{  WebElement ele=nameweb.get(i);
			name1[i]=ele.getText();
		}
		List<WebElement> Ageweb= driver.findElements(By.cssSelector("table#dynamictable tr td:nth-of-type(2)"));
		for(int i=0;i<Age.length;++i)
		{  WebElement ele=Ageweb.get(i);
			Age1[i]=ele.getText();
		}
		List<WebElement> Genderweb= driver.findElements(By.cssSelector("table#dynamictable tr td:nth-of-type(3)"));
		for(int i=0;i<gender.length;++i)
		{  WebElement ele=Genderweb.get(i);
		 gender1[i]=ele.getText();
		}
		
		
		// checking if data in website equal to given data
		for(int i=0;i<name.length;++i)
		{  
			
		
			Assert.assertEquals(name[i].equals(name1[i]), true);
			Assert.assertEquals(Age[i].equals(Age1[i]), true);
			Assert.assertEquals(gender[i].equals(gender1[i]), true);
			/*System.out.println(Age[i]);
			System.out.print(Age1[i]);
			System.out.println(gender[i]);
			System.out.print(gender1[i]);*/
			
		}
		// To find duplicate name in given data
		for (int i = 0; i < name.length-1; i++)
		{
			for (int j = i+1; j < name.length; j++)
            {
                if ((name[i].equals(name[j]) ) && (i != j))
                  System.out.println("Duplicate Element : "+name[j]);
                
            }
        }
       
		
	}
	
	@AfterTest
	public  void close()
	{driver.close();
		}
	}
	
	

