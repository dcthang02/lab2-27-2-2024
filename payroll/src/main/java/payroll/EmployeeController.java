package payroll;

// import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

  private final EmployeeRepository repository;

  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }


//   Aggregate root
//   tag::get-aggregate-root[]
  @GetMapping("/employees")
  public @ResponseBody Iterable<Employee> all() {
    return repository.findAll();
  }
//   end::get-aggregate-root[]

  @SuppressWarnings("null")
@PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @SuppressWarnings("null")
@GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @SuppressWarnings("null")
@PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @SuppressWarnings("null")
@DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}