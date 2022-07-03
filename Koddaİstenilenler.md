### 1.Mysql veya PostgreSQL ile controller katmanlarının çalışabilmesi için gerekli repository katmanlarını yazın. Ayrıca isbasi-email-service kuyruktan veriyi okuduktan sonra gerekli model class’ını oluşturup tabloya kaydedin.(60 Puan)
`logo.repository` paketindeki interfaceler JpaRepository'den kalıtım alır, bunlar da Hibernate tarafından implemente edilir.

isbasi-email-service EmailDto nesnesini aldıktan sonra localhost:27017'de çalışan MongoDB'ye gönderiyor. [EmailRepository](isbası-email/src/main/java/com/isbasi/repository/EmailRepository.java) Spring Data yardımıyla gerekli olan kodların implemente edilmesini sağlar.

### 2.JDBC, JdbcTemplate ve Hibernate ile bir DAO katmanını yazın ve avantajlarını ve dezavantajlarını kendi görüşlerinizle beraber yazın. OOP’nin polimorfizm’den yararlanarak aynı tabloya üç yöntem ile CRUD işlemlerini yapan kodu yazınız. (30 Puan)

Hibernate ile veritabanına kaydedilen her nesne için gerekli SQL komutlarının otomatik yazılmasını sağlayabilir. Aynı zamanda Hibernate bir SQL çözümüne bağlı kalmamızı engelleyip hem PostgreSQL hem MySQL hem de OracleDB gibi farklı ürünlerle çalışabilecek kod yazabilmemizi sağlar. 

Hibernate lazy-loading mekanizması ile bağlı sınıflarının ihtiyaç olduğunda veritabınından çekilmesini sağlar. JDBC ve JdbcTemplate'te bu mekanizmayı kodlamak ayrı bir zaman ve emek ister.

Hibernate nesnelere field eklendiğinde bunu query'lere otomatik eklerken Jdbc ve JdbcTemplate'te her fieldın ayrı ayrı kodlanması gerekir.

Hibernate ve JdbcTemplate veritabanına bağlanma, schema oluşturma gibi adımları otomatik yaparken Jdbc bunları tek tek yapmamızı ister.

Hibernate otomatik olarak cache desteğiyle gelir, birden fazla yapılan querylerin sonuçlarını hafızada tutarak daha hızlı sonuçları getirip veritabanını gereksiz meşgul etmez.

Jdbc ve JdbcTemplate'te oldukça fazla basmakalıp (boilerplate) kod vardır. Hibernate ile bir kaç satırda gerçekleştirilebilecek işlem Jdbc'de oldukça uzundur.
JdbcTemplate databasete yapılacak olan işlemin detaylarını geliştiriciye açık ederken Hibernate bunları arka planda bırakabilir.
JdbcTemplate ile daha spesifik ve karmaşık optimizasyonlar yapılabilir.

`logo.repository.customerdao` paketindeki [CustomerDao](src/main/java/com/logo/repository/customerdao/CustomerDao.java) interface'i `JpaRepository` interfacine benzer bir şekilde gerekli methodları tanımlıyor.
[HibernateCustomerDao](src/main/java/com/logo/repository/customerdao/HibernateCustomerDao.java)
[JDBCTemplateCustomerDao](src/main/java/com/logo/repository/customerdao/JDBCTemplateCustomerDao.java)
[JdbcCustomerDao](src/main/java/com/logo/repository/customerdao/JdbcCustomerDao.java) bu interfacei implemente ediyor. 
[CustomerService](src/main/java/com/logo/service/CustomerService.java)'te bunlardan birini döndüren `getCurrentCustomerDao()` ile bu üç sınıftan biri `CustomerService`te kullanılıyor.

### 3.Aşağıdaki kavramları örneklerle açıklayın ve hangi problemi nasıl çözdüklerini anlatan bir makale yazın.(Medium’da paylaşıp linkini koyabilirsiniz.) (10 Puan)
* MongoDB,
* Coucbase,
* Redis



