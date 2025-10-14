package com.backend.service;

import com.backend.dto.CustomerDto;
import com.backend.entity.Customer;
import com.backend.exception.BaseException;
import com.backend.exception.ErrorMessage;
import com.backend.exception.MessageType;
import com.backend.repository.CustomerRepository;
import com.backend.config.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.ProxyFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerDto.setActive(true);
        Customer customer = Mapper.map(customerDto, Customer.class);
        customer.setId(null);
        Customer savedCustomer = customerRepository.save(customer);
        return Mapper.map(savedCustomer, CustomerDto.class);
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,id.toString()));
        }
        return Mapper.map(customer, CustomerDto.class);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findCustomerByActive(true);
        return Mapper.mapAll(customers, CustomerDto.class);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getActiveCustomers(boolean active) {
        List<Customer> activeCustomers = customerRepository.findCustomerByActive(active);
        return Mapper.mapAll(activeCustomers, CustomerDto.class);
    }

    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        existingCustomer.setComment(customerDto.getComment());
        existingCustomer.setCompanyName(customerDto.getCompanyName());
        existingCustomer.setActive(customerDto.isActive());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return Mapper.map(updatedCustomer, CustomerDto.class);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        customer.setActive(false);
        customerRepository.save(customer);
    }
}