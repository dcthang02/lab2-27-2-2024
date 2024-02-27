package payroll;

import org.springframework.data.repository.CrudRepository;

interface EmployeeRepository extends CrudRepository<Employee, Long> {

}