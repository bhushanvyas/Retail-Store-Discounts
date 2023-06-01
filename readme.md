# Retail Store Discounts Application

This is a Spring Boot Java application that calculates the net payable amount for a bill in a retail store. The application applies different discounts based on user types and categories of items.

## Table of Contents
- [Requirements](#requirements)
- [High-Level Design](#high-level-design)
- [Technologies Used](#technologies-used)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Requirements
The application meets the following requirements:
- Calculates the net payable amount for a given bill in a retail store
- Applies different discounts based on user types and categories of items
- Provides a simple and generic implementation using object-oriented programming principles
- Includes unit tests with high code coverage
- Provides a clear README.md file explaining how to run the code, execute the tests, and generate code coverage reports

## High-Level Design
The application is designed using the following key classes:

1. `BillGenerationRequest`: Represents a request object containing the bill information.
2. `BillResponse`: Represents a response object containing the calculated net payable amount and other details.
3. `Item`: Represents an item in the bill, including properties like name, quantity, price, and category.
4. `UserType`: Enumerates the different types of users: `EMPLOYEE`, `AFFILIATE`, and `CUSTOMER`.
5. `Category`: Enumerates the different categories of items, such as `GROCERY` and `OTHER`.
6. `DiscountCalculator`: Interface for different discount calculators.
7. `DiscountServiceImpl`: Implements the `DiscountService` interface and provides the implementation for calculating the net payable amount based on the bill and discount rules.

## Technologies Used
The application is built using the following technologies and frameworks:
- Java
- Spring Boot
- JUnit 4 for testing
- Mockito for mocking dependencies



## API Documentation
The application exposes a single API endpoint to calculate the net payable amount for a given bill.

API Endpoint: `/api/calculateNetPayableAmount`
Method: POST
Content-Type: application/json
Request Body: 
```json
{
  "items": [
    {
      "name": "Item 1",
      "quantity": 2,
      "price": 10.0,
      "category": "GROCERY"
    },
    {
      "name": "Item 2",
      "quantity": 3,
      "price": 20.0,
      "category": "OTHER"
    }
  ],
  "userType": "EMPLOYEE",
  "longTermCustomer": true
}
```
Response Body:
```json
{
  "totalAmount": 70.0,
  "netPaybleAmount": 45.0,
  "totalDiscount": 25.0,
  "items": [
    {
      "name": "Item 1",
      "quantity": 2,
      "price": 10.0,
      "category": "GROCERY"
    },
    {
      "name": "Item 2",
      "quantity": 3,
      "price": 20.0,
      "category": "OTHER"
    }
  ],
  "userType

": "EMPLOYEE",
  "longTermCustomer": true
}
```

## Testing
The project includes unit tests to verify the correctness of the calculations and the behavior of the components. To run the tests, execute the following command in the project directory:
```
mvn test
```



## Usage
To use the application in your project, follow these steps:

1. Include the `retail-store-discounts` dependency in your project's `pom.xml` file:
   ```xml
   <dependency>
       <groupId>com.retail.store</groupId>
       <artifactId>retail-store-discounts</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```
2. Import the necessary classes in your code:
   ```java
   import com.retail.store.retailStore.model.BillGenerationRequest;
   import com.retail.store.retailStore.model.BillResponse;
   import com.retail.store.retailStore.service.DiscountService;
   import com.retail.store.retailStore.service.DiscountServiceImpl;
   ```
3. Create an instance of the `DiscountService` and use it to calculate the net payable amount:
   ```java
   DiscountService discountService = new DiscountServiceImpl();
   BillGenerationRequest generationRequest = new BillGenerationRequest();
   // Set the necessary properties in the generationRequest object
   BillResponse billResponse = discountService.calculateNetPayableAmount(generationRequest);
   // Access the calculated values from the billResponse object
   ```

## Contributing
Contributions to this project are welcome. To contribute, please follow these steps:
1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -m "Add your message here"`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Submit a pull request

## License
This project is licensed under the [MIT License](LICENSE).
```

Feel free to modify the README.md file to include any additional information or sections relevant to your project.