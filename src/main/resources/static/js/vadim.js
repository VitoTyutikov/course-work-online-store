materialsTable = document.getElementById('materials-table');

materialsUrl = "http://localhost:8080/materials"
fetch(materialsUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const materials = json;
        for (const material of materials) {
            const tdId = document.createElement('td');
            const tdName = document.createElement('td')
            const tdMeasureUnit = document.createElement('td')
            const tdUnitPrice = document.createElement('td')
            const tdDeleteButton = document.createElement('td');

            const deleteButton = document.createElement('button');
            deleteButton.textContent = '🗑';
            deleteButton.onclick = () => {
                fetch(materialsUrl + '/' + material.id, {
                    method: 'DELETE',
                })
                    .then(() => {
                        window.location.reload();
                    });
            };

            tdId.textContent = material.id;
            tdName.textContent = material.name;
            tdMeasureUnit.textContent = material.measureUnit;
            tdUnitPrice.textContent = material.unitPrice;
            tdDeleteButton.appendChild(deleteButton);

            const tr = document.createElement('tr');
            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdMeasureUnit);
            tr.appendChild(tdUnitPrice);
            tr.appendChild(tdDeleteButton);

            materialsTable.appendChild(tr);
        }
    });