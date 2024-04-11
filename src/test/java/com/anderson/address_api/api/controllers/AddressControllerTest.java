package com.anderson.address_api.api.controllers;

import builders.AddressBuilders;
import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class AddressControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AddressRepository repository;

    @Test
    @DisplayName("Insert address successfully")
    void insertSuccessfully() throws Exception {
        AddressRequestDTO dto = AddressBuilders.toAddressRequestDTO();

        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/addresses/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Insert zip code invalid")
    void insertInvalidData() throws Exception{
        AddressRequestDTO addressRequestDTO = AddressBuilders.toAddressRequestInvalidZipCodeDTO();

        String dtoString = mapper.writeValueAsString(addressRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/addresses/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Insert address already registered")
    void insertAlreadyRegistered() throws Exception{
        AddressRequestDTO addressRequestDTO = AddressBuilders.toAddressRequestDTO();

        repository.save(AddressBuilders.toAddressWithoutId());

        String dtoString = mapper.writeValueAsString(addressRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/addresses/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Insert zip code does not exist")
    void insertDoesNotExist() throws Exception {
        AddressRequestDTO addressRequestDTO = AddressBuilders.toAddressRequestZipCodeDoesNotExistDTO();

        String dtoString = mapper.writeValueAsString(addressRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/addresses/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Find by id successfully")
    void findByIdSuccessfully() throws Exception {
        Address address = repository.save(AddressBuilders.toAddressWithoutId());

        UUID id = address.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/addresses/find/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Find by id not found")
    void findByIdNotFound() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/addresses/find/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Address deleted successfully")
    void deleteSuccessfully() throws Exception {
        Address address = repository.save(AddressBuilders.toAddressWithoutId());

        UUID id = address.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/addresses/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Address updated successfully")
    void updateSuccessfully() throws Exception {
        Address address = repository.save(AddressBuilders.toAddressWithoutId());

        UUID id = address.getId();

        AddressUpdateDTO addressUpdateDTO = AddressBuilders.toAddressUpdateDTO();

        String updateString = mapper.writeValueAsString(addressUpdateDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/addresses/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}