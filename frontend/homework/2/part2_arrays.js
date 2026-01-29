const {
  employee1,
  employee2,
  employee3,
  employee4,
  employee5
} = require("./part1_objects");

const employees = [
  employee1,
  employee2,
  employee3,
  employee4,
  employee5
];

console.log(employees);

function filterByExperience(employees, minExperience) {
  return employees.filter(emp => emp.experience >= minExperience);
}

function getSummaries(employees) {
  return employees.map(emp => `${emp.name} (${emp.department}) - $${emp.salary}`);
}

function getAverageSalary(employees) {
  const total = employees.reduce((sum, emp) => sum + emp.salary, 0);
  return total / employees.length;
}

function departmentCount(employees) {
  return employees.reduce((acc, emp) => {
    acc[emp.department] = (acc[emp.department] || 0) + 1;
    return acc;
  }, {});
}

function highestPaid(employees) {
  return employees.reduce((max, emp) =>
    emp.salary > max.salary ? emp : max
  );
}

function sortByExperience(employees) {
  return [...employees].sort((a, b) => b.experience - a.experience);
}

module.exports = {
  employees,
  filterByExperience,
  getSummaries,
  getAverageSalary,
  departmentCount,
  highestPaid,
  sortByExperience
};
