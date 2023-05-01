package controller;

import service.DeveloperService;
import view.ConsoleDeveloperView;

public class DeveloperController {
    private final ConsoleDeveloperView view;
    private final DeveloperService service;

    public DeveloperController(ConsoleDeveloperView view, DeveloperService service) {
        this.view = view;
        this.service = service;
    }

    // Методы для обработки запросов пользователя и взаимодействия с сервисным слоем
}
//TODO: to make Controllers
