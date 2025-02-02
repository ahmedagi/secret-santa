CREATE TABLE IF NOT EXISTS employee (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS list (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS pair (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    giver_id INT NOT NULL,
    receiver_id INT,
    list_id INT NOT NULL,
    FOREIGN KEY (list_id) REFERENCES list(id) ON DELETE CASCADE,
    FOREIGN KEY (giver_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES employee(id) ON DELETE CASCADE,
    UNIQUE (list_id, giver_id)
);