package org.preproject.CRUDSecAndFront.controller;


import org.preproject.CRUDSecAndFront.enums.UserStatus;
import org.preproject.CRUDSecAndFront.model.User;
import org.preproject.CRUDSecAndFront.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class VkController {

    @Autowired
    private VkService vkService;


    //TODO методы для работы с ВК


    //TODO Отправляем комментарий в ВК         /want_to_be_admin
    @PostMapping("/post_comment")
    public ResponseEntity<String> postToVK(@RequestParam("message") String message) {
        return vkService.sendCommentToVK(message);
    }

    //TODO Получаем данные из ВК
    @GetMapping("/get_comments")
    public ResponseEntity<String> getFromVK() {
        return vkService.getCommentsFromVK();
    }

    //TODO Редактируем комментарий в ВК
    @GetMapping("/edit_comment")
    public ResponseEntity<String> checkResponseAdmin(@RequestParam("comment_id") String commentId,
                                                     @RequestParam("message") String message) {
        return vkService.editCommentFromVK(commentId, message);
    }

    //TODO Удаляем комментарий из ВК
    @GetMapping("/delete_comment")
    public ResponseEntity<String> deleteComment(@RequestParam("comment_id") String commentId) {
        return vkService.deleteCommentFromVK(commentId);
    }


    //TODO методы для работы с БД


    //TODO Меняем СТАТУС запроса пользователя. "Я ХОЧУ СТАТЬ АДМИНОМ"
    @PostMapping("/change_user_status_to_I_WANT_TO_BE_A_ADMIN")
    public ResponseEntity<String> changeUserStatusToIWantToBeAAdmin(@RequestParam("user_id") Integer id) {
        vkService.changeUserStatus(id, UserStatus.I_WANT_TO_BE_A_ADMIN);
        return new ResponseEntity<>("Изменили состояние статуса пользователя на 1", HttpStatus.OK);
    }

    //TODO Меняем СТАТУС запроса пользователя. "Админ дал доступ к роли АДМИН"
    @PostMapping("/change_user_status_to_OK")
    public ResponseEntity<String> changeUserStatusToOK(@RequestParam("user_id") Integer id) {
        vkService.changeUserStatus(id, UserStatus.OK);
        return new ResponseEntity<>("Изменили состояние статуса пользователя на 2", HttpStatus.OK);
    }

    //TODO Меняем СТАТУС запроса пользователя. "Админ НЕ дал доступ к роли АДМИН"
    @PostMapping("/change_user_status_to_NO")
    public ResponseEntity<String> changeUserStatusToNO(@RequestParam("user_id") Integer id) {
        vkService.changeUserStatus(id, UserStatus.NO);
        return new ResponseEntity<>("Изменили состояние статуса пользователя на 3", HttpStatus.OK);
    }

    //TODO Меняем СТАТУС запроса пользователя. "НОВЫЙ АДМИН"
    @PostMapping("/change_user_status_to_NEW_ADMIN")
    public ResponseEntity<String> changeUserStatusToAdmin(@RequestParam("user_id") Integer id) {
        vkService.changeUserStatus(id, UserStatus.NEW_ADMIN);
        return new ResponseEntity<>("Изменили состояние статуса пользователя на 4", HttpStatus.OK);
    }

    //TODO Меняем СТАТУС запроса пользователя. "Был запрос на АДМИН, но отклонили"
    @PostMapping("/change_user_status_to_REFUSE_REQUEST")
    public ResponseEntity<String> refuseToChangeUserStatusToAdmin(@RequestParam("user_id") Integer id) {
        vkService.changeUserStatus(id, UserStatus.REFUSE_REQUEST);
        return new ResponseEntity<>("Изменили состояние статуса пользователя на 5", HttpStatus.OK);
    }

    //TODO Получаем данные о запросивших повышение роли пользователях из БД
    @GetMapping("/get_users_which_WANT_TO_BE_A_ADMIN")
    public List<User> getUsersWhichWantToBeAAdmin() {
        return vkService.getUsersWhichWantToBeAAdmin(UserStatus.I_WANT_TO_BE_A_ADMIN);
    }

    //TODO Получаем номер статуса пользователя по его ID из БД
    @GetMapping("/get_user_status_number")
    public Integer getUserStatusNumber(@RequestParam("user_id") Integer userId) {
        return vkService.getUserStatusNumber(userId);
    }



//    старые версии методов
//    @PostMapping("/want_to_be_admin")
//    public ResponseEntity<String> postToVK(@RequestBody String MESSAGE) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", TOKEN);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("owner_id", OWNER_ID);
//        map.add("post_id", POST_ID);
//        map.add("message", MESSAGE);
//        map.add("v", V);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                URL_CREATE_COMMENT, request , String.class);
//        return new ResponseEntity<>("Запрос отправен в ВК", HttpStatus.OK);
//    }
//
//    @GetMapping("/get_new_admin")
//    public ResponseEntity<String> getFromVK() {
////        RestTemplate restTemplate = new RestTemplate();
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////        headers.set("Authorization", TOKEN);
//
////        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
////        map.add("owner_id", OWNER_ID);
////        map.add("post_id", POST_ID);
////        map.add("preview_length", PREVIEW_LENGTH);
////        map.add("v", V);
//
////        HttpEntity<String> request = new HttpEntity<>("", headers); //map, headers
//
////        ResponseEntity<String> response
////                = restTemplate.getForEntity(URL_GET_COMMENTS, String.class, request);
////        ResponseEntity<String> response =
////                restTemplate.exchange(URL_GET_COMMENTS, HttpMethod.GET, request, String.class);
//
//        Map<String, String> params = new HashMap<>();
//        params.put("owner_id", OWNER_ID);
//        params.put("post_id", POST_ID);
//        params.put("preview_length", PREVIEW_LENGTH);
//        params.put("v", V);
//
////        ResponseEntity<String> response = restTemplate.getForEntity("https://bhub.ru/add/{value}", String.class, params);
//
//        String uri = "https://api.vk.com/method/wall.getComments?owner_id=-220604044&post_id=2&preview_length=0" +
//                "&access_token=" + TOKEN1 +
//                "&v=5.131";
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
////        System.out.println("\n" + response + "\n");
//
//        return response;
//    }
//
//    @GetMapping("/delete_post")
//    public ResponseEntity<String> deleteComment(@RequestParam String commentId) {
////        System.out.println("\n" + commentId + "\n");
////        RestTemplate restTemplate = new RestTemplate();
//////        String commentId = "28";
////        String uri = "https://api.vk.com/method/wall.deleteComment" +
////                "?owner_id=-220604044" +
////                "&comment_id=" + commentId +
////                "&preview_length=0" +
////                "&access_token=" + TOKEN1 +
////                "&v=5.131";
//////        restTemplate.delete(uri);
////        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
////        return response;
//
////        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", TOKEN);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("owner_id", OWNER_ID);
//        map.add("comment_id", commentId);
//        map.add("preview_length", "0");
//        map.add("v", V);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                URL_DELETE_COMMENT, request , String.class);
//        return new ResponseEntity<>("Запрос на удаление отправен в ВК", HttpStatus.OK);
//    }
//
//    @GetMapping("/edit_comment")
//    public ResponseEntity<String> checkResponseAdmin(@RequestParam("comment_id") String comment_id,
//                                                     @RequestParam("message") String message) {
////        RestTemplate restTemplate = new RestTemplate();
//
//        String uri = "https://api.vk.com/method/wall.editComment" +
//                "?owner_id=" + OWNER_ID +
//                "&comment_id=" + comment_id +
//                "&message=" + message +
//                "&access_token=" + TOKEN1 +
//                "&v=" + V;
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
//        System.out.println("\n" + response + "\n");
//
//        return response;
//    }
}
