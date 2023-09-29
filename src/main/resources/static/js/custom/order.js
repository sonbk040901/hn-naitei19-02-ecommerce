function init() {
    const callback = (type) => function () {
        const $this = $(this);
        const id = $this.attr("id");
        const handle = $(`[data-target="#${id}"]`);
        const icon = handle.find("i");
        icon.css("transform", type === "show" ? "rotate(.25turn)" : "rotate(0)");
    }
    $(".collapse")
        .on("show.bs.collapse", callback("show"))
        .on("hide.bs.collapse", callback("hide"));
}

init();