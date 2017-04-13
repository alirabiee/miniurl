$('.form-control.url-input').focus();
if( location.href.indexOf('error=741') >= 0 ) {
    $('.result.alert-warning').html('The url you entered was not valid.').show()
}
else if( location.href.indexOf('error=549') >= 0 ) {
    $('.result.alert-warning').html('Could not verify the captcha. Please try again.').show()
}
