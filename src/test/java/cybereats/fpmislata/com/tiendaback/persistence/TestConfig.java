package cybereats.fpmislata.com.tiendaback.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.BookingJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.BookingJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.UserJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.PCJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryPCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.CategoryPCJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.CategoryProductJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.LogJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.LogJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.OrderItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.OrderItemJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.ProductJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.ReportJpaDaoImpl;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl.UserOrderJpaDaoImpl;
import jakarta.persistence.EntityManager;

@Configuration
@EnableJpaRepositories(basePackages = "cybereats.fpmislata.com.tiendaback.persistence.dao.jpa")
@EntityScan(basePackages = "cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity")
public class TestConfig {

    @Bean
    public BookingJpaDao bookingJpaDao(EntityManager entityManager) {
        return new BookingJpaDaoImpl();
    }

    @Bean
    public UserJpaDao userJpaDao(EntityManager entityManager) {
        return new UserJpaDaoImpl();
    }

    @Bean
    public PCJpaDao pcJpaDao(EntityManager entityManager) {
        return new PCJpaDaoImpl();
    }

    @Bean
    public CategoryPCJpaDao categoryPCJpaDao(EntityManager entityManager) {
        return new CategoryPCJpaDaoImpl();
    }

    @Bean
    public CategoryProductJpaDao categoryProductJpaDao(EntityManager entityManager) {
        return new CategoryProductJpaDaoImpl();
    }

    @Bean
    public LogJpaDao logJpaDao(EntityManager entityManager) {
        return new LogJpaDaoImpl();
    }

    @Bean
    public OrderItemJpaDao orderItemJpaDao(EntityManager entityManager) {
        return new OrderItemJpaDaoImpl();
    }

    @Bean
    public ProductJpaDao productJpaDao(EntityManager entityManager) {
        return new ProductJpaDaoImpl();
    }

    @Bean
    public ReportJpaDao reportJpaDao(EntityManager entityManager) {
        return new ReportJpaDaoImpl();
    }

    @Bean
    public UserOrderJpaDao userOrderJpaDao(EntityManager entityManager) {
        return new UserOrderJpaDaoImpl();
    }

}