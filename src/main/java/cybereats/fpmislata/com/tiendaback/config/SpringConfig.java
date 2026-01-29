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

    @Bean
    public BookingJpaDao bookingJpaDao() {
        return new BookingJpaDaoImpl();
    }

    @Bean
    public CategoryPCJpaDao categoryPCJpaDao() {
        return new CategoryPCJpaDaoImpl();
    }

    @Bean
    public CategoryProductJpaDao categoryProductJpaDao() {
        return new CategoryProductJpaDaoImpl();
    }

    @Bean
    public OrderItemJpaDao orderItemJpaDao() {
        return new OrderItemJpaDaoImpl();
    }

    @Bean
    public ReportJpaDao reportJpaDao() {
        return new ReportJpaDaoImpl();
    }

    @Bean
    public UserJpaDao userJpaDao() {
        return new UserJpaDaoImpl();
    }

    @Bean
    public UserOrderJpaDao userOrderJpaDao() {
        return new UserOrderJpaDaoImpl();
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

    @Bean
    public CartJpaDao cartJpaDao() {
        return new CartJpaDaoImpl();
    }

    @Bean
    public CartItemJpaDao cartItemJpaDao() {
        return new CartItemJpaDaoImpl();
    }

    @Bean
    public BookingRepository bookingRepository(BookingJpaDao bookingJpaDao) {
        return new BookingRepositoryImpl(bookingJpaDao);
    }

    @Bean
    public BookingService bookingService(BookingRepository bookingRepository, PCRepository pcRepository,
            UserRepository userRepository) {
        return new BookingServiceImpl(bookingRepository, pcRepository, userRepository);
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
    public CategoryProductRepository categoryProductRepository(CategoryProductJpaDao categoryProductJpaDao) {
        return new CategoryProductRepositoryImpl(categoryProductJpaDao);
    }

    @Bean
    public CategoryProductService categoryProductService(CategoryProductRepository categoryProductRepository) {
        return new CategoryProductServiceImpl(categoryProductRepository);
    }

    @Bean
    public OrderItemRepository orderItemRepository(OrderItemJpaDao orderItemJpaDao) {
        return new OrderItemRepositoryImpl(orderItemJpaDao);
    }

    @Bean
    public OrderItemService orderItemService(OrderItemRepository orderItemRepository) {
        return new OrderItemServiceImpl(orderItemRepository);
    }

    @Bean
    public ReportRepository reportRepository(ReportJpaDao reportJpaDao) {
        return new ReportRepositoryImpl(reportJpaDao);
    }

    @Bean
    public ReportService reportService(ReportRepository reportRepository, PCRepository pcRepository) {
        return new ReportServiceImpl(reportRepository, pcRepository);
    }

    @Bean
    public UserRepository userRepository(UserJpaDao userJpaDao) {
        return new UserRepositoryImpl(userJpaDao);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public UserOrderRepository userOrderRepository(UserOrderJpaDao userOrderJpaDao) {
        return new UserOrderRepositoryImpl(userOrderJpaDao);
    }

    @Bean
    public UserOrderService userOrderService(UserOrderRepository userOrderRepository) {
        return new UserOrderServiceImpl(userOrderRepository);
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

    @Bean
    public AuthRepository authRepository(UserJpaDao userJpaDao) {
        return new AuthRepositoryImpl(userJpaDao);
    }

    @Bean
    public AuthService authService(AuthRepository authRepository) {
        return new AuthServiceImpl(authRepository);
    }

    @Bean
    public StatsService statsService(UserOrderRepository userOrderRepository, BookingRepository bookingRepository,
            PCRepository pcRepository, UserRepository userRepository, ReportRepository reportRepository) {
        return new StatsServiceImpl(userOrderRepository, bookingRepository, pcRepository, userRepository,
                reportRepository);
    }

    @Bean
    public CartRepository cartRepository(CartJpaDao cartJpaDao, CartItemJpaDao cartItemJpaDao,
            ProductJpaDao productJpaDao, UserJpaDao userJpaDao) {
        return new CartRepositoryImpl(cartJpaDao, cartItemJpaDao, productJpaDao, userJpaDao);
    }

    @Bean
    public CartService cartService(CartRepository cartRepository, UserOrderRepository userOrderRepository,
            UserRepository userRepository, BookingRepository bookingRepository) {
        return new CartServiceImpl(cartRepository, userOrderRepository, userRepository, bookingRepository);
    }
}
