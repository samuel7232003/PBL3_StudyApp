$(function () {
    $('.list-rate-apm').slideUp();
    $('.list-apm .item-apm .list-rate').click(function (event) {
        $(this.parentElement).next().slideToggle();
    });
})