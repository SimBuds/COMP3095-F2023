package ca.gbc.inventoryservice.service;

import ca.gbc.inventoryservice.dto.InventoryRequest;
import ca.gbc.inventoryservice.dto.InventoryResponse;
import ca.gbc.inventoryservice.model.Inventory;
import ca.gbc.inventoryservice.repository.InventoryRepository;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<InventoryRequest> requests) {

        // Testing purposes only
        log.info("Wait started");
        Thread.sleep(5000);
        log.info("Wait ended");

        List<Inventory> availableInventory = inventoryRepository.findAllByInventoryRequests(requests);

        return requests.stream().map(request -> {

            boolean isInStock = availableInventory.stream()
                    .anyMatch(inventory -> inventory.getSkuCode().equals(request.getSkuCode())
                            && inventory.getQuantity() >= request.getQuantity());

            if (isInStock) {
                return InventoryResponse.builder()
                        .skuCode(request.getSkuCode())
                        .sufficientStock(true)
                        .build();
            } else {
                return InventoryResponse.builder()
                        .skuCode(request.getSkuCode())
                        .sufficientStock(false)
                        .build();
            }
        }).toList();
    }
}
