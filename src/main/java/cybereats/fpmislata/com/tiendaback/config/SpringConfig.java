package cybereats.fpmislata.com.tiendaback.config;

import cybereats.fpmislata.com.tiendaback.domain.repository.*;
import cybereats.fpmislata.com.tiendaback.domain.service.*;
import cybereats.fpmislata.com.tiendaback.domain.service.impl.*;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.*;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.*;
import cybereats.fpmislata.com.tiendaback.persistence.repository.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "cybereats.fpmislata.com.tiendaback.persistence.dao.jpa")
@EntityScan(basePackages = "cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity")
public class SpringConfig {
    // JPA DAOs

    @Bean
    public BookingJpaDao bookingJpaDao() {
        return new BookingJpaDaoImpl();
    }

    @Bean
    public CategoryPCJpaDao categoryPCJpaDao() {
        return new CategoryPCJpaDaoImpl();
    }

    @Bean
    public LogJpaDao logJpaDao() {
        return new LogJpaDaoImpl();
    }

    @Bean
    public PCJpaDao pcJpaDao() {
        return new PCJpaDaoImpl();
    }

    @Bean
    public ProductJpaDao productJpaDao() {
        return new ProductJpaDaoImpl();
    }

    // Services and Repositories

    @Bean
    public BookingRepository bookingRepository(BookingJpaDao bookingJpaDao) {
        return new BookingRepositoryImpl(bookingJpaDao);
    }

    @Bean
    public BookingService bookingService(BookingRepository bookingRepository) {
        return new BookingServiceImpl(bookingRepository);
    }

    @Bean
    public ProductRepository productRepository(ProductJpaDao productJpaDao) {
        return new ProductRepositoryImpl(productJpaDao);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public CategoryPCRepository categoryPCRepository(CategoryPCJpaDao categoryPCJpaDao) {
        return new CategoryPCRepositoryImpl(categoryPCJpaDao);
    }

    @Bean
    public CategoryPCService categoryPCService(CategoryPCRepository categoryPCRepository) {
        return new CategoryPCServiceImpl(categoryPCRepository);
    }

    @Bean
    public LogRepository logRepository(LogJpaDao logJpaDao) {
        return new LogRepositoryImpl(logJpaDao);
    }

    @Bean
    public LogService logService(LogRepository logRepository) {
        return new LogServiceImpl(logRepository);
    }

    @Bean
    public PCRepository pcRepository(PCJpaDao pcJpaDao) {
        return new PCRepositoryImpl(pcJpaDao);
    }

    @Bean
    public PCService pcService(PCRepository pcRepository) {
        return new PCServiceImpl(pcRepository);
    }
}
