[Cevaplar ve kodda istenilenler.](Koddaİstenilenler.md)

[Makale](NoSQLVeritabanları.md)

localhost:8080 API dokümantasyonuna yönlendirir. localhost:5432'de postgreSQL, localhost:27017'de mongoDB, localhost:
15672'de RabbitMQ çalışmasını gerektirir.
İlk çalıştırışta veritabanına bir kaç veri yazar, GET metodlarıyla bunlar alınabilir, POST ile aynı şablon kullanılıp id
alanı 0 bırakılmak suretiyle yeni veri eklenebilir.
UPDATE yaparken pathvariable olarak güncellenmek istenen verinin id'si body'de de yeni veri eklenebilir. null bırakılan
alanlar değiştirilmez. İlişkilir olan alanları değiştirmek için diğer objede sadece id'nin verilmesi yeterlidir.
Örneğin:
```
// 336 numaralı Transaction'a 6 birim 336 numaralı Product'ı ekler.
PUT localhost:8080/transaction/336

{
"id": 0,
"documentNumber":null,
"type": "FIRECIKIS",
"date": "2022-07-05",
"description": "string",
"products": [
   {
   "id": 0,
   "productOrService": {
   "id": 336
   },
   "amount": 6
   }
]
}
```

2. Mysql veya PostgreSQL ile controller katmanlarının çalışabilmesi için gerekli repository katmanlarını yazın. Ayrıca
   isbasi-email-service kuyruktan veriyi okuduktan sonra gerekli model class’ını oluşturup tabloya kaydedin.(60 Puan)
3. JDBC, JdbcTemplate ve Hibernate ile bir DAO katmanını yazın ve avantajlarını ve dezavantajlarını kendi görüşlerinizle
   beraber yazın. OOP’nin polimorfizm’den yararlanarak aynı tabloya üç yöntem ile CRUD işlemlerini yapan kodu yazınız. (
   30 Puan)
4. Aşağıdaki kavramları örneklerle açıklayın ve hangi problemi nasıl çözdüklerini anlatan bir makale yazın.(Medium’da
   paylaşıp linkini koyabilirsiniz.) (10 Puan)

* MongoDB,
* Coucbase,
* Redis
