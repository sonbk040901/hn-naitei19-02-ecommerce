(() => {
    $(document).ready(function(){
        $('.toast').toast('show');
    });
    const btn = $("button")
        .filter("[data-target='huydonhang']")
    const orderId = $('#orderId').data('order-id');
    btn.click(() => {
        $.ajax(`/orders/${orderId}/cancel`, {
            method: "patch"
        }).done(() => {
            history.pushState(null,null,'?canceled');
            location.reload();
        })
    })
})()