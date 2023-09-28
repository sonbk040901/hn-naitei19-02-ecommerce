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

$(document).ready(function () {
  $("html, body").animate(
    { scrollTop: $("#products-content").offset().top },
    200
  );
  if ($(".bbb_slider").length) {
    var trendsSlider = $(".bbb_slider");
    trendsSlider.owlCarousel({
      loop: false,
      margin: 30,
      nav: false,
      dots: false,
      autoplayHoverPause: true,
      autoplay: false,
      responsive: {
        0: { items: 1 },
        575: { items: 2 },
        991: { items: 3 },
      },
    });

    trendsSlider.on("click", ".bbb_fav", function (ev) {
      $(ev.target).toggleClass("active");
    });

    if ($(".bbb_prev").length) {
      var prev = $(".bbb_prev");
      prev.on("click", function () {
        trendsSlider.trigger("prev.owl.carousel");
      });
    }

    if ($(".bbb_next").length) {
      var next = $(".bbb_next");
      next.on("click", function () {
        trendsSlider.trigger("next.owl.carousel");
      });
    }
  }
});

$(".format-price").each(function () {
  $(this).text(formatPrice($(this).text()));
});

function formatPrice(price) {
  return price.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
}

function returnPrice(price) {
  return parseInt(price.replace(/\./g, ""));
}

$(document).on("load", function () {
  $("html, body").animate(
    { scrollTop: $("#products-content").offset().bottom },
    100
  );
});
