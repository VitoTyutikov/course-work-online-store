fetch('http://localhost:8080/user')
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");
        return response.json();
    })
    .then(json => {
        const tableBody = document.getElementById('clients-table');
        const client = json;
        const row = document.createElement('tr');
        const idCell = document.createElement('td');
        idCell.textContent = client.clientId;
        row.appendChild(idCell);

        const fnameCell = document.createElement('td');
        fnameCell.textContent = client.clientFname;
        row.appendChild(fnameCell);

        const lnameCell = document.createElement('td');
        lnameCell.textContent = client.clientLname;
        row.appendChild(lnameCell);

        const loginCell = document.createElement('td');
        loginCell.textContent = client.clientLogin;
        row.appendChild(loginCell);

        const emailCell = document.createElement('td');
        emailCell.textContent = client.clientEmail;
        row.appendChild(emailCell);

        const passwordCell = document.createElement('td');
        passwordCell.textContent = client.clientPassword;
        row.appendChild(passwordCell);

        const indexCell = document.createElement('td');
        indexCell.textContent = client.clientIndex;
        row.appendChild(indexCell);

        const cityCell = document.createElement('td');
        cityCell.textContent = client.clientCity;
        row.appendChild(cityCell);

        const addressCell = document.createElement('td');
        addressCell.textContent = client.clientAddress;
        row.appendChild(addressCell);

        const roleCell = document.createElement('td');
        roleCell.textContent = client.userRole;
        row.appendChild(roleCell);
        tableBody.appendChild(row);
    })
    .then(error => console.log(error));
