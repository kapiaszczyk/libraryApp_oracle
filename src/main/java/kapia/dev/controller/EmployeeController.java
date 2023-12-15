package kapia.dev.controller;

import kapia.dev.dto.EmployeeWithLowestSalaryPerDepartment;
import kapia.dev.dto.MatchingSalaryAndCommission;
import kapia.dev.model.Employee;
import kapia.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lowest-salary-in-department")
    public ResponseEntity<Iterable<EmployeeWithLowestSalaryPerDepartment>> lowerSalary() {
        return new ResponseEntity<>(employeeService.findAllWithLowestSalaryInDepartment(), HttpStatus.OK);
    }

    @GetMapping("/same-salary-and-commission-as")
    public ResponseEntity<Iterable<MatchingSalaryAndCommission>> sameSalaryAndCommission(@RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(employeeService.findAllWithSameSalaryAndCommission(lastName), HttpStatus.OK);
    }

    @PostMapping(value = "/set-as-manager", consumes = "application/json")
    public ResponseEntity<Iterable<Employee>> setAsManager(@RequestBody int[] employeeIdList, @RequestParam(value = "manager_id") Integer managerId) {
        return new ResponseEntity<>(employeeService.setAsManager(employeeIdList, managerId), HttpStatus.CREATED);
    }

    @PatchMapping("/update-salary")
    public ResponseEntity<Employee> updateSalary(@RequestParam(value = "employee_id") Integer employeeId, @RequestParam(value = "salary") Integer salary) {
        return new ResponseEntity<>(employeeService.updateSalary(employeeId, salary), HttpStatus.OK);
    }

}
