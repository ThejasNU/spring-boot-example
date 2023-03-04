package com.tnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SpringBootApplication
@RestController
@RequestMapping("api/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name,String email,Integer age){}

    @PostMapping
    public Customer addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer=new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        return customerRepository.save(customer);
    }
//    @PostMapping
//    public void addCustomer(@RequestBody NewCustomerRequest request){
//        Customer customer=new Customer();
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//
//        customerRepository.save(customer);
//    }

//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee) {
//        return employeeRepository.save(employee);
//    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request){
        Customer customer=customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer with id " + id + "doesn't exist"));
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

//    @GetMapping("/")
//    public GreetResponse greet(){
//        return new GreetResponse("hello");
//    }
//
//    record GreetResponse(String greet){}
}
