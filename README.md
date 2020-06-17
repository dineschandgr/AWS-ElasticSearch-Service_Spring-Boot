# ElasticSearch_SpringBoot
Elastic Search ELK stack using Spring Boot

There are 2 resource flows in this project

1. CRUD Operation using ElasticsearchRepository - CustomerController
  
  - to create,update,delete,retrieve data from the indexes
  - The retrieve operation is limited
  
2. Query data from indexes using DSL (Domain Specific Language) - ManualSearchResource
  - Retrieve data from index using criteria like multiple fields, wildcard etc
  

There are 2 use cases in this project and both use different indexes

1. CRUD operations and retrieval is performed in the index specified by the application i.e elasticsearch1
2. Logging the application logs by Logstash in a index named logstash*

Prerequisite

1. Download ElasticSearch zip and start ElasticSearch using elasticsearch.bat. Elastic search runs on port 9200
2. Download Kibana zip and specify ElasticSearch url localhost:9200. start Kibana.bat after that. Kibana runs on port 5601
3. Download logStash.zip and specify log file url and ElasticSearch url localhost:9200. 
4. Create logstash.conf file inside the bin folder to specify the input,filter and output stages. start logstash after that

Steps to execute

1. Create a model object called Customer with the annotation 
   @Document(indexName="elasticsearch_14062020",type="customer",shards=2)
2. Create a CustomerRepository
   CustomerRepository extends ElasticsearchRepository<Customer, String>
3. This repository inherits all the apis from Spring Data JPA and hence it is similar to be connecting to other DB. Only difference is 
   data is indexed and stored in files in the disk
4. Specify location of logs in application.properties. This is to store logs in centralized location and will be read by logstash
5. call this endpoint in postman to insert data http://localhost:8080/saveCustomers
   {
    "id":"7",
    "firstName":"xxx",
    "lastName":"xxx",
    "age":xx
	}
6. call this endpoint to retrieve data http://localhost:8080/findByFirstName/{firstName}
7. call this endpoint to search by either firstname or age http://localhost:8080/manual/search/multifields/{firstName}/{age}
8. call this endpoint to search by a single value in both firstName and lastName field. Also it searches by wildcard *          http://localhost:8080/manual/search/searchall/{text}
9. Click on management menu in Kibana and create index pattern by filtering the index. 
    - elasticsearch1 for crud data
    - logstash* for logs data
10. Go to discover menu and select and index and search any data either crud data or logs data
11. Create visualizations based on the searched data
12. Go to dashboards and add the creaed visualizations as shown below
 
<img width="900" alt="Kibana Visualization" src="https://github.com/dineschandgr/ElasticSearch_SpringBoot/blob/master/Search_results_screenshot/kibana-visualization.bmp">

Kibana Search

<img width="900" alt="Kibana Search" src="https://github.com/dineschandgr/ElasticSearch_SpringBoot/blob/master/Search_results_screenshot/kibana-search.PNG">

Search Results from Query DSL

1. multifields_firstName_age

<img width="900" alt="multifields_firstName_age" src="https://github.com/dineschandgr/ElasticSearch_SpringBoot/blob/master/Search_results_screenshot/multifields_firstName_age.bmp">

2. search_firstName_lastName_wildcard.bmp

<img width="900" alt="search_firstName_lastName_wildcard.bmp" src="https://github.com/dineschandgr/ElasticSearch_SpringBoot/blob/master/Search_results_screenshot/search_firstName_lastName_wildcard.bmp">

