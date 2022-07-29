package web.command.customer;

import com.goit.javadev.database.entity_services.CompanyDaoService;
import com.goit.javadev.database.entity_services.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class CreateCustomer implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("maxId", new CustomerDaoService(connection).getMaxId())
        );

        engine.process("createCustomer", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
