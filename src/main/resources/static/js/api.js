export async function addEmployee(name, callback) {
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