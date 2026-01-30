//TASK-1
function Task(title, priority) {
  this.id = Date.now();
  this.title = title;
  this.priority = priority;
  this.completed = false;
}
////
Task.prototype.markComplete = function () {
  this.completed = true;
  return this; 
};

Task.prototype.updatePriority = function (newPriority) {
  const validPriorities = ["low", "medium", "high"];

  if (validPriorities.includes(newPriority)) {
    this.priority = newPriority;
  }

  return this; 
};
////
function PriorityTask(title, priority, dueDate) {
  Task.call(this, title, priority);
  this.dueDate = dueDate;
}

PriorityTask.prototype = Object.create(Task.prototype);
PriorityTask.prototype.constructor = PriorityTask;
////
Task.prototype.getInfo = function () {
  return this.title + " - " + this.priority;
};

Task.prototype.getAllTasksInfo = function (tasks) {
  return tasks.map(function (task) {
    return task.getInfo();
  });
};

//TASK-2
function createTaskAsync(title, priority) {
  console.log("Creating tasks...");

  return new Promise(function (resolve) {
    setTimeout(function () {
      console.log("Task created!");
      const task = new Task(title, priority);
      resolve(task);
    }, 1000);
  });
}
createTaskAsync("Learn JS", "high").then(function (task) {
  console.log(task);
});

/////
function demonstrateEventLoop() {

    setTimeout(() => {
        console.log(1);
    }, 2000);



    Promise.resolve().then(() => {
        setTimeout(() => {
            console.log(2);
        }, 8000);
    });
    setTimeout(() => {
        console.log(3);
    }, 6000);
    setTimeout(() => {
        console.log(4);
    }, 4000);

}
demonstrateEventLoop();

//////
async function createAndSaveTask(title, priority) {
  try {
    const task = await createTaskAsync(title, priority);
    await createTaskAsync("Temporary Task", "low");
    console.log("Task created and saved successfully!");
    return task;
  } catch (error) {
    console.log("Something went wrong");
  }
}
createAndSaveTask("Study Event Loop", "high").then(function (task) {
  console.log("Returned task:", task);
});



/////
function createMultipleTasksAsync(taskDataArray) {
  console.log("Creating " + taskDataArray.length + " tasks...");

  const taskPromises = taskDataArray.map(function (task) {
    return createTaskAsync(task.title, task.priority);
  });

  return Promise.all(taskPromises).then(function (tasks) {
    console.log("All tasks created!");
    return tasks;
  });
}
const tasksData = [
  { title: "Task A", priority: "low" },
  { title: "Task B", priority: "medium" },
  { title: "Task C", priority: "high" }
];

createMultipleTasksAsync(tasksData).then(function (tasks) {
  console.log("Returned tasks array:", tasks);
});
//