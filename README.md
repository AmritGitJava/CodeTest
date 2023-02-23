# CodeTest for Telecom operator

Implemented as per requirement.

Using Softwares and Language:

Java 8
MySql 8
Spring-boot 2.3.1
Spring JPA
Intellij IDEA
REST API implementation

Tables created by code while run application.
mvn clean spring-boot:run

Once tables created in MySql database, comment "spring.jpa.hibernate.ddl-auto = create" property in application.properties file.

POST method to insert data in MySql database: http://localhost:8080/api/v1/customer
Sample request:
{
    "customerName":"Name",
    "phone":[
        {
            "phoneNumber":"121113456739"
        },
        {
            "phoneNumber":"14231267189123"
        }
    ]
}

GET method to get all phone numbers: http://localhost:8080/api/v1/customer/phoneNumbers

GET method to get all phone numbers of a single customer: http://localhost:8080/api/v1/customer/{customerName}/phoneNumbers

PATCH method to activate phone number: http://localhost:8080/api/v1/customer/phoneNumbers/{phoneNumber}
