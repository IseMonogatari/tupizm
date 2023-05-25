// TODO достаём авторизованного пользователя для отображения информации на навбаре
function showAuthorizedUser() {
    let user;
    $.ajax({
        url: '/get_authorizedUser',
        type: 'GET',
        cache: false,
        async: false,
        contentType: 'application/json',
        success: function (response) {
            user = response;
            console.log("Авторизованный пользователь: ");
            console.log(user);
        },
        error: function (error) {
            console.log(error);
        }
    })
    return user;
}

function showEmail() {
    $("#authorized_user_email").append(showAuthorizedUser().email);
}

function showRoles() {
    $("#authorized_user_firstname").append(showAuthorizedUser().allRoles);
}