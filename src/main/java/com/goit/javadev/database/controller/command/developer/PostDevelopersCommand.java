package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.model.entity.developer.Developer;
import com.goit.javadev.database.model.entity_services.DeveloperDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class PostDevelopersCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();
        DeveloperDaoService developerDaoService = new DeveloperDaoService(connection);

        Developer developer = new Developer();

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        Developer.Gender gender = Developer.Gender.valueOf(req.getParameter("gender").toUpperCase());
        int salary = Integer.parseInt(req.getParameter("salary"));
        int companyId = Integer.parseInt(req.getParameter("companyId"));


        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(salary);
        developer.setCompanyId(companyId);

        developerDaoService.insertNewEntity(developer);

        resp.sendRedirect("/dao/developer");
    }
}
