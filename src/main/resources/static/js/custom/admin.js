$(".view-order").click(function(e) {
	e.preventDefault();
	var orderId = parseInt($(this).data("order-id"));

	$.ajax({
		type: "GET",
		url: "/admin/orders/" + orderId,
		data: { orderId: orderId },
		success: function(data) {
			// Nạp nội dung chi tiết đơn hàng vào modal
			$("#orderDetailModal .modal-body").html(data);

			// Hiển thị modal
			$("#orderDetailModal").modal("show");
		},
		error: function() {
			alert("Đã xảy ra lỗi khi tải chi tiết đơn hàng.");
		}
	});
});