$(document).ready(function() {
    $('.show-button').click(function() {
        $(this).parent().next('.list').slideToggle();
        $(this).find('.arrow').toggleClass('rotate');
    });
});