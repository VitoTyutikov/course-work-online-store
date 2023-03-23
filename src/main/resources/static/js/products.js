productTable = document.getElementById('products-table')

productUrl = "http://localhost:8080/products-rest"
fetch(productUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");
        return response.json();
    })
    .then((json) => {
        const products = json;
        for (const product of products) {
            const tdId = document.createElement('td');
            const tdName = document.createElement('td');
            const tdManufacturerId = document.createElement('td');
            const tdManufacturerName = document.createElement('td');
            const tdCategoryId = document.createElement('td');
            const tdCategoryName = document.createElement('td')
            const tdProductPrice = document.createElement('td');
            const tdProductDescription = document.createElement('td');
            const tdProductImage = document.createElement('td');
            const tdProductRating = document.createElement('td');
            const tdProductDiscount = document.createElement('td');
            const tdProductIsActive = document.createElement('td');


            tdId.textContent = product.productId;
            tdName.textContent = product.productName;
            tdManufacturerId.textContent = product.manufacturerId;
            tdManufacturerName.textContent = product.manufacturersByManufacturerId.manufacturerName;
            tdCategoryId.textContent = product.categoryId;
            tdCategoryName.textContent = product.categoriesByCategoryId.categoryName;
            tdProductPrice.textContent = product.productPrice;
            tdProductDescription.textContent = product.productDescription;
            tdProductImage.textContent = product.productImage;
            tdProductRating.textContent = product.productRating;
            tdProductDiscount.textContent = product.productDiscount;
            tdProductIsActive.textContent = product.productIsActive;


            const tr = document.createElement('tr');
            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdManufacturerId)
            tr.appendChild(tdManufacturerName);
            tr.appendChild(tdCategoryName);
            tr.appendChild(tdProductPrice);
            tr.appendChild(tdProductDescription);
            tr.appendChild(tdProductImage);
            tr.appendChild(tdProductRating);
            tr.appendChild(tdProductDiscount);
            tr.appendChild(tdProductIsActive);

            productTable.appendChild(tr);
        }
    });