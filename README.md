# gigaboutique
Latest project for OC 

git link : https://github.com/BenoitABZ/gigaboutique

Gigaboutique is an spring cloud architecture platform witch allow customer to easily find best discount on clothes sold on the web. It can be shoes, jeans, tee shirt...
Each product are grouped by category. 

features :

. Backend scrap from websites:

     -all items (name, brand, images, old price, new price, discount, size and availability, sex...)
     -The seller (number of reviews, three last reviews, score, logo...)
                         
  Scrapping technology is based on XML language and easily configurable
  
- As a customer i can :

     - sign up and sign in (signing up is required to access the list of products)
     - access to a list of product and their details
     - be redirected to the seller's website
     - for each product, customer can see dealer's informations and reviews
     - filter by category, sex and brand
     - create a wish-list by clicking on a logo
                        
  products unavailable are automatically deleted from the wish-list and customers are notified
                        
  On the next release customer may also:
 
     - apply discount cashback manually added by the customer
     - suscribe to a newsletter
     - contact customer relationship
 
- As a admin i can :

     - configure the number of items scraped from each website
     - configure the number of items displayed
     - configure the number of reviews to fetch
     - configure database parameters
     - configure security parameters
     - add keywords for categorization
     - add a new website to scrap
 
   On the next release admin may also:
 
     - have their own IHM instead of properties or .yml files
     - apply discount cashback manually added by the admin 
                       
 - Spring boot microservices : 
   
     - Spring config : 
                       - Centralize all .properties and .yml config files located in github repository 
     
                       - Each microservices retrieve their congifuration here
                       
     - Spring eureka : All microservices instances are registered into eureka --> allow load balancing and healthcheck
                       
     - Spring gateway : Centralize endpoints and route each request to corresponding microservices
                       
     - giga-userservice : implement users, roles and wish-list business rules, authentication and authorization
                       
     - giga-productservice : implement all items business rules and authorization
                       
     - giga-vendeurservice : implement all sellers and reviews business rules and authorization
                       
     - giga-clientservice : implement all controllers linked with thymeleaf templates
                       
     - giga-batchvendeurservice : Scheduled batch that scrap every sellers and their reviews
                      
     - giga-batchproductservice : Scheduled batch that scrap every products
                       
     - giga-batchnotifyservice : 
                                 - Scheduled batch that check if a product of a user's wish-list is unavailable
     
                                 - Send mail to warn customer
                       
    On the next release : 
     
    - giga-admin : coupled with actuator, it allow real time congiguration without restart any server
  
  - Databases : PostgreSQL 10.0 platine
                        
    - db.giga.user
                        
    - db.giga.product
                        
    - db.giga.seller
                        
  - CI/CD : TRAVIS CI
                        
  - To deploy : Databases ans microservices (except batch) are dockerized (directory /gigaboutique/docker/dev) : 
  
    - to run databases only : docker-compose up -d
                       
    - to run all the application, run the script : ./gigaboutique.sh
                       
   
   Browser : localhost:8007
  
  
  
                       
                       
                       
                        

