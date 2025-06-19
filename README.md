# Magento E-commerce Automation – Selenium Framework

This project is an automated testing framework for a Magento-based e-commerce application. Built using **Selenium WebDriver**, **TestNG**, and **Java**, it covers core functionalities like user login, product search, add to cart and checkout, and validations.

---

## 🔧 Tech Stack

- **Programming Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Browser Support:** Chrome, Firefox  , Edge
- **Reporting:** ExtentReports

---

## 📌 Features Covered

- ✅ User login
- 🔍 Product search and filtering
- 🛒 Add to cart & wishlist
- 💳 Checkout and order placement
- 📊 Assertion of expected vs actual results

---

## 🛠️ Project Structure

magento_e-commerce-automation-selenium/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── abstractcomponents/ 
│ │ │ ├── pages/ # Page Object Model classes
│ │ │ ├── resources/ 

├── src/
│ ├── test/
│ │ ├── java/
│ │ │ ├── data/ # Data classes
│ │ │ ├── testcases / # Test classes
│ │ │ ├── testcomponents/ 
├── reports
├── testng.xml # TestNG suite configuration
├── pom.xml # Maven dependencies


---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sangita12/magento_e-commerce-automation-selenium.git
   
2. Import into Eclipse or IntelliJ

3. Install dependencies:

   Maven: mvn clean install

4. Update test data & base URL in config (if needed)

5. Run tests:

    From IDE: Run testng.xml

    Or from terminal: mvn test

## 🚀 Reports
Test reports (like ExtentReports) are generated under: 
---- reports

🙌 Contribution
Pull requests and enhancements are welcome!
Make sure to follow standard coding practices and include test coverage.


