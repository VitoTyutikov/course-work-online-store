var productsBody = $('#products-body');
window.addEventListener('load', function () {
    productTable = document.getElementById('products-table');
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
                row.append($('<td>').text(product.productImage));
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
        var productName = $('#product-name').val();
        var priceFrom = $('#price-from').val();
        var priceTo = $('#price-to').val();
        var categoryName = $('#category-name').val();
        var manufacturerName = $('#manufacturer-name').val();

        fetch('http://localhost:8080/products?productName=' + productName + '&priceFrom=' + priceFrom + '&priceTo=' + priceTo + '&categoryName=' + categoryName + '&manufacturersName=' + encodeURIComponent(manufacturerName))
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
                    row.append($('<td>').text(product.productImage));
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

