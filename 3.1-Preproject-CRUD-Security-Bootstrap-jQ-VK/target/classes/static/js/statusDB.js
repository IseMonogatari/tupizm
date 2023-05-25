// TODO какие функции у нас будут выполняться при запуске страницы
$(document).ready(function() {
    $('#wantToBeAdmin').click(function() {
        $(this).attr('disabled', true); // Либо добавить атрибут disabled
    });

    let userStatusNumber = UserStatusNumberFromDB(showAuthorizedUser().id);

    if (userStatusNumber === 1 || userStatusNumber === 2 || userStatusNumber === 3) {
        $('#wantToBeAdmin').attr('disabled', true); // скрываем кнопку, если уже отправили запрос, но ответа нет
    }  else {
        $('#wantToBeAdmin').attr('disabled', false); // показываем кнопку
    }
});


// TODO изменяем цвет таба при отправке запроса "стать админом"
function changeColor() {
    let tab = document.getElementById('new_admin');
    tab.style.backgroundColor = '#aa3402';
    tab.style.color = '#ffffff';
}




// TODO Получаем пользователей, которые хотят получить роль АДМИНИСТРАТОР, из БД!
function getUsersWhichWantToBeAAdminForTable() {
    let listUsers = getUsersWhichWantToBeAAdmin();

    let table;
    for (i = 0; i < listUsers.length; i++) {
        let userId = listUsers[i].id;
        let userName = listUsers[i].name;

        table = table +
            "<tr>" +
            "   <td>" + userId + "</td>" +
            "   <td>" + userName + "</td>" +
            "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='changeUserStatusToOKInDBAndEditCommentInVK("${userId}")'>Добавить роль</button>`
            + " "
            + `<button type=\"button\" class=\"btn btn-danger\" onclick='changeUserStatusToNOInDBAndEditCommentInVK("${userId}")'>Отказать</button>`
            + "</td>" +
            "</tr>";
    }
    $("#get_VK").html(table);

    // $.ajax({
    //     url: '/get_users_which_WANT_TO_BE_A_ADMIN',
    //     type: 'GET',
    //     async: false,
    //     contentType: 'application/json',
    //     success: function (listUsers) {
    //         console.log("список желающих \"админа\"");
    //         let table;
    //
    //         for (i = 0; i < listUsers.length; i++) {
    //             let userId = listUsers[i].id;
    //             let userName = listUsers[i].name;
    //
    //             table = table +
    //                 "<tr>" +
    //                 "   <td>" + userId + "</td>" +
    //                 "   <td>" + userName + "</td>" +
    //                 "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='changeUserStatusToOKInDBAndEditCommentInVK("${userId}")'>Добавить роль</button>`
    //                 + " "
    //                 + `<button type=\"button\" class=\"btn btn-danger\" onclick='changeUserStatusToNOInDBAndEditCommentInVK("${userId}")'>Отказать</button>`
    //                 + "</td>" +
    //                 "</tr>";
    //         }
    //         $("#get_VK").html(table);
    //     },
    //     error: function (error) {
    //         console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
    //     }
    // });
}

function getUsersWhichWantToBeAAdmin() {
    let userList;
    $.ajax({
        url: '/get_users_which_WANT_TO_BE_A_ADMIN',
        type: 'GET',
        async: false,
        contentType: 'application/json',
        success: function (listUsers) {
            console.log("список желающих \"админа\"");
            userList = listUsers;
        },
        error: function (error) {
            console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
        }
    });
    return userList;
}


// TODO Функции с обновлением СТАТУСа


//TODO Меняем статус пользователя на Я ХОЧУ БЫТЬ АДМИНОМ
function changeUserStatusToIWantToBeAAdmin(USER) {
    $.ajax({
        url: '/change_user_status_to_I_WANT_TO_BE_A_ADMIN',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            user_id: USER.id
        },
        success: function(result) {
            console.log(result);
            tableAuthorizedUser();   //Обновляем пользователя, чтоб сразу видеть статус
        },
        error: function (error) {
            console.log("ошибка запроса 'Я ХОЧУ БЫТЬ АДМИНОМ' " + error);
        }
    });
}

//TODO Изменяем статус пользователя на "ОК"
function changeUserStatusToOKInDB(USER) {
    $.ajax({
        url: '/change_user_status_to_OK',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            user_id: USER.id
        },
        success: function(result) {
            console.log("Обновили запрос на 'АДМИН' на ОК " + result);
            getUsersWhichWantToBeAAdminForTable(); //обновняем таблицу пользователей, желающих стать АДМИНОМ
        },
        error: function (error) {
            console.log("какая-то ошибка обновления в сатусе ОК " + error);
        }
    });
}

//TODO Изменяем статус пользователя на "NО"
function changeUserStatusToNOInDB(USER) {
    $.ajax({
        url: '/change_user_status_to_NO',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            user_id: USER.id
        },
        success: function(result) {
            console.log("Обновили запрос на 'АДМИН' на NO " + result);
            getUsersWhichWantToBeAAdminForTable(); //обновняем таблицу пользователей, желающих стать АДМИНОМ
        },
        error: function (error) {
            console.log("какая-то ошибка обновления в сатусе NO " + error);
        }
    });
}

//TODO Изменяем статус пользователя на "NEW_ADMIN"
function changeUserStatusToNewAdminInDB(USER) {
    $.ajax({
        url: '/change_user_status_to_NEW_ADMIN',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            user_id: USER.id
        },
        success: function(result) {
            console.log("Обновили запрос на 'АДМИН' на NEW_ADMIN " + result);
            showAuthorizedUser(); //обновняем личную страницу пользователя
        },
        error: function (error) {
            console.log("какая-то ошибка обновления в сатусе NEW_ADMIN " + error);
        }
    });
}

//TODO Изменяем статус пользователя на "Refuse_Request"
function changeUserStatusToRefuseRequestInDB(USER) {
    $.ajax({
        url: '/change_user_status_to_REFUSE_REQUEST',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            user_id: USER.id
        },
        success: function(result) {
            console.log("Обновили запрос на 'АДМИН' на REFUSE REQUEST " + result);
            showAuthorizedUser(); //обновняем личную страницу пользователя
        },
        error: function (error) {
            console.log("какая-то ошибка обновления в сатусе REFUSE REQUEST " + error);
        }
    });
}



// TODO Добавляем пользователю роль "АДМИН"
function changeUserRoleToAdminInDB(USER) {
    $.ajax({
        url: '/update_user',
        type: 'POST',
        cache: false,
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({
            id: USER.id,
            lastName: USER.lastName,
            name: USER.name,
            email: USER.email,
            password: null, //не передаём пароль, ибо он зашифрованный, что нельзя будет сравнить
            role: "ROLE_ADMIN"
        }),
        success: function(result) {
            console.log("Дали роль 'АДМИН' " + result);
        },
        error: function (error) {
            console.log("какая-то ошибка обновления РОЛИ " + error);
        }
    });
}



// TODO Получаем пользователей, которые хотят получить роль АДМИНИСТРАТОР, из БД. Для закладки!
function getUsersWhichWantToBeAAdminForButton() {   //Этот метод не нужно постоянно чекать, надо его иначе...
    let exist = false;
    $.ajax({
        url: '/get_users_which_WANT_TO_BE_A_ADMIN',
        type: 'GET',
        async: false,
        contentType: 'application/json',
        success: function (listUsers) {
            if (listUsers.length !== 0) {
                exist = true;
                console.log("для покраснения");
            }
        },
        error: function (error) {
            console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
        }
    });
    return exist;
}


// TODO Что решили по поводу роли АДМИН?
function checkUserStatusInDBAndDeleteCommentInVKIfUserStatusIsNotIWTBaADMIN() {
    let user = showAuthorizedUser();
    let userStatusNumber = UserStatusNumberFromDB(user.id);
    let responseFromVK = getCommentsFromVK();
    if (userStatusNumber === -1 || userStatusNumber === 0) {
        set_message_info("#refuseMessage", "Вы не отправляли запрос на получение роли АДМИНИСТРАТОРА.");
    } else if (userStatusNumber === 1) {
        set_message_info("#refuseMessage", "Запрос пока в обработке.");
    }  else if (userStatusNumber === 2) {
        set_message_success("#refuseMessage", "Поздравляем!\nВы стали новым АДМИНИСТРАТОРОМ.");
        changeUserRoleToAdminInDB(user);
        changeUserStatusToNewAdminInDB(user);
        deleteThisComment(responseFromVK, user);
        tableAuthorizedUser();
    }  else if (userStatusNumber === 3) {
        set_message_error("#refuseMessage", "Простите!\nНо Вам отказано в запросе стать АДМИНИСТРАТОРОМ.");
        changeUserStatusToRefuseRequestInDB(user);
        deleteThisComment(responseFromVK, user);
        tableAuthorizedUser();
    } else if (userStatusNumber === 5) {
        set_message_info("#refuseMessage", "Ранее Вам было отказано в запросе стать АДМИНИСТРАТОРОМ.");
    } else {
        set_message_error("#refuseMessage", "Вы КТО?!");
    }
}



// //TODO функция проверки отказа в обновлении РОЛИ пользователя
// function UserStatusNumber() {
//     let user = showAuthorizedUser();
//     console.log(user);
//     let userStatusNumber;
//     if (user.status === null) {
//         userStatusNumber = 0;  //Означает, что Статус у пользователя NULL
//     } else if (user.status.statusNumber === 1) {
//         userStatusNumber = 1;  //Означает, что Статус у пользователя "I_WANT_TO_BE_A_ADMIN"
//     } else if (user.status.statusNumber === 2) {
//         userStatusNumber = 2;  //Означает, что Статус у пользователя "OK"
//     } else if (user.status.statusNumber === 3) {
//         userStatusNumber = 3;  //Означает, что Статус у пользователя "NO"
//     } else if (user.status.statusNumber === 5) {
//         userStatusNumber = 5;  //Означает, что Статус у пользователя "REFUSE_REQUEST"
//     } else {
//         userStatusNumber = 0;
//     }
//     console.log("Из функции UserStatusNumber() выходит это число: " + userStatusNumber);
//     return userStatusNumber;
// }

function UserStatusNumberFromDB(USER_ID) {
    let number;
    $.ajax({
        url: '/get_user_status_number',
        type: 'GET',
        cache: false,
        async: false,
        data: {
            user_id: USER_ID
        },
        success: function (statusNumber) {
            number = statusNumber;
            console.log("Номер статуса пользователя = " + number);
        },
        error: function (error) {
            console.log("Ошибка в получении номера статуса пользователя из БД " + error);
        }
    });
    return number;
}