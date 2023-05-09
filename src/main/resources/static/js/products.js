var productsBody = $('#products-body');
window.addEventListener('load', function () {
    productTable = document.getElementById('products-table');

    fetch('http://localhost:8080/categories')
        .then(response => response.json())
        .then(categories => {
            const select = document.createElement('select');
            select.id = 'category-name';
            const defaultOption = document.createElement('option');
            defaultOption.textContent = 'Choose category';
            defaultOption.value = '';
            select.appendChild(defaultOption);
            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryName;
                option.textContent = category.categoryName;
                select.appendChild(option);
            });
            document.getElementById('category-select').appendChild(select);
        });
    fetch('http://localhost:8080/manufacturers')
        .then(response => response.json())
        .then(manufacturers => {
            const select = document.createElement('select');
            select.id = 'manufacturer-name';
            const defaultOption = document.createElement('option');
            defaultOption.textContent = 'Choose manufacturer';
            select.appendChild(defaultOption);
            defaultOption.value = '';
            manufacturers.forEach(manufacturer => {
                const option = document.createElement('option');
                option.value = manufacturer.manufacturerName;
                option.textContent = manufacturer.manufacturerName;
                select.appendChild(option);
            });
            document.getElementById('manufacturer-select').appendChild(select);
        });
    fetch('http://localhost:8080/products')
        .then((response) => {
            if (!response.ok)
                alert("Unable to connect to server | HTTP Error: ${response.status}");
            return response.json();
        })
        .then((json) => {
            productsBody.empty(); // Удаляем уже отображенные товары

            for (const product of json) {
                // var product = data[i];
                var row = $('<tr>');
                row.append($('<td>').text(product.productName));
                row.append($('<td>').text(product.manufacturerName));
                row.append($('<td>').text(product.categoriesByCategoryId.categoryName));
                row.append($('<td>').text(product.productPrice));
                row.append($('<td>').text(product.productDescription));
                // row.append($('<td>').text(product.productImage));

                // row.append($('<td>').append($()))

                const imgPath = product.productImage;
                const img = document.createElement("img");
                img.setAttribute("src", imgPath);
                row.append(img);



                row.append($('<td>').text(product.productRating));
                row.append($('<td>').text(product.productDiscount));
                row.append($('<td>').text(product.productIsActive));
                productsBody.append(row);
            }
        });
})


$(document).ready(function () {
    $('#search-form').submit(function (event) {
        event.preventDefault();
        var url;
        var productName = $('#product-name').val();
        var priceFrom = $('#price-from').val();
        var priceTo = $('#price-to').val();
        var categoryName = $('#category-name').val();
        var manufacturerName = $('#manufacturer-name').val();
        if (!productName && !priceFrom && !priceTo && !categoryName && !manufacturerName) {
            // console.log("OK");
            url = "http://localhost:8080/products";
        } else if (categoryName === '') {
            alert("Choose category");
            return;
        } else {
            url = 'http://localhost:8080/products/' + categoryName + '?productName=' + productName + '&priceFrom=' + priceFrom + '&priceTo=' + priceTo + '&manufacturersName=' + encodeURIComponent(manufacturerName);
        }
        // console.log(url);
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error retrieving products.');
                }
                return response.json();
            })
            .then(json => {

                productsBody.empty(); // Удаляем уже отображенные товары

                for (const product of json) {
                    // var product = data[i];
                    var row = $('<tr>');
                    row.append($('<td>').text(product.productName));
                    row.append($('<td>').text(product.manufacturerName));
                    row.append($('<td>').text(product.categoriesByCategoryId.categoryName));
                    row.append($('<td>').text(product.productPrice));
                    row.append($('<td>').text(product.productDescription));


                    const imgPath = product.productImage;
                    const img = document.createElement("img");
                    img.setAttribute("src", imgPath);
                    row.append(img);



                    row.append($('<td>').text(product.productRating));
                    row.append($('<td>').text(product.productDiscount));
                    row.append($('<td>').text(product.productIsActive));
                    productsBody.append(row);
                }
            })
            .catch(error => {
                alert(error.message);
            });
    });
});

