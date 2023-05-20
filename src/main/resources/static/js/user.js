fetch('http://localhost:8080/user')
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");
        return response.json();
    })
    .then(json => {
        const client = json;
        // window.location.replace("/login");

        document.getElementById('client-fname').textContent = client.clientFname;
        document.getElementById('client-lname').textContent = client.clientLname;
        document.getElementById('client-login').textContent = client.clientLogin;
        document.getElementById('client-email').textContent = client.clientEmail;
        document.getElementById('client-index').textContent = client.clientIndex;
        document.getElementById('client-city').textContent = client.clientCity;
        document.getElementById('client-address').textContent = client.clientAddress;
        document.getElementById('client-role').textContent = client.userRole;
    })
    .catch(error => {
        window.location.href = 'login';
    });
