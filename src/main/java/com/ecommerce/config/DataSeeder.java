package com.ecommerce.config;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedRoles();
        seedAdminUser();
        seedProducts();
        log.info(" Data seeding complete. Swagger UI: http://localhost:8080/swagger-ui.html");
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new Role(null, Role.ERole.ROLE_USER),
                    new Role(null, Role.ERole.ROLE_ADMIN)
            ));
            log.info("Seeded roles: ROLE_USER, ROLE_ADMIN");
        }
    }

    private void seedAdminUser() {
        if (!userRepository.existsByEmail("admin@ecommerce.com")) {
            Role adminRole = roleRepository.findByName(Role.ERole.ROLE_ADMIN).orElseThrow();
            Role userRole  = roleRepository.findByName(Role.ERole.ROLE_USER).orElseThrow();

            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@ecommerce.com")
                    .password(passwordEncoder.encode("Admin@1234"))
                    .roles(Set.of(adminRole, userRole))
                    .build();
            userRepository.save(admin);
            log.info("Seeded admin user → email: admin@ecommerce.com | password: Admin@1234");
        }

        if (!userRepository.existsByEmail("john@example.com")) {
            Role userRole = roleRepository.findByName(Role.ERole.ROLE_USER).orElseThrow();
            User user = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john@example.com")
                    .password(passwordEncoder.encode("User@1234"))
                    .roles(Set.of(userRole))
                    .build();
            userRepository.save(user);
            log.info("Seeded regular user → email: john@example.com | password: User@1234");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                    Product.builder().name("Apple iPhone 15 Pro").description("Latest Apple flagship with A17 Pro chip")
                            .price(new BigDecimal("999.99")).stockQuantity(50).category("Electronics")
                            .imageUrl("https://example.com/iphone15.jpg").active(true).build(),

                    Product.builder().name("Samsung Galaxy S24").description("Android flagship with 200MP camera")
                            .price(new BigDecimal("849.99")).stockQuantity(40).category("Electronics")
                            .imageUrl("https://example.com/s24.jpg").active(true).build(),

                    Product.builder().name("Sony WH-1000XM5").description("Industry-leading noise cancelling headphones")
                            .price(new BigDecimal("349.99")).stockQuantity(100).category("Audio")
                            .imageUrl("https://example.com/sony-wh.jpg").active(true).build(),

                    Product.builder().name("Nike Air Max 270").description("Lightweight running shoes with Max Air unit")
                            .price(new BigDecimal("129.99")).stockQuantity(200).category("Footwear")
                            .imageUrl("https://example.com/nike-am270.jpg").active(true).build(),

                    Product.builder().name("MacBook Pro 14-inch").description("Apple M3 Pro chip, 18GB RAM, 512GB SSD")
                            .price(new BigDecimal("1999.00")).stockQuantity(30).category("Laptops")
                            .imageUrl("https://example.com/macbook-pro.jpg").active(true).build(),

                    Product.builder().name("Levi's 501 Original Jeans").description("Classic straight-fit denim jeans")
                            .price(new BigDecimal("59.99")).stockQuantity(300).category("Clothing")
                            .imageUrl("https://example.com/levis501.jpg").active(true).build(),

                    Product.builder().name("Instant Pot Duo 7-in-1").description("7-in-1 electric pressure cooker")
                            .price(new BigDecimal("79.99")).stockQuantity(75).category("Kitchen")
                            .imageUrl("https://example.com/instantpot.jpg").active(true).build(),

                    Product.builder().name("Kindle Paperwhite").description("Waterproof e-reader with 6.8-inch display")
                            .price(new BigDecimal("139.99")).stockQuantity(120).category("Electronics")
                            .imageUrl("https://example.com/kindle.jpg").active(true).build()
            ));
            log.info("Seeded 8 sample products across 5 categories");
        }
    }
}
