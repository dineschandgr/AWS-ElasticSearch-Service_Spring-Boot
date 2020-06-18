# AWS-ElasticSearch-Service_Spring-Boot

Connecting to AWS Elastic Search using Spring Boot running in localhost

Prerequisite

1. Create an Elastic Search cluster in AWS in t2.small instance which provides 0.5 GB of RAM in free tier
2. AWS ElasticService Https rest calls have to be signed with AWS credentials, hence an interceptor {@link HttpRequestInterceptor} is required to sign every API calls with credentials. 

AWS Elastic Search provides the following endpoints

1. ElasticSearch Cluster Rest endpoint
2. Kibana end point

Config

Provide the following configuration in the application. properties file

1. aws.es.region=ap-southeast-1
2. aws.es.endpoint=Aws_ealtic_cluster_endpoint
3. #aws.serviceName=es
4. aws.es.accessKeyId=accessId
5. aws.es.secretKey=secretkey


In this test project, access policy is set to open to all to access both services but in production more strict policies should be applied

Kibana needs cognito authentication in real projects

There are 2 resource flows in this project

1. CRUD Operation using ElasticsearchRepository - CustomerController
  
  - to create,update,delete,retrieve data from the indexes
  - The retrieve operation is limited
  
2. Query data from indexes using DSL (Domain Specific Language) - ManualSearchResource
  - Retrieve data from index using criteria like multiple fields, wildcard etc
  
The use case in this project is a crud flow using ElasticSearchRepository in Spring Data JPA 

CRUD operations and retrieval is performed in the index specified by the application i.e elasticsearch

The indexes can be viewed in the aws elastic search console or the following endpoints

url to find index

http://localhost:9200/_cat/indices/?v

url to find data inside index

http://localhost:9200/elasticsearch/_search

*elasticsearch is the index name


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
    - elasticsearch for crud data
    
10. Go to kibana endpoint url and maviage to discover menu , select the index and search any data either crud data
11. Create visualizations based on the searched data


Notes:

1. To connect to aws elastic search service using logstash, run logstash in a EC2 instance or install logstash as a container inside the microservice.
2. The logstash can be used in AWS ECS, Fargate container or Kubernetes pods
3. Logstash will then stream logs to the cluster and then we can view the logs from kibana
4. For logging and monitoring, our application need not connect to the ElasticSearch cluster. Only logstash config needs to be updated to use the elasticsearch cluster endpoint

 

