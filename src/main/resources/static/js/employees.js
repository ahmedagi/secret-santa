import { addEmployee, deleteEmployee } from "./api.js";

// Event handlers
async function handleAddEmployee(e) {
    e.preventDefault();
    const trimmed = nameInput.value.trim();
    if (trimmed === '') {
        showErrorMessage("Name cannot be blank.");
        return;
    } else if (trimmed.length > 64) {
        showErrorMessage("Maximum length is 64 characters.")
        return;
    }

    const { success, data } = await addEmployee(trimmed);
    if (success) {
        addEmployeeCard(data.name, data.id);
        scrollToLastEmployee();
        nameInput.value = '';
        nameInput.focus();
    } else {
        showErrorMessage(data.message);
    }
}

async function handleDeleteEmployee(e) {
    const button = e.currentTarget;
    const id = button.getAttribute("data-employee-id");
    const { success, data } = await deleteEmployee(id);
    if (success) {
        const card = document.querySelector(`.employee-card[data-employee-id="${id}"]`);
        employeesContainer.removeChild(card);
        button.removeEventListener("click", handleDeleteEmployee);
    } else {
        alert(data.message);
    }
}

function scrollToLastEmployee() {
    const rect = footer.getBoundingClientRect();
    window.scrollTo({
        top: window.scrollY + rect.top - window.innerHeight,
        behavior: 'smooth'
    });
}

/**
 * Creates and adds a new employee card to the UI.
 *
 * @param name name of the employee
 * @param id id of the employee
 */
function addEmployeeCard(name, id) {
    const employeeCard = document.createElement('div');
    employeeCard.classList.add("employee-card");
    employeeCard.setAttribute('data-employee-id', id);

    const nameSpan = document.createElement('span');
    nameSpan.classList.add('employee-name');
    nameSpan.textContent = name;

    const deleteButton = document.createElement('button');
    deleteButton.classList.add('delete-button', 'button');
    deleteButton.setAttribute('type', 'button');
    deleteButton.setAttribute('data-employee-id', id);
    deleteButton.addEventListener("click", handleDeleteEmployee)

    const deleteIcon = document.createElement('i');
    deleteIcon.classList.add('fa-solid', 'fa-trash', 'fa-icon');

    deleteButton.appendChild(deleteIcon);

    employeeCard.appendChild(nameSpan);
    employeeCard.appendChild(deleteButton);

    employeesContainer.appendChild(employeeCard);
}

function showErrorMessage(message) {
    errorMessage.innerText = message;
}

function hideErrorMessage() {
    errorMessage.innerText = "";
}

// References to html elements
const employeesContainer = document.querySelector(".employees-container");
const nameInput = document.querySelector("#name-input");
const addButton = document.querySelector(".submit-button");
const errorMessage = document.querySelector(".error-message");
const footer = document.querySelector('.footer');
const deleteButtons = document.querySelectorAll(".delete-button");

// Event listeners
addButton.addEventListener("click", handleAddEmployee);

nameInput.addEventListener("input", () => {
    hideErrorMessage();
});

deleteButtons.forEach(button =>
    button.addEventListener("click", handleDeleteEmployee)
);