const initQuery = () => {
    const from = $('#from_date');
    const to = $('#to_date');
    const statuss = $('#status .filter-btn');
    // times.click(function () {
    //     const cur = $(this);
    //     times.removeClass('active');
    //     cur.toggleClass('active');
    // });
    statuss.click(function () {
        const cur = $(this);
        statuss.removeClass('active');
        cur.toggleClass('active');
    });
    const filterBtn = $('#filter-btn');
    filterBtn.click(() => {
        const params = new URLSearchParams(location.search);
        const newParams = new URLSearchParams();
        newParams.set('s', params.get('s') || "");
        newParams.set('from', from.val());
        newParams.set('to', to.val());
        const d2 = statuss.filter('.active').data('value');
        newParams.set('s', d2);
        history.pushState(null, null, '?' + newParams.toString());
        location.reload();
    });
};
initQuery();