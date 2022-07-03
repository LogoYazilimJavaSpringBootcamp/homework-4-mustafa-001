### NoSQL Veritabanları
NoSQL veritabanları ilişkisel veritabanlarının eksikliklerini gidermek için ortaya çıkmışlardır. 
NoSQL scaling ve dağıtılmış veritabanı uygulamalarında ilişkisel veritabanlarına göre avantajlar sağlarlar.
NoSQL veritabanları çeşitlerine göre anahtar-değer çifti, doküman, graph olarak ayrılabilirler. Structured verinin yanı sıra unstructured veri de saklayabilirler ve schema güncellemelerinde SQL veritabanlarını kıyasla daha esnektirler.

CAP (Consistency, Availability, Partition Tolerance ) teoremi açısından bakıldığında NoSQL veritabanları consistency arka planda tutarak performans ve uptime'ı yüksek tutmaya çalışırlar. Çoğu NoSQL "eventual consistency"i destekler, yani veri anında tüm düğümlerde aynı olmasa da eninde sonunda hepsi eşitlenecektir.

MongoDB doküman tabanlı bir NoSQL veritabanıdır. Veriler document ve collection denilen yapılar içinde tutulurlar. MongoDB kullanıcıları veri eklerken daha önceden belirlenmiş bir schemaya uymak zorunda değildirler. MongoDB'nin sharding desteği sayesinde birden fazla sunucuda veriler tutulabilir ve load balancing yapılabilir; bu sayede erişilebilirliği ve hızı yüksek bir veri sistemi uygulanabilir.

Couchbase yine doküman tabanlı, memcached protokolü ile uyumlu bir NoSQL veritabanıdır. Birden fazla makinede clusterlanabilecek şekilde tasarlanmış, kolay bir şekilde dikey scale'lebilecek bir yapıdadır. Couchbase sadece disk üzerinde, cache görevinde çalışan memcached'e ek olarak diske kaydedilebilme, veri çoklama, data partitioning gibi özelliklere de sahiptir.
CAP teoremi açısından Couchbase CP (Consistency, Partition Tolerance) olarak gelirken  birden fazla cluster kullanarak AP sistem olarak ayarlanabilir. 

Redis in-memory (hafızada duran) anahtar-değer veritabanı, cache ve message-brokerdır. Yani birden fazla görevde kullanılabilecek bir yapıdadır. Disk yerine hafızadan çalıştığı için Redis bir hız avantajına sahiptir. Bu sayede veritabanı olamasını yanı sıra cache olarak da kullanılabilir.