const readEmployeeData = require("./utils/fileReader");
const writeReport = require("./utils/reportGenerator");


const {
  generateSummaryReport,
  generateDepartmentReport,
  generateTopEarnersReport
} = require("./services/employeeAnalytics");

const employees = readEmployeeData("./data/employees.json");
const arg = process.argv[2];

if (!arg) {
  // summary
  const summary = generateSummaryReport(employees);
  console.log("\n===== SUMMARY REPORT =====\n");
  console.log(summary);
  writeReport("./reports/summary.txt", summary);

  // department
  const department = generateDepartmentReport(employees, "Engineering");
  console.log("\n===== DEPARTMENT REPORT =====\n");
  console.log(department);
  writeReport("./reports/department.txt", department);

  // top earners
  const top = generateTopEarnersReport(employees, 3);
  console.log("\n===== TOP EARNERS REPORT =====\n");
  console.log(top);
  writeReport("./reports/topEarners.txt", top);
}
 else if (arg === "summary") {
  writeReport("./reports/summary.txt", generateSummaryReport(employees));
} else if (arg === "department") {
  writeReport("./reports/department.txt", generateDepartmentReport(employees, "Engineering"));//can write any department name
} else if (arg === "top") {
  writeReport("./reports/topEarners.txt", generateTopEarnersReport(employees, 3));
}
