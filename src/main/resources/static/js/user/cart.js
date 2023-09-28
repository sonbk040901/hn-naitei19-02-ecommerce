function addToCart(cartDetails, productId) {
  if (!cartDetails.find((x) => x.product_id === productId)) {
    var addToCart = {
      productId: productId,
      quantity: 1,
      cartId: Number($(".my-cart").attr("id")),
    };
    // Send the AJAX POST request
    $.ajax({
      type: "POST",
      url: "/cart/add", // Replace with your server-side processing script
      data: JSON.stringify(addToCart),
      contentType: "application/json",
      success: function (response) {
        $(".num_of_products").text(Number($(".num_of_products").text()) + 1);
        alert("Thêm sản phẩm vào giỏ hàng thành công!");
      },
    });
  } else {
    alert("Sản phẩm đã có sẵn trong giỏ hàng!");
  }
}
