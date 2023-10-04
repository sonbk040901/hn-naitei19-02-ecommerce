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

function submitAddToCartForm() {
  const quantity = $("#product-quantity").val();
  const productId = $("#product-quantity").data("product-id");
  addToCart(productId, quantity);
}
function addToCart(productId, quantity) {
  $.ajax({
    type: "POST",
    url: "/cart/add",
    data: JSON.stringify({
      productId,
      quantity: quantity ? parseInt(quantity) : 1,
    }),
    contentType: "application/json",
    success: function (response) {
      if (!parseInt(response)) {
        alert("Please login to add product to cart!");
        return;
      }
      $("#cart_size").text(response);
      alert("Product added to cart successfully!");
    },
    error: function (xhr) {
      alert("Product added to cart failed!");
    },
  });
}

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
  $.ajax({
    url: `/cart/${cartId}/delete/${productId}`,
    type: "delete",
    contentType: "application/json",
    success: function (response) {
      if (response > 0) {
        $(".cart-item").each((index, element) => {
          if (element.getAttribute("data-product-id") == productId) {
            element.remove();
          }
        });
        $("#update-cart-success").show();
        $("#update-cart-fail").hide();
        setTimeout(() => {
          $("#update-cart-success").hide();
        }, 3000);
        countTotalPrice();
        $("#cart_size").text(response);
      } else if (response == 0) {
        window.location.href = "/cart";
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

function checkNumber(string) {
  return /^\d+$/.test(string);
}
