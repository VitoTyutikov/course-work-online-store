// window.addEventListener('load', function () {
//     productTable = document.getElementById('products-table');
//     fetch('http://localhost:8080/products')
//         .then((response) => {
//             if (!response.ok)
//                 alert("Unable to connect to server | HTTP Error: ${response.status}");
//             return response.json();
//         })
//         .then((json) => {
//
//             const products = json;
//             for (const product of products) {
//                 const tdName = document.createElement('td');
//                 const tdManufacturerName = document.createElement('td');
//                 const tdCategoryName = document.createElement('td')
//                 const tdProductPrice = document.createElement('td');
//                 const tdProductDescription = document.createElement('td');
//                 const tdProductImage = document.createElement('td');
//                 const tdProductRating = document.createElement('td');
//                 const tdProductDiscount = document.createElement('td');
//                 const tdProductIsActive = document.createElement('td');
//
//
//                 // tdId.textContent = product.productId;
//                 tdName.textContent = product.productName;
//                 // tdManufacturerId.textContent = product.manufacturerId;
//                 tdManufacturerName.textContent = product.manufacturersByManufacturerId.manufacturerName;
//                 // tdCategoryId.textContent = product.categoryId;
//                 tdCategoryName.textContent = product.categoriesByCategoryId.categoryName;
//                 tdProductPrice.textContent = product.productPrice;
//                 tdProductDescription.textContent = product.productDescription;
//                 tdProductImage.textContent = product.productImage;
//                 tdProductRating.textContent = product.productRating;
//                 tdProductDiscount.textContent = product.productDiscount;
//                 tdProductIsActive.textContent = product.productIsActive;
//
//
//                 const tr = document.createElement('tr');
//                 // tr.appendChild(tdId);
//                 tr.appendChild(tdName);
//                 // tr.appendChild(tdManufacturerId)
//                 tr.appendChild(tdManufacturerName);
//                 tr.appendChild(tdCategoryName);
//                 tr.appendChild(tdProductPrice);
//                 tr.appendChild(tdProductDescription);
//                 tr.appendChild(tdProductImage);
//                 tr.appendChild(tdProductRating);
//                 tr.appendChild(tdProductDiscount);
//                 tr.appendChild(tdProductIsActive);
//
//                 productTable.appendChild(tr);
//             }
//         });
// })
//

$(document).ready(function () {
    $('#search-form').submit(function (event) {
        event.preventDefault();
        var productName = $('#product-name').val();
        var priceFrom = $('#price-from').val();
        var priceTo = $('#price-to').val();
        var categoryName = $('#category-name').val();
        var manufacturerName = $('#manufacturer-name').val();

        $.ajax({
            url: 'http://localhost:8080/products',
            method: 'GET',
            data: {
                productName: productName,
                priceFrom: priceFrom,
                priceTo: priceTo,
                categoryName: categoryName,
                manufacturersName: [manufacturerName]
            },
            success: function (data) {
                var productsBody = $('#products-body');
                productsBody.empty();

                for (var i = 0; i < data.length; i++) {
                    var product = data[i];
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
            },
            error: function () {
                alert('Error retrieving products.');
            }

        });
    }

    );
});
