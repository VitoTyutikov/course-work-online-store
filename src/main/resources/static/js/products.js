// Fetches and renders the categories select element
function renderCategoriesSelect() {
    fetch('http://localhost:8080/categories')
        .then(response => response.json())
        .then(categories => {
            const select = document.getElementById('category-name');
            select.innerHTML = '<option value="">Choose category</option>';
            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryName;
                option.textContent = category.categoryName;
                select.appendChild(option);
            });
        });
}

// Fetches and renders the manufacturers select element
function renderManufacturersSelect() {
    fetch('http://localhost:8080/manufacturers')
        .then(response => response.json())
        .then(manufacturers => {
            const select = document.getElementById('manufacturer-name');
            select.innerHTML = '<option value="">Choose manufacturer</option>';
            manufacturers.forEach(manufacturer => {
                const option = document.createElement('option');
                option.value = manufacturer.manufacturerName;
                option.textContent = manufacturer.manufacturerName;
                select.appendChild(option);
            });
        });
}

// Fetches and renders the products container
function renderProductsContainer(url) {
    const productsContainer = document.getElementById('products-container');
    productsContainer.innerHTML = ''; // Clear existing products

    fetch(url)
        .then(response => {
            if (!response.ok)
                throw new Error(`Unable to connect to server | HTTP Error: ${response.status}`);
            return response.json();
        })
        .then(products => {
            products.forEach(product => {
                const productCard = createProductCard(product);
                productsContainer.appendChild(productCard);
            });
        })
        .catch(error => {
            alert(error.message);
        });
}

// Creates a product card element
function createProductCard(product) {
    const card = document.createElement('div');
    card.className = 'col-md-4 mb-4';

    const img = document.createElement('img');
    img.src = product.productImage;
    img.className = 'card-img-top';
    img.alt = product.productName;
    card.appendChild(img);

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    const title = document.createElement('h5');
    title.className = 'card-title';
    title.textContent = product.productName;
    const link = document.createElement('a');
    link.href = 'http://localhost:8080/product/' + product.productId;
    link.appendChild(title);
    cardBody.appendChild(link);

    const manufacturer = document.createElement('p');
    manufacturer.className = 'card-text';
    manufacturer.textContent = `Manufacturer: ${product.manufacturersByManufacturerId.manufacturerName}`;
    cardBody.appendChild(manufacturer);

    const category = document.createElement('p');
    category.className = 'card-text';
    category.textContent = `Category: ${product.categoriesByCategoryId.categoryName}`;
    cardBody.appendChild(category);

    const price = document.createElement('p');
    price.className = 'card-text';
    price.textContent = `Price: $${product.productPrice}`;
    cardBody.appendChild(price);

    const description = document.createElement('p');
    description.className = 'card-text';
    description.textContent = product.productDescription;
    cardBody.appendChild(description);

    const rating = document.createElement('p');
    rating.className = 'card-text';
    rating.textContent = `Rating: ${Math.round(product.productRating)}`;
    cardBody.appendChild(rating);


    const form = document.createElement('form');
    form.action = '/cart/add/' + product.productId;

    const submitBtn = document.createElement('input');
    submitBtn.type = 'submit';
    submitBtn.value = 'Add to Cart';
    submitBtn.className = 'btn btn-primary';

    form.appendChild(submitBtn);
    cardBody.appendChild(form);

    card.appendChild(cardBody);

    return card;
}


// Handles the search form submission
function handleSearchFormSubmit(event) {
    event.preventDefault();
    const productName = document.getElementById('product-name').value;
    const priceFrom = document.getElementById('price-from').value;
    const priceTo = document.getElementById('price-to').value;
    const categoryName = document.getElementById('category-name').value;
    const manufacturerName = document.getElementById('manufacturer-name').value;

    let url = 'http://localhost:8080/products';
    // const params = [];

    // var url;
    // var productName = $('#product-name').val();
    // var priceFrom = $('#price-from').val();
    // var priceTo = $('#price-to').val();
    // var categoryName = $('#category-name').val();
    // var manufacturerName = $('#manufacturer-name').val();
    if (!productName && !priceFrom && !priceTo && !categoryName && !manufacturerName) {
        url = "http://localhost:8080/products";
    } else if (categoryName === '') {
        alert("Choose category");
        return;
    } else {
        url = 'http://localhost:8080/products/' + categoryName + '?productName=' + productName + '&priceFrom=' + priceFrom + '&priceTo=' + priceTo + '&manufacturersName=' + encodeURIComponent(manufacturerName);
    }


    renderProductsContainer(url);
}

// Initialize the page
document.addEventListener('DOMContentLoaded', function () {
    renderCategoriesSelect();
    renderManufacturersSelect();
    renderProductsContainer('http://localhost:8080/products');

    const searchForm = document.getElementById('search-form');
    searchForm.addEventListener('submit', handleSearchFormSubmit);
});