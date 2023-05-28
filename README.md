# Project: Deploying a network database through API calls
This project aims to create a network database using Java/Python and Neo4j to store and connect data from SCB (Statistics Sweden). SCB provides datasets about the census and other important information about Sweden. The objective is to design an application that can read data from the SCB API and populate the Neo4j database based on a specific design. The project also includes creating a simple dashboard to demonstrate some of the datasets.
## Introduction
SCB, Statistics Sweden (https://www.scb.se) publishes datasets about the census and other important information about Sweden. There is a need to connect this data across other studies for cross-theme analysis.

This project aims to design a network database to store data from SCB. A Java-based application will be created using the official API to read the data and push it into Neo4j based on the design. The objective is to track new data points and changes to the selected sources in the network database. Additionally, a simple dashboard will be demonstrated using some of the datasets.

## Installation
1. Clone the repository: `git clone https://github.com/alexhasan99/StatTrack`
2. Install the required dependencies for the Java environment accordingly.
3. Set up a Neo4j database and configure the connection details.
4. Obtain API keys or access credentials from SCB (Statistics Sweden) to access the required datasets.
5. Set up the necessary environment variables or configuration files to store the API keys securely.

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Data Sources](#data-sources)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Data Sources
The project includes data from various themes. Here is a list of the datasets considered for this project:

| Theme              | Table Name                                                                                                        | URL                                                                                                 |
|--------------------|------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| Education          | Number of students 16-64 years of age by region of the educational institution, sex, type of studies, and more  | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__UF__UF0507/StudiedeltagandeSK/)   |
| Education          | Population 16-64 years of age by residential region, sex, age, and more                                          | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__UF__UF0507/StudiedeltagandeK/)     |
| Environment        | Population in localities by type of dwelling and region                                                           | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__MI__MI0810__MI0810B/BefTatortTypBostReg/) |
| Environment        | Public green space in localities by region and land cover                                                         | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__MI__MI0805__MI0805A/GYMaTackeAllmToReg/) |
| Household Finances | Number of households and average number of persons per household by region, tenure, and more                    | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0111__HE0111A/HushallT30/)    |
| Household Finances | Housing expenditures per household and number of households by region, type of tenure, and more                 | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0202/HE0202T02/)              |
| Household Finances | Total earned income for persons registered in the national population register by region, education, and more    | [Link](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0110__HE0110A/SamForvInk1c/)  |

## Usage
1. Initialize the application and establish a connection with the SCB API.
2. Fetch the required datasets using the provided API endpoints.
3. Transform the data into a suitable format for storage in the Neo4j database.
4. Push the data into the Neo4j database based on the defined database schema.
5. Implement functionality to track new data points and changes to the selected sources in the network database.
6. Build a simple dashboard to visualize and interact with the datasets.
7. There is a picture that have all names for Relationsships & nodes

## Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to submit a pull request or open an issue in the repository. Please adhere to the code of conduct when contributing.

## License
This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code for non-commercial and commercial purposes.

## Database Design
![StatTrack (4)](https://github.com/alexhasan99/StatTrack/assets/121833434/b8126643-e7fc-48ff-aa11-7004b0fc1048)


