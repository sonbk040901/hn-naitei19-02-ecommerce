$("#submit-filter").on("click", function (e) {
  e.preventDefault();
  const urlParams = new URLSearchParams(window.location.search);
  urlParams.set("minPrice", $("#min-price-filter").val());
  urlParams.set("maxPrice", $("#max-price-filter").val());
  urlParams.set("numberOfSale", $("#select-sale-filter").val());
  window.location.search = urlParams;
});

$(".sort-item-price").map(function () {
  $(this).on("click", function () {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set("sortBy", $(this).data("type"));
    urlParams.set("sortType", $(this).data("sort"));
    window.location.search = urlParams;
  });

  const urlParams = new URLSearchParams(window.location.search);
  if (
    $(this).data("type") === urlParams.get("sortBy") &&
    $(this).data("sort") === urlParams.get("sortType")
  ) {
    $(this).addClass("active");
  }
});
