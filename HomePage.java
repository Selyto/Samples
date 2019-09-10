package pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement txtSearchbox;
	
	@FindBy(id="searchDropdownBox")
	private WebElement ddSearch;
	
	@FindBy(xpath="//input[@value='Go']")
	private WebElement btnSearch;
	
	
	@FindBy(xpath="//div[contains(@data-cel-widget,'search_result_')]")
	List<WebElement> searchResults;
	
	String pName = "//span[contains(@class,'a-color-base a-text-normal')]";
	String pDate = "//span[contains(@class,'a-color-secondary a-text-normal')]";
	String pPrice = "//span[@class='a-price-whole']";
	String rating = "//span[contains(text(),'stars')]";//"//a[@class='a-popover-trigger a-declarative']/i/span";
	String ratingText = "//div[@class='a-section a-spacing-small a-text-center']//span[@class='a-size-base a-color-secondary']";
	String prime = "//i[contains(@class,'a-icon a-icon-prime a-icon-medium')]";
	String bookDetails = "//h2/following-sibling::div[@class='a-row a-size-base a-color-secondary']";
	
	//data-component-type  s-product-image
	
	//@FindBy(xpath="")
		
	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.visibilityOfElementLocated((By) txtSearchbox));
	}
	
		public void searchProduct(String category, String productName) {
			selectCategory(category);
			txtSearchbox.sendKeys(productName);
			btnSearch.click();
		}
		
		public void selectCategory(String term) {
			Select category = new Select(ddSearch);
			category.selectByVisibleText(term);
		}
		
		public void printProductDetails() {
			boolean primeflag =false,bookdetailsflag = false;
			//String book
				WebElement searchResult = searchResults.get(0);
				System.out.println("Name : " + searchResult.findElement(By.xpath(pName)).getText());
				System.out.println("Price : " + searchResult.findElement(By.xpath(pPrice)).getText());
				if(!searchResult.findElement(By.xpath(rating)).isDisplayed()) {
					Actions act1 = new Actions(driver);
					act1.moveToElement(searchResult.findElement(By.xpath(rating))).perform();
				}
				Actions act = new Actions(driver);
				act.moveToElement(searchResult.findElement(By.xpath(rating))).perform();
				System.out.println("Rating : " + searchResult.findElement(By.xpath(ratingText)).getText().split(" ")[0]);
				try {
					if(searchResult.findElement(By.xpath(prime)).isDisplayed()) {
						System.out.println("Prime Available"); 
					}
				}
				catch(Exception e) {
					
				}
				try {
					String x = searchResult.findElement(By.xpath(bookDetails)).getText();
					String[] splitDetails = x.split("\\|");
					System.out.println("Author : " + splitDetails[0].substring(3, splitDetails[0].length()-1)); 
					System.out.println("Published Date : " + splitDetails[1].trim()); 
				}
				catch(Exception e) {
					
				}
				
		}
		public void getBookDetails(WebElement searchResult) {
			
		}
	
}
