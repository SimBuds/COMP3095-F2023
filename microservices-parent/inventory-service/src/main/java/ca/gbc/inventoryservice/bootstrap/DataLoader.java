package ca.gbc.inventoryservice.bootstrap;

import ca.gbc.inventoryservice.model.Inventory;
import ca.gbc.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        if(inventoryRepository.findBySkuCode("sku_123456").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_123456")
                    .quantity(2)
                    .build();

            inventoryRepository.save(widget);
        }

        if(inventoryRepository.findBySkuCode("sku_789123").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_789123")
                    .quantity(4)
                    .build();

            inventoryRepository.save(widget);
        }

        if(inventoryRepository.findBySkuCode("sku_55555").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_55555")
                    .quantity(0)
                    .build();

            inventoryRepository.save(widget);
        }
    }
}
