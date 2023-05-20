$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8080/orders',
        dataType: 'json',
        success: function (orders) {
            const ordersList = $("#orders-list");

            for (const order of orders) {
                const orderCard = $('<div>').addClass('card mb-3');
                const cardBody = $('<div>').addClass('card-body row');

                const orderId = $('<div>').addClass('col-md-3');
                const orderIdLink = $('<a>').attr('href', '/order/' + order.orderId).text(order.orderId);
                orderId.append(orderIdLink);

                const orderDate = $('<div>').addClass('col-md-3').text(order.orderDate);
                const orderStatus = $('<div>').addClass('col-md-3').text('Order Status: ' + order.orderStatus);

                cardBody.append(orderId, orderDate, orderStatus);
                orderCard.append(cardBody);
                ordersList.append(orderCard);
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.status + ': ' + xhr.statusText);
            // alert('Unable to connect to server | HTTP Error: ' + xhr.status);
            window.location.href = "/login";
        }
    });
});