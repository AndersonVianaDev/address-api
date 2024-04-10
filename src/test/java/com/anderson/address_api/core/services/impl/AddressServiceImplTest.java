package com.anderson.address_api.core.services.impl;

import builders.AddressBuilders;
import com.anderson.address_api.core.dtos.AddressExternalDTO;
import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import com.anderson.address_api.core.services.ConsultZipCode;
import com.anderson.address_api.shared.exceptions.AlreadyRegisteredException;
import com.anderson.address_api.shared.exceptions.InvalidDataException;
import com.anderson.address_api.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.WebSocketHandshakeException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static com.anderson.address_api.shared.exceptions.Constants.*;


@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl service;

    @Mock
    private AddressRepository repository;

    @Mock
    private ConsultZipCode consultZipCode;

    @Captor
    private ArgumentCaptor<Address> addressCaptor;

    @Test
    @DisplayName("insert address successfully")
    void insertSuccessfully() throws Exception {
        // arrange
        AddressRequestDTO dto = AddressBuilders.toAddressRequestDTO();
        AddressExternalDTO externalDTO = AddressBuilders.toAddressExternalDTO();

        when(repository.findByNumberAndZipCode(dto.number(), dto.zipCode())).thenReturn(Optional.empty());
        when(consultZipCode.getAddress(dto.zipCode())).thenReturn(externalDTO);

        // action
        service.insert(dto);

        // assertions
        verify(consultZipCode, times(1)).getAddress(dto.zipCode());

        // getting the address at the time it is being passed as an argument to the save method
        verify(repository, times(1)).save(addressCaptor.capture());
        Address addressResult = addressCaptor.getValue();

        assertEquals(dto.zipCode(), addressResult.getZipCode());
        assertEquals(dto.number(), addressResult.getNumber());
    }

    @Test
    @DisplayName("invalid zip code format")
    void insertInvalidData() {
        // arrange
        AddressRequestDTO dto = new AddressRequestDTO("32342-23423", null, null);

        // action
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> service.insert(dto));

        // assertions
        assertEquals(INVALID_ZIP_CODE_FORMAT, exception.getMessage());
    }

    @Test
    @DisplayName("address already registered")
    void insertAlreadyRegistered() {
        // arrange
        AddressRequestDTO dto = AddressBuilders.toAddressRequestDTO();
        Address address = AddressBuilders.toAddress();

        when(repository.findByNumberAndZipCode(dto.number(), dto.zipCode())).thenReturn(Optional.of(address));

        // action
        AlreadyRegisteredException exception = assertThrows(AlreadyRegisteredException.class, () -> service.insert(dto));

        // assertions
        assertEquals(ADDRESS_ALREADY_REGISTERED, exception.getMessage());
    }

    @Test
    @DisplayName("zip code does not exist")
    void insertNotFound() throws Exception {
        // arrange
        AddressRequestDTO dto = AddressBuilders.toAddressRequestDTO();

        when(repository.findByNumberAndZipCode(dto.number(), dto.zipCode())).thenReturn(Optional.empty());
        when(consultZipCode.getAddress(dto.zipCode())).thenThrow(new NotFoundException(ZIP_CODE_DOES_NOT_EXIST));

        // action
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.insert(dto));

        // assertions
        assertEquals(ZIP_CODE_DOES_NOT_EXIST, exception.getMessage());
    }

    @Test
    @DisplayName("unexpected error")
    void insertIllegalState() {
        // arrange
        AddressRequestDTO dto = AddressBuilders.toAddressRequestDTO();

        when(repository.findByNumberAndZipCode(dto.number(), dto.zipCode())).thenReturn(Optional.empty());

        // action
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> service.insert(dto));

        // assertions
        assertEquals(UNEXPECTED_ERROR, exception.getMessage());
    }

    @Test
    @DisplayName("find address by id successfully")
    void findByIdSuccessfully() {
        // arrange
        Address address = AddressBuilders.toAddress();
        UUID idAddress = address.getId();

        when(repository.findById(idAddress)).thenReturn(Optional.of(address));

        // action
        Address addressResult = service.findById(idAddress);

        // assertions
        verify(repository, times(1)).findById(idAddress);

        assertEquals(address, addressResult);
    }

    @Test
    @DisplayName("address not found")
    void findByIdNotFound() {
        // arrange
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        // action
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.findById(id));

        // assertions
        assertEquals(ADDRESS_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("delete address successfully")
    void deleteSuccessfully() {
        // arrange
        Address address = AddressBuilders.toAddress();
        UUID idAddress = address.getId();

        when(repository.findById(idAddress)).thenReturn(Optional.of(address));

        // action
        service.delete(idAddress);

        // assertions
        verify(repository, times(1)).delete(addressCaptor.capture());

        assertEquals(address, addressCaptor.getValue());
    }

    @Test
    @DisplayName("update address successfully")
    void updateSuccessfully() {
        // arrange
        Address address = AddressBuilders.toAddress();
        UUID idAddress = address.getId();
        AddressUpdateDTO addressUpdateDTO = AddressBuilders.toAddressUpdateDTO();

        when(repository.findById(idAddress)).thenReturn(Optional.of(address));

        // action
        service.update(idAddress, addressUpdateDTO);

        // assertions
        verify(repository, times(1)).update(addressCaptor.capture());
        Address addressResult = addressCaptor.getValue();

        assertEquals(addressUpdateDTO.number(), addressResult.getNumber());
        assertEquals(addressUpdateDTO.complement(), addressResult.getComplement());
    }
}