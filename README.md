# License Project

## Overview

License is a Java-based application designed to manage and distribute software licenses. It offers a robust platform for creating, storing, and controlling software licensing agreements.

## Features

+ Customer Management: Efficiently manage customer data.
+ Project Management: Organize and maintain project details.
+ License Issuance: Generate and distribute software licenses.
+ Crypto Key Management: Securely handle cryptographic keys for enhanced security.

## Technologies

+ Java
+ Maven
+ Spring Boot

## Getting Started
### Prerequisites

+ Java JDK 11+
+ Maven

### Installation

Clone the repository:

    git clone https://github.com/AliDinpanah/License.git

Navigate to the project directory:

    cd License

Build the project using Maven:

    mvn clean install

### Running the Application

Run the application using Maven:
 
    mvn spring-boot:run

## Usage

+ Create a Customer: POST /customer with customer details.
+ Create a Project: POST /project with project details.
+ Issue a License: POST /license with license specifications.
+ Generate Crypto Key: POST /cryptoKey for secure transactions.

## Contributing

Contributions to improve License are welcome. Please follow the standard pull request process.
## License

Distributed under the MIT License. See LICENSE file for more information.
## Contact

Ali Dinpanah - [Github](https://github.com/AliDinpanah)
