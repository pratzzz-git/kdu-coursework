function getAnalytics(employees) {
  const skillMap = {};

  employees.forEach(emp => {
    emp.skills.forEach(skill => {
      skillMap[skill] = (skillMap[skill] || 0) + 1;
    });
  });

  return skillMap;
}

module.exports = getAnalytics;
