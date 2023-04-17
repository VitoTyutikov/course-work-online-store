fetch("http://localhost:8080/orders")
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");
        return response.json();
    })
    .then(json => {
        const table = document.getElementById('orders-table');
        const orders = json;
        for (const order of orders) {
            const row = document.createElement("tr");

            const id = document.createElement("td");
            id.textContent = order.orderId;
            row.appendChild(id);

            const clientId = document.createElement("td");
            clientId.textContent = order.clientId;
            row.appendChild(clientId);

            const storeId = document.createElement("td");
            storeId.textContent = order.storeId;
            row.appendChild(storeId);

            const orderDate = document.createElement("td");
            orderDate.textContent = order.orderDate;
            row.appendChild(orderDate);

            const orderStatus = document.createElement("td");
            orderStatus.textContent = order.orderStatus;
            row.appendChild(orderStatus);

            table.appendChild(row);
        }

    });