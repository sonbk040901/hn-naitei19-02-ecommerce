$(function() {
  $('a[href*=#]:not([href=#])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top
        }, 1000);
        return false;
      }
    }
  });


  var slideHeight = "600px";

  $('#slider, .carousel.slide, .carousel-inner, .carousel-inner .item').css('height',slideHeight);

  $(window).resize(function(){'use strict',
      $('#slider, .carousel.slide, .carousel-inner, .carousel-inner .item').css('height',slideHeight);
  });


  var slideHeightz = "450px";

  $('#testimonials, #testimonials .carousel.slide, #testimonials .carousel-inner, #testimonials .carousel-inner .item').css('height',slideHeightz);

  $(window).resize(function(){'use strict',
      $('#testimonials, #testimonials .carousel.slide, #testimonials .carousel-inner, #testimonials .carousel-inner .item').css('height',slideHeightz);
  });


});