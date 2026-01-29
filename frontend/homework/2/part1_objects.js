const employee1 = {
  id: 1,
  name: "Aarav Sharma",
  age: 30,
  salary: 85000,
  department: "Engineering",
  skills: ["JavaScript", "React"],
  experience: 5
};

const employee2 = {
  id: 2,
  name: "Rohit Verma",
  age: 28,
  salary: 60000,
  department: "HR",
  skills: ["Communication"],
  experience: 3
};

const employee3 = {
  id: 3,
  name: "Priya Iyer",
  age: 35,
  salary: 95000,
  department: "Engineering",
  skills: ["Python"],
  experience: 8
};

const employee4 = {
  id: 4,
  name: "Neha Kapoor",
  age: 26,
  salary: 50000,
  department: "Sales",
  skills: ["Negotiation"],
  experience: 2
};

const employee5 = {
  id: 5,
  name: "Ankit Gupta",
  age: 32,
  salary: 70000,
  department: "HR",
  skills: ["Recruitment"],
  experience: 6
};

module.exports = {
  employee1,
  employee2,
  employee3,
  employee4,
  employee5
};

function getEmployeeInfo(employee) {
  return `${employee.name} works in ${employee.department} and earns ${employee.salary}`;
}

function addSkill(employee, skill) {
  employee.skills.push(skill);
}

employee1.getFullInfo = function () {
  return `${this.name}, ${this.age}, ${this.department}, ${this.salary}, ${this.skills.join(", ")}`;
};

function compareEmployees(emp1, emp2) {
  if (emp1.skills.length > emp2.skills.length) {
    return emp1.name;
  }
  return emp2.name;
}

module.exports.getEmployeeInfo = getEmployeeInfo;
module.exports.addSkill = addSkill;
module.exports.compareEmployees = compareEmployees;

