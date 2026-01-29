const {
  employee1,
  employee2,
  getEmployeeInfo,
  addSkill,
  compareEmployees
} = require("./part1_objects");

console.log("========== PART 1: OBJECTS ==========");

console.log(getEmployeeInfo(employee1));

addSkill(employee1, "Node.js");
console.log("Updated Skills:", employee1.skills);

console.log("Full Info:", employee1.getFullInfo());

console.log("More Skills:", compareEmployees(employee1, employee2));


const {
  employees,
  filterByExperience,
  getSummaries,
  getAverageSalary,
  departmentCount,
  highestPaid,
  sortByExperience
} = require("./part2_arrays");

console.log("\n========== PART 2: ARRAYS ==========");

console.log("All Employees:", employees);

console.log("Experience >= 5:", filterByExperience(employees, 5));

console.log("Summaries:", getSummaries(employees));

console.log("Average Salary:", getAverageSalary(employees));
console.log("Department Count:", departmentCount(employees));

console.log("Highest Paid Employee:", highestPaid(employees));
console.log("Sorted by Experience:", sortByExperience(employees));


const {
  extractDetails,
  topAndBottomPaid,
  mergeSkills,
  employeeStats
} = require("./part3_destructuring");

console.log("\n========== PART 3: DESTRUCTURING & SPREAD/REST ==========");

console.log("Extracted Details:", extractDetails(employee1));

console.log("Top & Bottom Paid:", topAndBottomPaid(employees));

console.log("Merged Skills:", mergeSkills(employee1, employee2));

console.log("Employee Stats:", employeeStats(...employees));


const getAnalytics = require("./part4_analytics");

console.log("\n========== PART 4: ANALYTICS ==========");

console.log("Skill Analytics:", getAnalytics(employees));
