function extractDetails(employee) {
  const { name, department, salary } = employee;
  return { name, department, salary };
}

function topAndBottomPaid(employees) {
  const sorted = [...employees].sort((a, b) => a.salary - b.salary);
  return [sorted[0], sorted[sorted.length - 1]];
}

function mergeSkills(emp1, emp2) {
  const skills = [...emp1.skills, ...emp2.skills];
  return [...new Set(skills)];
}

function employeeStats(...employees) {
  const total = employees.length;
  const avgAge =
    employees.reduce((sum, emp) => sum + emp.age, 0) / total;

  return { total, avgAge };
}

module.exports = {
  extractDetails,
  topAndBottomPaid,
  mergeSkills,
  employeeStats
};
