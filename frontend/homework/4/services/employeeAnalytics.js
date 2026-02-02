function generateSummaryReport(employees) {
  let totalSalary = 0;
  let departments = {};

  employees.forEach(emp => {
    totalSalary += emp.salary;

    if (!departments[emp.department]) {
      departments[emp.department] = { count: 0, salary: 0 };
    }

    departments[emp.department].count++;
    departments[emp.department].salary += emp.salary;
  });

  let result = "";
  result += `Total Employees: ${employees.length}\n`;
  result += `Total Salary: ${totalSalary}\n`;
  result += `Average Salary: ${totalSalary / employees.length}\n\n`;

  for (let dept in departments) {
    result += `${dept}\n`;
    result += `Count: ${departments[dept].count}\n`;
    result += `Total Salary: ${departments[dept].salary}\n`;
    result += `Average Salary: ${departments[dept].salary / departments[dept].count}\n\n`;
  }

  return result;
}

function generateDepartmentReport(employees, department) {
  const filtered = employees.filter(e => e.department === department);
  let total = 0;

  let result = `Department: ${department}\n\n`;

  filtered.forEach(emp => {
    result += `${emp.name} - ${emp.salary}\n`;
    total += emp.salary;
  });

  result += `\nTotal Salary: ${total}\n`;
  result += `Average Salary: ${total / filtered.length}\n`;

  return result;
}

function generateTopEarnersReport(employees, count) {
  const sorted = [...employees].sort((a, b) => b.salary - a.salary);
  const top = sorted.slice(0, count);

  let result = "";

  top.forEach((emp, index) => {
    result += `${index + 1}. ${emp.name} - ${emp.department} - ${emp.salary}\n`;
  });

  return result;
}

module.exports = {
  generateSummaryReport,
  generateDepartmentReport,
  generateTopEarnersReport
};
