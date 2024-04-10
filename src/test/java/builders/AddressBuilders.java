package builders;

import com.anderson.address_api.core.dtos.AddressExternalDTO;
import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.model.Address;

import java.time.Instant;
import java.util.UUID;

public class AddressBuilders {

    public static AddressRequestDTO toAddressRequestDTO() {
        return new AddressRequestDTO("24416060", "288", "Segunda rua a direita depois da praça");
    }

    public static AddressExternalDTO toAddressExternalDTO() {
        return new AddressExternalDTO("24416-060", "São Gonçalo", "RJ", "Barro Vermelho");
    }

    public static Address toAddress() {
        return new Address(UUID.randomUUID(), "24416060", "São Gonçalo", "RJ", "Barro Vermelho", "Segunda rua a direita depois da praça", "288", Instant.now(), Instant.now());
    }

    public static AddressUpdateDTO toAddressUpdateDTO() {
        return new AddressUpdateDTO("298", "Rua do salgado");
    }
}
