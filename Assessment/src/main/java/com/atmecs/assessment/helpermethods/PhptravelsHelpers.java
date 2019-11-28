package com.atmecs.assessment.helpermethods;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.helpermethods.DateHandlingHelpers;
import com.atmecs.assessment.pageactions.AssertionHelpers;
import com.atmecs.assessment.pageactions.ExplicitWaitHelpers;
import com.atmecs.assessment.pageactions.PageActions;
import com.atmecs.assessment.pageactions.PageActionsScrollDown;
import com.atmecs.assessment.pageactions.TextHandlingHelpers;
import com.atmecs.assessment.reports.LogReport;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

public class PhptravelsHelpers {
	Properties properties;
	PageActions pageactions = new PageActions();
	Properties testdata;
	LocatorSeparator separatelocator= new LocatorSeparator();
	LogReport log= new LogReport();
	AssertionHelpers assertionhelpers= new AssertionHelpers();
	int numberofdetails =4;
	PageActionsScrollDown pageactionsscrolldown= new PageActionsScrollDown();
	ExplicitWaitHelpers explicitwait = new ExplicitWaitHelpers();
	DateHandlingHelpers datehandling=new DateHandlingHelpers();
	TextHandlingHelpers texthandlinghelpers= new TextHandlingHelpers();
	
	public PhptravelsHelpers() throws IOException {
		
	    properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	    testdata=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	    
	 	}
	
	public void invoiceDetailsValidation(WebDriver driver,int randomNumber) throws InterruptedException
	{
		String actualcurrentdate=driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.dateinmyaccountpage"))).getText();
	    System.out.println(actualcurrentdate);
	    String expectedcurrentdate=datehandling.getDateFormat("dd MMMM yyyy");
	    Assert.assertEquals(actualcurrentdate, expectedcurrentdate);
	   
	    LocalDateTime datetime1 = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");  
	    String actualCurrentTime = datetime1.format(format);   
	    String expectedCurrentTime= texthandlinghelpers.gettingTextValue(driver,properties.getProperty("loc.timeinmyaccountspage"));
	    String expectedTimeArray[]=expectedCurrentTime.split(":");
	    Assert.assertEquals(actualCurrentTime, expectedTimeArray[0]+":"+expectedTimeArray[1]);

   	    String myaccountpageinvoicedetails[]=new String[numberofdetails];
   	    String myaccountpageinvoicedetailsarray[]=new String[numberofdetails];
		
   	    String myaccountpagebookingidlocatorfirstpart=properties.getProperty("loc.myaccountpagebookingidlocatorfirstpart");
		String myaccountpagebookingidlocatorsecondpart=Integer.toString(randomNumber);
		String myaccountpagebookingidlocatorthirdpart=properties.getProperty("loc.myaccountpagebookingidlocatorthirdpart");
		String myaccountpagebookingidtextfulltext =myaccountpagebookingidlocatorfirstpart+myaccountpagebookingidlocatorsecondpart+myaccountpagebookingidlocatorthirdpart;
	
		WebElement invoicebutton=driver.findElement(By.xpath((properties.getProperty("loc.invoicebutton")+"####"+"]//div[@class='col-md-2 offset-0 o1']/a").replaceAll("####",randomNumber+"")));
		WebElement previousinvoicebutton=driver.findElement(By.xpath((properties.getProperty("loc.invoicebutton")+"####"+"]//div[@class='col-md-2 offset-0 o1']/a").replaceAll("####",(randomNumber-1)+"")));
        pageactionsscrolldown.pageScrollDownTillElementVisible(driver, previousinvoicebutton);
        
	  
		String myaccountpageinvoicecompletedata=driver.findElement(By.xpath("(//span[@class='grey'])["+randomNumber+"]")).getText();
		myaccountpageinvoicedetailsarray=myaccountpageinvoicecompletedata.split("\n");
		
 for(int arrayindex=0;arrayindex<numberofdetails-1;arrayindex++) {
	    for(int index=0;index<numberofdetails-1;index++) {
	    	
	       myaccountpageinvoicedetails[index]=myaccountpageinvoicedetailsarray[arrayindex];
	 	  
	       }
        }
        String value= driver.findElement(By.cssSelector("strong[class='size22']>div")).getAttribute("innerText");
        System.out.println(value);
 
		explicitwait.explicitwaitForElementToBeClickable(driver,invoicebutton);
	
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", invoicebutton);
		Thread.sleep(3000);
		Set<String>windowHandle=driver.getWindowHandles();
		for(String window:windowHandle) {
			 driver.switchTo().window(window);
		}
		
		String invoicepagedetails[]=new String[numberofdetails];
   
	    String invoicepagestatustext=driver.findElement(By.cssSelector(".page-wrapper.page-confirmation.bg-light div.success-box.unpaid .content")).getText();
	    System.out.println(invoicepagestatustext);
	    invoicepagedetails[0]=texthandlinghelpers.splittingStringText(driver,"loc.invoicepagebookinid",3);
	    Assert.assertEquals(invoicepagedetails[0],myaccountpageinvoicedetails[2]);
	   
	    invoicepagedetails[1]=texthandlinghelpers.splittingStringText(driver, "loc.invoicepagebookingnumber", 3);
	    Assert.assertEquals(invoicepagedetails[1],myaccountpageinvoicedetails[5]);
	    
	    String invoicepagestatusarray[]= null;
	    invoicepagestatusarray=invoicepagestatustext.split("\\s");		
	    String invoicepagestatus=invoicepagestatusarray[invoicepagestatusarray.length];		
	    Assert.assertEquals(myaccountpageinvoicedetails[numberofdetails], invoicepagestatus);		
	    
	    
	    	}
	
	public String[] gettingMultipleText(WebDriver driver,String elementLocator,int noOfTexts) {
		
		String actualMultipleText[]= new String[noOfTexts];
		List<WebElement> listoftexts = driver.findElements(separatelocator.separatingLocators(properties.getProperty(elementLocator)));
		for( int noOfIteration=noOfTexts;noOfIteration<listoftexts.size();noOfIteration++) {
			
			actualMultipleText[noOfIteration]=listoftexts.get(noOfIteration).getText();
			
		}
		return actualMultipleText;
		
	}
	
	   

	}
	

