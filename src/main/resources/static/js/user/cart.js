function addToCart(cartDetails, productId) {
  if (!cartDetails.find((x) => x.product_id === productId)) {
    var addToCart = {
      productId: productId,
      quantity: 1,
      // cartId: Number($(".my-cart").attr("id")),
      cartId: 11,
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
$("#update-cart-success").hide();
$("#update-cart-fail").hide();

$(".cart-item").each((index, element) => {
  const input = element.querySelector(".cart-item-quantity");
  const priceElement = element.querySelector(".cart-item-price");
  const totalPriceElement = element.querySelector(".cart-item-total-price");
  input.addEventListener("change", (event) => {
    const quantity = event.target.value;
    const price = returnPrice(priceElement.textContent);
    const totalPrice = quantity * price;
    totalPriceElement.textContent = formatPrice(totalPrice) + " VND";

    const cartId = element.getAttribute("data-cart-id");
    const productId = element.getAttribute("data-product-id");
    updateCart(cartId, productId, quantity);
  });
});

function updateCart(cartId, productId, quantity) {
  $.ajax({
    url: `/cart/${cartId}/update/${productId}`,
    type: "put",
    contentType: "application/json",
    data: JSON.stringify({
      quantity: quantity,
    }),
    success: function (response) {
      if (response) {
        $("#update-cart-success").show();
        $("#update-cart-fail").hide();
        setTimeout(() => {
          $("#update-cart-success").hide();
        }, 3000);
      } else {
        $("#update-cart-fail").show();
        $("#update-cart-success").hide();
        setTimeout(() => {
          $("#update-cart-fail").hide();
        }, 3000);
      }
      countTotalPrice();
    },
    error: function (xhr) {
      $("#update-cart-fail").show();
      $("#update-cart-success").hide();
      setTimeout(() => {
        $("#update-cart-fail").hide();
      }, 3000);
    },
  });
}

function removeCartItem(cartId, productId) {
  console.log(cartId, productId);
  $.ajax({
    url: `/cart/${cartId}/delete/${productId}`,
    type: "delete",
    contentType: "application/json",
    success: function (response) {
      if (response) {
        $(".cart-item").each((index, element) => {
          if (element.getAttribute("data-product-id") == productId) {
            element.remove();
          }
        });
        $(".num_of_products").text(Number($(".num_of_products").text()) - 1);
        $("#update-cart-success").show();
        $("#update-cart-fail").hide();
        setTimeout(() => {
          $("#update-cart-success").hide();
        }, 3000);
        countTotalPrice();
      } else {
        $("#update-cart-fail").show();
        $("#update-cart-success").hide();
        setTimeout(() => {
          $("#update-cart-fail").hide();
        }, 3000);
      }
    },
    error: function (xhr) {
      $("#update-cart-fail").show();
      $("#update-cart-success").hide();
      setTimeout(() => {
        $("#update-cart-fail").hide();
      }, 3000);
    },
  });
}

function countTotalPrice() {
  let total = 0;
  $(".cart-item-total-price").each((index, element) => {
    total += returnPrice(element.textContent);
  });
  $(".cart-total-price").text(formatPrice(total) + " VND");
}

function formatPrice(price) {
  return price.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
}

function returnPrice(price) {
  return parseInt(price.replace(/\./g, ""));
}
