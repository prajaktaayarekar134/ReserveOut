package com.stackroute.authenticationservice.rabbitmqPublisher;

import com.stackroute.authenticationservice.Dao.UserDao;
import com.stackroute.authenticationservice.entities.User;
import com.stackroute.authenticationservice.rabbitmqConfig.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class UserPublisher {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    private User user;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/sendMessageToRabbitMq/{emailId}")
    public void  sendMessageToRabbitMq(@PathVariable String emailId)
    {
        Optional<User> optional=userDao.findById(emailId);
        User user = optional.get();
        String emailId1=user.getEmailId();
       // user.setUserName();
      //  String emailId = user.getEmailId();
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,emailId1);
    }

}
