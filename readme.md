## Setup and Run Commands

```bash
sudo -u postgres psql -c "CREATE DATABASE sales;"
sudo snap install ripgrep
sudo apt install maven
mvn -version
mvn compile
mvn test
mvn spring-boot:run
mvn clean install
mvn exec:java -Dexec.mainClass="com.example.Main"
mvn package
```

## Restore Database From SQL Dump

Use the provided SQL dump at `documents/database.sql`.
This file starts with `CREATE DATABASE SALES;`, so use one of the flows below.

```bash
# Full reset and restore (recommended)
sudo -u postgres psql -c "DROP DATABASE IF EXISTS sales;"
sudo -u postgres psql -c "CREATE DATABASE sales;"
sed '1d' ./documents/database.sql | sudo -u postgres psql -d sales
```

Alternative (keep database, reset schema only):

```bash
sudo -u postgres psql -d sales -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"
sed '1d' ./documents/database.sql | sudo -u postgres psql -d sales
```

Note: `could not change directory ... Permission denied` may appear when running `sudo -u postgres` from your home folder. It is usually a warning and can be ignored if the import continues.

## API URL

[http://localhost:8080](http://localhost:8080)

## Screenshots

![Sales App Screenshot 1](assets/sales_app_1.jpeg)
![Sales App Screenshot 2](assets/sales_app_2.jpeg)