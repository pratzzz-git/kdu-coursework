const fs = require("fs");
const path = require("path");

function writeReport(filePath, content) {
  const dir = path.dirname(filePath);

  if (!fs.existsSync(dir)) {
    fs.mkdirSync(dir);
  }

  fs.writeFileSync(filePath, content);
}

module.exports = writeReport;
