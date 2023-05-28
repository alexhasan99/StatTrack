Project: Deploying a Network Database through API Calls
This project aims to create a network database using Java/Python and Neo4j to store and connect data from SCB (Statistics Sweden). SCB provides datasets about the census and other important information about Sweden. The objective is to design an application that can read data from the SCB API and populate the Neo4j database based on a specific design. The project also includes creating a simple dashboard to demonstrate some of the datasets.

Table of Contents
Introduction
Installation
Usage
API Targets
Data Sources
Contributing
License
Introduction
SCB, Statistics Sweden, publishes valuable datasets related to various themes such as education, environment, household finances, population, and living conditions. This project focuses on creating a network database that can connect and analyze data across different themes for cross-theme analysis. The database is built using Neo4j, and the data is fetched from SCB using their official API.

The main objectives of this project are as follows:

Design a network database schema to store SCB data.
Develop a Java/Python application that interacts with the SCB API to retrieve data.
Push the retrieved data into the Neo4j database based on the predefined database design.
Implement functionality to track new data points and changes in the selected sources within the network database.
Create a simple dashboard to showcase and visualize some of the datasets.
Installation
To run this application, follow these steps:

Clone the repository to your local machine.
Ensure you have Java/Python and the necessary dependencies installed.
Set up a Neo4j database and configure the connection details.
Install the required libraries for making API calls and interacting with Neo4j.
Run the application using the appropriate command or IDE.
Usage
To use this application, follow these steps:

Ensure the installation steps are completed successfully.
Configure the API target(s) in the application to specify which datasets to fetch.
Run the application to retrieve the data from the SCB API.
The data will be stored in the Neo4j database according to the predefined database design.
Utilize the dashboard functionality to visualize and analyze the datasets stored in the network database.
API Targets
The following API targets should be considered for this project:

Number of students 16-64 years of age by region of the educational institution, sex, type of studies, the autumn term, and the location of the educational institution in relation to the municipality of residence. Year 1999 - 2020
Population 16-64 years of age by residential region, sex, age, type of studies, the autumn term. Year 1993 - 2020
Population in localities by type of dwelling and region. Year 2000 - 2020
Public green space in localities by region and land cover. Year 2015
Number of households and average number of persons per household by region, tenure, and type of dwelling (excluding one- or two-dwelling buildings). Year 2012 - 2021
Housing expenditures per household and number of households by region, type of tenure, and type of household. Year 2015 - 2017
Total earned income for persons registered in the national population register during the whole year by region, level of education, sex, and age. Year 2000 - 2020
Disposable income for households by region, type of households, and age. Year 2011 - 2020
Income inequality indicators by region. Year 2011 - 2020
Population per region by age and sex. Year 2010 - 2021
Number of persons by region, age, type of household, number of children, and sex. Year 2011 - 2021
Income standard among children and young persons aged 0-21 living at home by region, sex, and age. Year 2014 - 2020
Data Sources
The project includes data from various themes. Here is a list of the datasets considered for this project:

Theme	Table Name	URL
Education	Number of students 16-64 years of age by region of the educational institution, sex, type of studies, and more	Link
Education	Population 16-64 years of age by residential region, sex, age, and more	Link
Environment	Population in localities by type of dwelling and region	Link
Environment	Public green space in localities by region and land cover	Link
Household Finances	Number of households and average number of persons per household by region, tenure, and more	Link
Household Finances	Housing expenditures per household and number of households by region, type of tenure, and more	Link
Household Finances	Total earned income for persons registered in the national population register by region, education, and more	Link
Household Finances	Disposable income for households by region, type of households, age, and more	Link
Household Finances	Income inequality indicators by region and more	Link
Population	Population per region by age and sex	Link
Population	Number of persons by region, age, type of household, number of children, and sex	Link
Living Conditions	Income standard among children and young persons aged 0-21 living at home by region, sex, and age	Link
Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to submit a pull request or open an issue in the repository. Please adhere to the code of conduct when contributing.

License
This project is licensed under the MIT License. Feel free to use, modify, and distribute the code for non-commercial and commercial purposes.
