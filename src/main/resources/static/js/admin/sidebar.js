jQuery(function ($) {
    $(".sidebar-dropdown > a").click(function () {
        $(".sidebar-submenu").slideUp(200);
        const parent = $(this).parent();
        const isParentActive = parent.hasClass("active");
        $(".sidebar-dropdown").removeClass("active");
        if (isParentActive) {
            parent.removeClass("active");
            return;
        }
        $(this).next(".sidebar-submenu").slideDown(200);
        parent.addClass("active");
    });

    $("#close-sidebar").click(function () {
        $(".page-wrapper").removeClass("toggled");
    });

    $("#show-sidebar").click(function () {
        $(".page-wrapper").addClass("toggled");
    });
});
