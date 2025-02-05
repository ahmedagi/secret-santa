async function addEmployee(name, callback) {
    const response = await fetch("/employees", {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify({ name })
    });

    if (response.ok) {
        callback(true, await response.json());
    } else {
        callback(false, await response.json());
    }
}

/**
 * Delete employee API call
 * @param id id of the employee to delete
 * @returns {Promise<{success: boolean, data: null}|{success: boolean, data: any}>} { successStatus, responseData }
 */
async function deleteEmployee(id) {
    const response = await fetch(`/employees/${id}`, {
        method: 'DELETE',
    });

    if (response.ok) {
        return { success: true, data: null };
    } else {
        return { success: false, data: await response.json() };
    }
}

export {
    addEmployee,
    deleteEmployee,
}