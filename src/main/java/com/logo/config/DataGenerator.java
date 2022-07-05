package com.logo.config;

import com.logo.model.*;
import com.logo.model.enums.FirmType;
import com.logo.model.enums.InvoiceType;
import com.logo.model.enums.StockTransactionType;
import com.logo.model.enums.UnitType;
import com.logo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;

@Component
public class DataGenerator {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final AddressRepository addressRepository;
    private final ProductAmountPairRepository productOrServiceAmountPairRepository;
    private final StockTransactionRepository stockTransactionRepository;


    @Autowired
    public DataGenerator(UserRepository userRepository,
                         CustomerRepository customerRepository,
                         ProductRepository productRepository,
                         InvoiceRepository invoiceRepository,
                         AddressRepository addressRepository,
                         ProductAmountPairRepository productOrServiceAmountPairRepository,
                         StockTransactionRepository stockTransactionRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
        this.productOrServiceAmountPairRepository = productOrServiceAmountPairRepository;
        this.stockTransactionRepository = stockTransactionRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        if (!invoiceRepository.findAll().isEmpty() && !userRepository.findAll().isEmpty()){
            return;
        }
        System.out.println("Database is empty, inserting placeholder items.");
        var user1 = userRepository.save(User.builder()
                .name("Ali")
                .surname("Serçe")
                .email("aliserce@email.com")
                .firmType(FirmType.INDIVIUAL)
                .password("aliserçe1995")
                .build());
        var user2 = userRepository.save(User.builder()
                .name("Mehmet")
                .surname("Kartal")
                .email("mehmetkartal@email.com")
                .firmType(FirmType.CORPORATE)
                .password("beşiktaş1903")
                .build());
        var add1 = addressRepository.save(Address.builder()
                .country("TR")
                .province("İstanbul")
                .build());
        var add2 = addressRepository.save(Address.builder()
                .country("TR")
                .province("Ankara")
                .build());
        var add3 = addressRepository.save(Address.builder()
                .country("TR").province("İzmir").build());
        var customer1 = customerRepository.save(Customer.builder()
                .name("Filiz Buğday")
                .address(add1)
                .age(30)
                .isActive(true)
                .user(user1)
                .build());
        var customer2 = customerRepository.save(Customer.builder()
                .name("Halil SivasllıoğullarıgilKıran")
                .address(add2)
                .age(25)
                .isActive(false)
                .user(user1)
                .build());
        var customer3 = customerRepository.save(Customer.builder()
                .name("Müşteri 3")
                .address(add3)
                .age(35)
                .isActive(true)
                .user(user2)
                .build());
        var product1 = productRepository.save(((Product) Product.builder()
                .name("Elma")
                .currency(Currency.getInstance("TRY"))
                .CESSRate(BigDecimal.ZERO)
                .isActive(true)
                .purchasePrice(BigDecimal.valueOf(10))
                .salesPrice(BigDecimal.valueOf(12))
                .unitType(UnitType.Kilogram)
                .vatRate(BigDecimal.valueOf(0.08))
                .barcode("VVVYYYZZZ")
                .withholdingRatePercent(BigDecimal.valueOf(100))
                .user(user1)
                .build()));

        var product2 = productRepository.save(Product.builder()
                .name("Telefon")
                .currency(Currency.getInstance("TRY"))
                .CESSRate(BigDecimal.valueOf(22))
                .isActive(true)
                .purchasePrice(BigDecimal.valueOf(10))
                .salesPrice(BigDecimal.valueOf(12))
                .unitType(UnitType.Kilogram)
                .vatRate(BigDecimal.valueOf(0.08))
                .withholdingRatePercent(BigDecimal.valueOf(100))
                .barcode("AAABBBCCC")
                .user(user2)
                .build());
        var service = productRepository.save(Product.builder()
                .name("İnşaat")
                .currency(Currency.getInstance("TRY"))
                .CESSRate(BigDecimal.valueOf(20))
                .isActive(true)
                .purchasePrice(null)
                .salesPrice(BigDecimal.valueOf(10))
                .unitType(UnitType.Saat)
                .vatRate(BigDecimal.valueOf(0.01))
                .withholdingRatePercent(BigDecimal.valueOf(40))
                .user(user1)
                .build());
        var productAmountPair1 = productOrServiceAmountPairRepository.save(new ProductAmountPair(product1, 1));
        var productAmountPair2 = productOrServiceAmountPairRepository.save(new ProductAmountPair(product2, 2));
        var productAmountPair3 = productOrServiceAmountPairRepository.save(new ProductAmountPair(service, 3));
        var productAmountPair4 = productOrServiceAmountPairRepository.save(new ProductAmountPair(product2, 4));
        var productAmountPair5 = productOrServiceAmountPairRepository.save(new ProductAmountPair(product2, 5));
        var productAmountPair6 = productOrServiceAmountPairRepository.save(new ProductAmountPair(product1, 6));

        var invoice1 = invoiceRepository.save(Invoice.builder()
                .address(add1)
                .currency(Currency.getInstance("TRY"))
                .customer(customer1)
                .invoiceDate(LocalDateTime.now().minusDays(3))
                .deliveryDate(LocalDateTime.now().plusDays(5))
                .discountRate(new BigDecimal(10))
                .shipmentAdress(Optional.empty())
                .invoiceType(InvoiceType.PURCHASE_INVOICE)
                .productOrServiceAmountPairs(Arrays.asList(productAmountPair1, productAmountPair2))
                .user(user1)
                .build());

        var invoice2 = invoiceRepository.save(Invoice.builder()
                .address(add1)
                .currency(Currency.getInstance("TRY"))
                .customer(customer2)
                .invoiceDate(LocalDateTime.now().minusDays(3))
                .deliveryDate(LocalDateTime.now().plusDays(5))
                .discountRate(new BigDecimal(10))
                .shipmentAdress(Optional.empty())
                .invoiceType(InvoiceType.PURCHASE_INVOICE)
                .productOrServiceAmountPairs(Arrays.asList(productAmountPair4, productAmountPair4))
                .user(user2)
                .build());

        var invoice3 = invoiceRepository.save(Invoice.builder()
                .address(add2)
                .currency(Currency.getInstance("TRY"))
                .customer(customer3)
                .invoiceDate(LocalDateTime.now().minusDays(3))
                .deliveryDate(LocalDateTime.now().plusDays(5))
                .discountRate(new BigDecimal(10))
                .shipmentAdress(Optional.empty())
                .invoiceType(InvoiceType.SALES_INVOICE)
                .productOrServiceAmountPairs(Arrays.asList(productAmountPair5, productAmountPair6))
                .user(user1)
                .build());

        var transaction1 = stockTransactionRepository.save(StockTransaction.builder()
                .date(LocalDate.now().minusDays(2))
                .description("Depodan çıkış")
                .type(StockTransactionType.SARFCIKIS)
                .products(Arrays.asList(productAmountPair2, productAmountPair3))
                .user(user1)
                .documentNumber("111222333")
                .build());
        var transaction2 = stockTransactionRepository.save(StockTransaction.builder()
                .date(LocalDate.now().minusDays(3))
                .description("Depodan fire")
                .type(StockTransactionType.FIRECIKIS)
                .products(Arrays.asList(productAmountPair1, productAmountPair5))
                .user(user1)
                .documentNumber("111222444")
                .build());
        System.out.println("Database was empty, insertion completed.");
    }
}