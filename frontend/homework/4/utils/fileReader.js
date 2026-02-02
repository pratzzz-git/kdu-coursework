const fs = require("fs");

function readEmployeeData(filePath) {
  const data = fs.readFileSync(filePath, "utf-8");
  return JSON.parse(data);
}

module.exports = readEmployeeData;
