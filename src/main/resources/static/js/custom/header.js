//Gọi hàm này khi muốn update lại thông tin header
function renderHeaderInfo() {
    $.ajax("/header-info").done(({cartSize, orderSize, notificationSize} = {
        cartSize: 0,
        orderSize: 0,
        notificationSize: 0
    }) => {
        $("#cart_size").text(cartSize);
        $("#order_size").text(orderSize);
        $("#notification_size").text(notificationSize);
    });
}

(() => {
    $(document).ready(function () {
        $('.toast').toast('show');
    });
    renderHeaderInfo();
})();