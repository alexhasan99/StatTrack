# Project: Deploying a network database through API calls

SCB, Statistics Sweden (https://www.scb.se) publishes datasets about the census and other important information about Sweden. There is a need to connect this data across other studies for cross-theme analysis.

Design a network database to store data from SCB. Create a Python/Java-based application using the official API to read the data and push the same into Neo4j based on your design. The objective is to be able to track new data points and changes to the selected sources in the network database. Demonstrate a simple dashboard with some of the datasets.

Create an application that can take an API target and incorporate the following data sources:

1. [Number of students 16-64 years of age by region of the educational institution, sex, type of studies the autumn term and the location of the educational institution in relation to the municipality of residence. Year 1999 - 2020](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__UF__UF0507/StudiedeltagandeSK/)
2. [Population 16-64 years of age by residential region, sex, age, type of studies the autumn term. Year 1993 - 2020](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__UF__UF0507/StudiedeltagandeK/)
3. [Population in localities by type of dwelling and region. Year 2000 - 2020](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__MI__MI0810__MI0810B/BefTatortTypBostReg/)
4. [Public green space in localities by region and land cover. Year 2015](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__MI__MI0805__MI0805A/GYMaTackeAllmToReg/)
5. [Number of households and average number of persons per household by region, tenure and type of dwelling (excluding one- or two-dwelling buildings). Year 2012 - 2021](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0111__HE0111A/HushallT30/)
6. [Housing expenditures per household and number of households by region, type of tenure and type of household. Year 2015 - 2017](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0202/HE0202T02/)
7. [Total earned income for persons registered in the national population register during the whole year by region, level of educational, sex and age. Year 2000 - 2020](https://www.statistikdatabasen.scb.se/pxweb/en/ssd/START__HE__HE0110__HE0110A/SamForvInk1c/)

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

## Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to submit a pull request or open an issue in the repository. Please adhere to the code of conduct when contributing.

## License
This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code for non-commercial and commercial purposes.
